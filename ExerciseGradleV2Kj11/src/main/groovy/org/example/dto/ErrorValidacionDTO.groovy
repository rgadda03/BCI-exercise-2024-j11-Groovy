package org.example.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

import java.sql.Timestamp

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ErrorValidacionDTO extends Throwable {

    private static final long serialVersionUID = 1L;

    private Timestamp timestamp;
    private int codigo;
    private String detail;

    public ErrorValidacionDTO(int codigo, String detail) {
        super();
        this.codigo = codigo;
        this.detail = detail;
    }

}
