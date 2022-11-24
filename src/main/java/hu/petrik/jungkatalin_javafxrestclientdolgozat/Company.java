package hu.petrik.jungkatalin_javafxrestclientdolgozat;
import com.google.gson.annotations.Expose;

public class Company {
    private int id;
    @Expose
    private String companyName;

    @Expose
    private int CVV;

    @Expose
    private String phoneNumber;

    @Expose
    private boolean kid;

    public Company(int id, String companyName, int CVV, String phoneNumber, boolean kid) {
        this.id = id;
        this.companyName = companyName;
        this.CVV = CVV;
        this.phoneNumber = phoneNumber;
        this.kid = kid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isKid() {
        return kid;
    }

    public void setKid(boolean kid) {
        this.kid = kid;
    }
}
