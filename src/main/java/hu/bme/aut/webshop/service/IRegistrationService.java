package hu.bme.aut.webshop.service;

import hu.bme.aut.webshop.domain.Customer;
import hu.bme.aut.webshop.service.exception.UserExistsException;

public interface IRegistrationService {
    void registerCustomer(Customer customer)throws UserExistsException;
}
