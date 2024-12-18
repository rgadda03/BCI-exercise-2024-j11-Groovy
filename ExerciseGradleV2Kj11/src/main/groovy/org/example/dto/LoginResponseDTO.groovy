package org.example.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

import java.time.LocalDate

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class LoginResponseDTO {

    private UUID id; //UUID s232s-sadas2-2321sads-etc
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    @JsonProperty("isActive")
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

}
