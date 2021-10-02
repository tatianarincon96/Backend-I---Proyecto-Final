package com.dh.clinica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    private final Logger logger = Logger.getLogger(GlobalException.class);

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({FindByIdException.class})
    public ResponseEntity<String> procesarErrorNotFoundById(FindByIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<String> procesarErrorServiceException(ServiceException ex) {
        logger.error(ex.getMessage() + ", " +  ex.operation);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler({UnauthorizedAccessException.class})
    public ResponseEntity<String> procesarErrorPermisoNoAutorizadoException(UnauthorizedAccessException ex) {
        logger.error(ex.getMessage() + ", " +  ex.operation);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage() + " " +  headers + " " + status + " " + request);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene permisos para acceder a este recurso");
    }
}
