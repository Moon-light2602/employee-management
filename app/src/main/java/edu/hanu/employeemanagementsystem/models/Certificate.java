package edu.hanu.employeemanagementsystem.models;

public class Certificate {
    private int certificatedID;
    private String certificateName;
    private String certificateRank;
    private String certificatedDate;

    public Certificate (String certificateName, String certificateRank, String certificatedDate) {
        this.certificateName = certificateName;
        this.certificateRank = certificateRank;
        this.certificatedDate = certificatedDate;
    }

    public int getCertificatedID() {
        return certificatedID;
    }

    public void setCertificatedID(int certificatedID) {
        this.certificatedID = certificatedID;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateRank() {
        return certificateRank;
    }

    public void setCertificateRank(String certificateRank) {
        this.certificateRank = certificateRank;
    }

    public String getCertificatedDate() {
        return certificatedDate;
    }

    public void setCertificatedDate(String certificatedDate) {
        this.certificatedDate = certificatedDate;
    }


}
