package java;

import javax.persistence.*;

@Entity
@Table(name = "clients_code", schema = "public", catalog = "verification")
public class ClientsCodeEntity {
    private Long id;
    private Long mobileNum;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mobile_num", nullable = false)
    public Long getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(Long mobileNum) {
        this.mobileNum = mobileNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientsCodeEntity that = (ClientsCodeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mobileNum != null ? !mobileNum.equals(that.mobileNum) : that.mobileNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mobileNum != null ? mobileNum.hashCode() : 0);
        return result;
    }
}
