package hu.bme.aut.webshop.dao;

import hu.bme.aut.webshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByEmail (String email);
}
