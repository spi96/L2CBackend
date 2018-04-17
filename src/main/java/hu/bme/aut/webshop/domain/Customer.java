package hu.bme.aut.webshop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Spi on 2018. 04. 06..
 */
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "Zip_Code")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "House_Number"))
    })

    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
