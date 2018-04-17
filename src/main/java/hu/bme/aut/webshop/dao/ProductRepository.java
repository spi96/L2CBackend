package hu.bme.aut.webshop.dao;

import hu.bme.aut.webshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
