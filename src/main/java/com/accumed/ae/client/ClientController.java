package com.accumed.ae.client;


import com.accumed.ae.history.HistoryController;
import com.accumed.ae.history.HistoryServices;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accumed.ae.vc.VerificationCodeServices;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping(path = "/client")
@ComponentScan("com.accumed.ae")
public class ClientController {
    //    private final Bucket bucket;
    private final ClientServices clientServices;
    private final VerificationCodeServices verificationCodeServices;
    private final HistoryServices historyServices;

    @Autowired
    public ClientController(ClientServices clientServices, VerificationCodeServices verificationCodeServices, HistoryServices historyServices) {
//        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(5, Duration.ofMinutes(15)));
//        this.bucket = Bucket4j.builder().addLimit(limit).build();
        this.verificationCodeServices = verificationCodeServices;
        this.clientServices = clientServices;
        this.historyServices = historyServices;
    }


    @GetMapping()
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok().body(clientServices.getClients());
    }

    @PostMapping
    public List<Client> registerNewClient(@RequestBody Client client) {
        clientServices.newClient(client);
        return clientServices.getClients();
    }

    @PostMapping(path = "/submit")
    public void submitID(@RequestParam Long clientID, String actionType) {
        if (historyServices.isRepeated(clientID, actionType)) {
            verificationCodeServices.setCode(clientID, actionType);
        } else {
            throw new IllegalStateException("ERROR");
        }
    }

    @PostMapping(path = "/verificationCode")
    public boolean submitCode(@RequestParam Long code, Long clientID, String actionType) {
        return clientServices.verifyCode(code,clientID,actionType);
    }


}
