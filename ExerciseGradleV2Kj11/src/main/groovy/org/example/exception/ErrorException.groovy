package org.example.exception

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

import java.sql.Timestamp

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ErrorException {

    private Timestamp timestamp;
    private int codigo;
    private String detail;

    Timestamp getTimestamp() {
        return timestamp
    }

    void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp
    }

    int getCodigo() {
        return codigo
    }

    void setCodigo(int codigo) {
        this.codigo = codigo
    }

    String getDetail() {
        return detail
    }

    void setDetail(String detail) {
        this.detail = detail
    }
}
