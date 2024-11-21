
package com.api.atividade02.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //notação @ControllerAdvice é responsável pelo gerenciamento global das exceções.
public class ValidationHandler extends ResponseEntityExceptionHandler{
    //A classe ValidationHandler estende a classe ResponseEntityExceptionHandler, que é uma classe-base conveniente quando se 
    //deseja implementar esse tipo de tratamento de exceção centralizado, 
    //pois fornece métodos para lidar com exceções internas do Spring MVC.
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request){
        
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> { 
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            });
           return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST); 
    }
}
