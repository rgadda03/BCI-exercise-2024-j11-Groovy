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
class RegisterResponseDTO {

    private UUID id; //UUID s232s-sadas2-2321sads-etc
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    @JsonProperty("isActive")
    private boolean isActive;

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
}
