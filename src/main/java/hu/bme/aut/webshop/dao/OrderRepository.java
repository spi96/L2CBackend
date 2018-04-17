package hu.bme.aut.webshop.dao;

import hu.bme.aut.webshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
