package java;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "verification_code", schema = "public", catalog = "verification")
public class VerificationCodeEntity {
    private Long verificationCodeid;
    private Long code;
    private Date expirationDate;
    private Boolean isUsed;

    @Id
    @Column(name = "verification_codeid", nullable = false)
    public Long getVerificationCodeid() {
        return verificationCodeid;
    }

    public void setVerificationCodeid(Long verificationCodeid) {
        this.verificationCodeid = verificationCodeid;
    }

    @Basic
    @Column(name = "code", nullable = true)
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Basic
    @Column(name = "expiration_date", nullable = true)
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Basic
    @Column(name = "is_used", nullable = true)
    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationCodeEntity that = (VerificationCodeEntity) o;

        if (verificationCodeid != null ? !verificationCodeid.equals(that.verificationCodeid) : that.verificationCodeid != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        if (isUsed != null ? !isUsed.equals(that.isUsed) : that.isUsed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = verificationCodeid != null ? verificationCodeid.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (isUsed != null ? isUsed.hashCode() : 0);
        return result;
    }
}
