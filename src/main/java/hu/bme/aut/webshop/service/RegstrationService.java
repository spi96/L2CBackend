package hu.bme.aut.webshop.service;

import hu.bme.aut.webshop.dao.CustomerRepository;
import hu.bme.aut.webshop.domain.Customer;
import hu.bme.aut.webshop.service.exception.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegstrationService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Long registerCustomer(Customer customer)throws UserExistsException{
        Customer existingCustomer = customerRepository.findCustomerByEmail(customer.getEmail());

        if(existingCustomer != null){
            throw new UserExistsException();
        }

        return customerRepository.save(customer).getId();
    }
}
