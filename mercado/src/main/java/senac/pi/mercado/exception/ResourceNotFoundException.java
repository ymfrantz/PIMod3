
package com.api.atividade02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//A anotação @ResponseStatus é responsável por indicar que a classe retornará com status em “http”,
//de acordo com o informado, nesse caso, not found.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    
    
    //o construtor que recebe uma mensagem e informa ao construtor da superclasse 
    //para notificar quando a exceção ocorrer.
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
}
