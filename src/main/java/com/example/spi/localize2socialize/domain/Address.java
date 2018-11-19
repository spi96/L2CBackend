package com.example.spi.localize2socialize.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Spi on 2018. 04. 07..
 */
@Embeddable
public class Address implements Serializable {
    private String city;

    private String street;

    private String houseNumber;

    private String zipCode;

    public Address() {
    }

    public Address(String city, String street, String houseNumber, String zipCode) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return zipCode + ", " + city + ", " + street + " " + houseNumber;
    }
}
