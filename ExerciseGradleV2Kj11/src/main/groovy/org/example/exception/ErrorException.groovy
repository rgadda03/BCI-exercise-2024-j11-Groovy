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

}
