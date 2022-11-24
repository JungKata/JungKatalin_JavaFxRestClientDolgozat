package hu.petrik.jungkatalin_javafxrestclientdolgozat;
import com.google.gson.annotations.Expose;

public class Company {
    private int id;
    @Expose
    private String CompanyName;

    @Expose
    private int CVV;

    @Expose
    private String PhoneNumber;

    @Expose
    private boolean Kid;

    public Company(int id, String companyName, int CVV, String phoneNumber, boolean kid) {
        this.id = id;
        this.CompanyName = companyName;
        this.CVV = CVV;
        this.PhoneNumber = phoneNumber;
        this.Kid = kid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public boolean isKid() {
        return Kid;
    }

    public void setKid(boolean kid) {
        Kid = kid;
    }
}
