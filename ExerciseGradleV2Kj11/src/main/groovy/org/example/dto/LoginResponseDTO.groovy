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

    LoginResponseDTO() {
    }

    LoginResponseDTO(UUID id, LocalDate created, LocalDate lastLogin, String token,
                     boolean isActive, String name, String email, String password, List<PhoneDTO> phones) {
        this.id = id
        this.created = created
        this.lastLogin = lastLogin
        this.token = token
        this.isActive = isActive
        this.name = name
        this.email = email
        this.password = password
        this.phones = phones
    }

    UUID getId() {
        return id
    }

    void setId(UUID id) {
        this.id = id
    }

    LocalDate getCreated() {
        return created
    }

    void setCreated(LocalDate created) {
        this.created = created
    }

    LocalDate getLastLogin() {
        return lastLogin
    }

    void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin
    }

    String getToken() {
        return token
    }

    void setToken(String token) {
        this.token = token
    }

    boolean getIsActive() {
        return isActive
    }

    void setIsActive(boolean isActive) {
        this.isActive = isActive
    }

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
