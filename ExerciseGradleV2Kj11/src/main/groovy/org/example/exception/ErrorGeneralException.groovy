package org.example.exception

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ErrorGeneralException {

    private List<ErrorException> error;

    List<ErrorException> getError() {
        return error
    }

    void setError(List<ErrorException> error) {
        this.error = error
    }
}
