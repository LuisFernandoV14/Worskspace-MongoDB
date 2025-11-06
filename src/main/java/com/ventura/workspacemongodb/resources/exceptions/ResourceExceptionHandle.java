package com.ventura.workspacemongodb.resources.exceptions;

import com.ventura.workspacemongodb.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        // --- DEBUG TEST ---
        System.out.println("--- Handler: CAUGHT THE EXCEPTION ---");
        System.out.println("--- Handler: Is 'e' null? " + (e == null));
        System.out.println("--- Handler: Is 'e.getMessage()' null? " + (e.getMessage() == null));
        System.out.println("--- Handler: Is 'request' null? " + (request == null));
        System.out.println("--- Handler: Is 'request.getRequestURI()' null? " + (request.getRequestURI() == null));
        // --- END DEBUG TEST ---

        HttpStatus status = HttpStatus.NOT_FOUND;

        System.out.println("Vou criar em");
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Object not found.", e.getMessage(), request.getRequestURI());
        System.out.println("Ta criado em");

        return ResponseEntity.status(status).body(error);
    }

}
