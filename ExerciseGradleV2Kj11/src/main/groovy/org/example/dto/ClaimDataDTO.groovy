package org.example.dto

class ClaimDataDTO {

    private Date expierationDate
    private String email

    ClaimDataDTO() {
    }

    ClaimDataDTO(Date expierationDate, String email) {
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
