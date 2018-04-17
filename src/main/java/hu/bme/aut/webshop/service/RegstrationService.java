package hu.bme.aut.webshop.service;

import hu.bme.aut.webshop.dao.CustomerRepository;
import hu.bme.aut.webshop.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegstrationService {


    CustomerRepository customerRepository;

    @Transactional
    public Long registerCustomer(Customer customer){
        Customer existingCustomer =
    }
}
