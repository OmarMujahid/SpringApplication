package com.accumed.ae.vc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/verificationcode")


public class VerificationCodeController {

    private final VerificationCodeServices verificationCodeServices;
//private final ClientServices clientServices;
    @Autowired
    public VerificationCodeController(VerificationCodeServices verificationCodeServices) {
        this.verificationCodeServices = verificationCodeServices;
//        this.clientServices = clientServices;
    }

    @GetMapping
    public List<VerificationCode> getVerificationCodes() {
        return verificationCodeServices.getVerificationCodes();
    }

    @PostMapping
    public void registerNewCode(@RequestBody VerificationCode verificationCode) {
        verificationCodeServices.newVerificationCode(verificationCode);
    }
    @DeleteMapping(path = "/delete")
    public List<VerificationCode> deleteOrphan(@RequestParam Long id){
        verificationCodeServices.deletecode(id);
        return verificationCodeServices.getVerificationCodes();
    }

    @DeleteMapping(path = ("/deleteAll"))
    public void deleteAllVerificationCodes(){
        verificationCodeServices.deletAllCodes();
    }
}
