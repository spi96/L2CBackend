package com.example.spi.localize2socialize.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.jndi.toolkit.url.Uri;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Spi on 2018. 04. 06..
 */
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"personId"}))
@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    private List<Post> posts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    private List<Calendar> sharedCalendars;

    public Account() {}

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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts.clear();
        if(posts != null){
            this.posts.addAll(posts);
        }
    }

    public List<Calendar> getSharedCalendars() {
        return sharedCalendars;
    }

    public void setSharedCalendars(List<Calendar> sharedCalendars) {
        this.sharedCalendars = sharedCalendars;
    }

    public void addCalendar(Calendar calendar){
        if(sharedCalendars == null){
            sharedCalendars = new ArrayList<>();
        }
        sharedCalendars.add(calendar);
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

    @Override
    public int hashCode() {
        return Objects.hash(personGivenName, personFamilyName, personEmail, personName, encodedPhoto);
    }
}
