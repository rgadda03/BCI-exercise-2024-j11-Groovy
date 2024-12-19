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

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    List<PhoneDTO> getPhones() {
        return phones
    }

    void setPhones(List<PhoneDTO> phones) {
        this.phones = phones
    }
}
