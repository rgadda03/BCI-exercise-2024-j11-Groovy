package org.example.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class PhoneDTO {

    private long number;
    private int citycode;
    private String countrycode;

    PhoneDTO() {
    }

    PhoneDTO(long number, int citycode, String countrycode) {
        this.number = number
        this.citycode = citycode
        this.countrycode = countrycode
    }

    long getNumber() {
        return number
    }

    void setNumber(long number) {
        this.number = number
    }

    int getCitycode() {
        return citycode
    }

    void setCitycode(int citycode) {
        this.citycode = citycode
    }

    String getCountrycode() {
        return countrycode
    }

    void setCountrycode(String countrycode) {
        this.countrycode = countrycode
    }
}
