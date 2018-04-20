package hu.bme.aut.webshop.controller;

import hu.bme.aut.webshop.domain.Customer;
import hu.bme.aut.webshop.service.RegistrationService;
import hu.bme.aut.webshop.service.exception.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping(value="/registration")
    public ResponseEntity registerCustomer(@RequestBody Customer customer){
        try {
            registrationService.registerCustomer(customer);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
