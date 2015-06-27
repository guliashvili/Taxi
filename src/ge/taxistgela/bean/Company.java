package ge.taxistgela.bean;

import ge.taxistgela.helper.ExternalAlgorithms;

/**
 * Created by Alex on 5/25/2015.
 */
public class Company extends GeneralCheckableInformation {
    private Integer companyID;
    private String companyCode;
    private String email;
    private String password;
    private String companyName;
    private String phoneNumber;
    private String facebookID;
    private String googleID;
    private Boolean isVerifiedEmail;
    private Boolean isVerifiedPhone;


    public Company() {
    }

    public Company(Integer companyID, String companyCode, String email, String password, String companyName, String phoneNumber, String facebookID, String googleID, Boolean isVerifiedEmail, Boolean isVerifiedPhone) {
        setCompanyID(companyID);
        setCompanyCode(companyCode);
        setEmail(email);
        setPassword(password);
        setCompanyName(companyName);
        setPhoneNumber(phoneNumber);
        setFacebookID(facebookID);
        setGoogleID(googleID);
        setIsVerifiedEmail(isVerifiedEmail);
        setIsVerifiedPhone(isVerifiedPhone);
    }

    public Company(Company company) {
        this(company.getCompanyID(), company.getCompanyCode(), company.getEmail(), company.getPassword(), company.getCompanyName(),
                company.getPhoneNumber(), company.getFacebookID(), company.getGoogleID(), company.getIsVerifiedEmail(), company.getIsVerifiedPhone());
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Company)) return  false;
        Company o = (Company)obj;
        return  ExternalAlgorithms.equalsNull(getCompanyCode(),o.getCompanyCode()) &&
                ExternalAlgorithms.equalsNull(getCompanyID() , o.getCompanyID()) &&
                ExternalAlgorithms.equalsNull(getCompanyName(), o.getCompanyName()) &&
                ExternalAlgorithms.equalsNull(getEmail(), o.getEmail()) &&
                ExternalAlgorithms.equalsNull(getFacebookID(), o.getFacebookID()) &&
                ExternalAlgorithms.equalsNull(getGoogleID(),o.getGoogleID()) &&
                ExternalAlgorithms.equalsNull(getPassword(),o.getPassword()) &&
                ExternalAlgorithms.equalsNull(getPhoneNumber(), o.getPhoneNumber()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedEmail(), o.getIsVerifiedEmail()) &&
                ExternalAlgorithms.equalsNull(getIsVerifiedPhone(), o.getIsVerifiedPhone());
    }

    @Override
    public int hashCode() {
        return getCompanyID();
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getFacebookID() {
        return facebookID;
    }

    @Override
    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    @Override
    public String getGoogleID() {
        return googleID;
    }

    @Override
    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    @Override
    public Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    @Override
    public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    @Override
    public Boolean getIsVerifiedPhone() {
        return isVerifiedPhone;
    }

    @Override
    public void setIsVerifiedPhone(Boolean isVerifiedPhone) {
        this.isVerifiedPhone = isVerifiedPhone;
    }
}


