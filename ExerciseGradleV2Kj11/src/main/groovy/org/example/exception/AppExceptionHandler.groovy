package org.example.exception

import org.example.dto.ErrorGeneralDTO
import org.example.dto.ErrorValidacionDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import java.sql.Timestamp
import java.time.Instant

@ControllerAdvice
class AppExceptionHandler {

    @ExceptionHandler([ErrorValidacionDTO.class])
    public ResponseEntity<Object> handleErrorDTOException (ErrorValidacionDTO errorDTO){

        Instant instanteActual = Instant.now();

        ErrorException errorTechnicalException = new ErrorException(Timestamp.from(instanteActual),errorDTO.getCodigo(),errorDTO.getDetail());
        ErrorGeneralException technicalException = new ErrorGeneralException();
        technicalException.setError(new ArrayList<>());
        technicalException.getError().add(errorTechnicalException);
        return new ResponseEntity<>(technicalException,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler([ErrorGeneralDTO.class])
    public ResponseEntity<Object> handleErrorDTOException (ErrorGeneralDTO errorDTO){

        Instant instanteActual = Instant.now();

        ErrorException errorTechnicalException = new ErrorException(Timestamp.from(instanteActual),errorDTO.getCodigo(),errorDTO.getDetail());
        ErrorGeneralException technicalException = new ErrorGeneralException();
        technicalException.setError(new ArrayList<>());
        technicalException.getError().add(errorTechnicalException);
        return new ResponseEntity<>(technicalException,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
