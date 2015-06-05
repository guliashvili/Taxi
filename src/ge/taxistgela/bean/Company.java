package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Company implements GeneralCheckableInformation {
    private int companyID;
    private String companyCode;
    private String email;
    private String password;
    private String companyName;
    private String phoneNumber;
    private String facebookID;
    private String googleID;
    private boolean isVerified;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Company)) return  false;
        Company o = (Company)obj;
        return  ExternalAlgorithms.equalsNull(getCompanyCode(),o.getCompanyCode()) &&
                getCompanyID() == o.getCompanyID() &&
                ExternalAlgorithms.equalsNull(getCompanyName(), o.getCompanyName()) &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(),o.getGoogleID()) &&
                ExternalAlgorithms.equalsNull(getPassword(),o.getPassword()) &&
                ExternalAlgorithms.equalsNull(getPhoneNumber(),o.getPhoneNumber()) &&
                isVerified() == o.isVerified;
    }

    @Override
    public int hashCode() {
        return getCompanyID();
    }

    public Company(int companyID, String companyCode, String email, String password, String companyName, String phoneNumber, String facebookID, String googleID,boolean isVerified) {
        this.companyID = companyID;
        this.companyCode = companyCode;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.facebookID = facebookID;
        this.googleID = googleID;
        this.isVerified = isVerified;
    }



    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
}
