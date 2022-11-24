package hu.petrik.jungkatalin_javafxrestclientdolgozat;

public class Company {
    private int id;

    private String companyName;

    private int CVV;

    private String phoneNumber;

    public Company(int id, String companyName, int CVV, String phoneNumber) {
        this.id = id;
        this.companyName = companyName;
        this.CVV = CVV;
        this.phoneNumber = phoneNumber;
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
}