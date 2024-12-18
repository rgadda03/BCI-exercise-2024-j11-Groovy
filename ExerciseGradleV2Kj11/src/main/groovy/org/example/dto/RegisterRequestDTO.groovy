package org.example.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class RegisterRequestDTO {

    private String name; //opcional
    private String email;
    private String password;
    private List<PhoneDTO> phones; //opcional

}
