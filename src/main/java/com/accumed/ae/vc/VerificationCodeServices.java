package com.accumed.ae.vc;

import com.accumed.ae.client.ClientRepository;
import com.accumed.ae.history.HistoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class VerificationCodeServices {
    private final VerificationCodeRepository verificationCodeRepository;
    private final ClientRepository clientRepository;
    private final HistoryServices historyServices;
    @Autowired
    public VerificationCodeServices(VerificationCodeRepository verificationCodeRepository, ClientRepository clientRepository, HistoryServices historyServices) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.clientRepository = clientRepository;
        this.historyServices = historyServices;
    }

    public List<VerificationCode> getVerificationCodes(){
        return verificationCodeRepository.findAll();
    }

    public void newVerificationCode(VerificationCode verificationCode){
        verificationCodeRepository.save(verificationCode);
    }

    public Long setCode(Long clientID, String actionType) {
//        String code = UUID.randomUUID().toString();
        Long code = ThreadLocalRandom.current().nextLong(1000, 10000);
            if (clientRepository.existsById(clientID)) {
                LocalDate a = java.time.LocalDate.now().plusDays(1L);
                VerificationCode x = new VerificationCode(
                        code,
                        false,
                        a,
                        actionType,
                        clientID

                );
                verificationCodeRepository.save(x);
                return code;
            } else {
                throw new IllegalStateException("client ID does not exist");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Client with This ID");

            }

    }
    public String getActionType(Long clientID){
        if(verificationCodeRepository.existsByClientID(clientID)) {
            return verificationCodeRepository.findByClientID(clientID).getActionType();
        }else { return "wrong ID";}
    }
    public Long getCode(Long clientID){
        if(verificationCodeRepository.existsByClientID(clientID))
      return  verificationCodeRepository.findByClientID(clientID).getCode();
        else throw new IllegalStateException("no client");
    }

    public void deletecode(Long id) {
        if(verificationCodeRepository.existsById(id)){
            verificationCodeRepository.deleteById(id);
        }
    }
    public LocalDate getExpirationDate(Long id){
        return verificationCodeRepository.findByClientID(id).getExpirationDate();
    }
    public void codeUsed(Long id){
        VerificationCode x = verificationCodeRepository.findByClientID(id);
        x.setUsed(true);
        verificationCodeRepository.save(x);
    }
    public VerificationCode findByIdService(Long clientID){
        return verificationCodeRepository.findById(clientID).get();
    }

    public void deletAllCodes() {
        verificationCodeRepository.deleteAll();
    }
}

