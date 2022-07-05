package com.accumed.ae.client;

import com.accumed.ae.history.HistoryServices;
import com.accumed.ae.vc.VerificationCodeRepository;
import com.accumed.ae.vc.VerificationCodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServices {
    private final ClientRepository clientRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final VerificationCodeServices verificationCodeServices;
    private final HistoryServices historyServices;
    @Autowired
    public ClientServices(ClientRepository clientRepository, VerificationCodeRepository verificationCodeRepository, VerificationCodeServices verificationCodeServices, HistoryServices historyServices) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.clientRepository = clientRepository;
        this.verificationCodeServices = verificationCodeServices;
        this.historyServices = historyServices;
    }
    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public void newClient(Client client){
        clientRepository.save(client);
    }

    public boolean verifyCode(Long code, Long clientID, String actionType){
        if (historyServices.isRepeated(clientID, actionType)) {
            if (
                    code.equals(verificationCodeServices.getCode(clientID))
                            && actionType.equals(verificationCodeServices.getActionType(clientID))
                            && !verificationCodeServices.findByIdService(clientID).isUsed()

            ) {
                verificationCodeServices.codeUsed(clientID);
                return true;
            } else return false;

        } else {
            throw new IllegalStateException("TOO MANY");
        }
    }
}
