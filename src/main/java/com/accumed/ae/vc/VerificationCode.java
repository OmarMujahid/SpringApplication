package com.accumed.ae.vc;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table()
public class VerificationCode {
    @Id
    @SequenceGenerator(
            name = "verificationCode_sequence",
            sequenceName = "verificationCode_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "verificationCode_sequence"
    )
    @Column(name = "verificationCodeID")
    private Long verificationCodeID;
    @Column(name = "clientID")
    private Long clientID;

    @Column()
    private Long code;




    @Column(name = "isUsed")
    private boolean isUsed;

    @Column(name = "expirationDate")
    private LocalDate expirationDate;

    @Column(name = "actionType")
    private String actionType;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getVerificationCodeID() {
        return verificationCodeID;
    }

    public void setVerificationCodeID(Long verificationCodeID) {
        this.verificationCodeID = verificationCodeID;
    }

    public VerificationCode(){}

    public VerificationCode(Long code, boolean isUsed, LocalDate expirationDate, String actionType, Long clientID) {
        this.actionType = actionType;
        this.clientID = clientID;
        this.code = code;
        this.isUsed = isUsed;
        this.expirationDate = expirationDate;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {

        this.expirationDate = expirationDate;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

}
