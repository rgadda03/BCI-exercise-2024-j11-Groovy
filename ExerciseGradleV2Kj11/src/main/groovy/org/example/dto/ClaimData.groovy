package org.example.dto

class ClaimData {

    private Date expierationDate
    private String email

    ClaimData() {
    }

    ClaimData(Date expierationDate, String email) {
        this.expierationDate = expierationDate
        this.email = email
    }

    Date getExpierationDate() {
        return expierationDate
    }

    void setExpierationDate(Date expierationDate) {
        this.expierationDate = expierationDate
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

}
