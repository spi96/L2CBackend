package hu.bme.aut.webshop.service.exception;

import java.io.Serializable;

public class UserExistsException extends Exception implements Serializable {

    public UserExistsException(){
        super("User already exists with the given e-mail address");
    }

    public UserExistsException(String message){
        super(message);
    }
}
