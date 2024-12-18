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

}
