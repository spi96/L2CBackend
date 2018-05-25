package hu.bme.aut.webshop.dao;

import hu.bme.aut.webshop.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(String role);
}
