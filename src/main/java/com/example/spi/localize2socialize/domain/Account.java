package com.example.spi.localize2socialize.domain;

import com.sun.jndi.toolkit.url.Uri;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Spi on 2018. 04. 06..
 */
@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String personId;

    private String personName;

    @Column(nullable = false)
    private String personGivenName;

    @Column(nullable = false)
    private String personFamilyName;

    @Column(nullable = false)
    private String personEmail;

    @Column(nullable = false)
    private String encodedPhoto;

    public Account() {
    }

    public Account(String personName, String personGivenName, String personFamilyName, String personEmail,
                   String personId, String encodedPhoto) {
        this.personName = personName;
        this.personGivenName = personGivenName;
        this.personFamilyName = personFamilyName;
        this.personEmail = personEmail;
        this.personId = personId;
        this.encodedPhoto = encodedPhoto;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonGivenName() {
        return personGivenName;
    }

    public void setPersonGivenName(String personGivenName) {
        this.personGivenName = personGivenName;
    }

    public String getPersonFamilyName() {
        return personFamilyName;
    }

    public void setPersonFamilyName(String personFamilyName) {
        this.personFamilyName = personFamilyName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEncodedPhoto() {
        return encodedPhoto;
    }

    public void setEncodedPhoto(String encodedImage) {
        this.encodedPhoto = encodedImage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Account) {
            Account acc = (Account) obj;
            return  personGivenName.equals(acc.personGivenName) &&
                    personFamilyName.equals(acc.personFamilyName) &&
                    personEmail.equals(acc.personEmail) &&
                    personName.equals(acc.personName) &&
                    encodedPhoto.equals(acc.encodedPhoto);
        } else {
            return false;
        }
    }
}
