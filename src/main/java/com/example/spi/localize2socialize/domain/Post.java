package com.example.spi.localize2socialize.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ownerId", "startDate"}))
@Entity
@DiscriminatorValue("1")
public class Post extends SharedItem {
    @ManyToOne
    @JoinColumn(name = "ownerId")
    @JsonBackReference
    private Account owner;

    @Column(columnDefinition="NVARCHAR(MAX)")
    private String encodedAttachedImage;

    public Post(){}

    public Post(Location location, Date startDate, String description, Account owner,
                String encodedAttachedImage) {
        super(location, startDate, startDate, description);
        this.owner = owner;
        this.encodedAttachedImage = encodedAttachedImage;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public String getEncodedAttachedImage() {
        return encodedAttachedImage;
    }

    public void setEncodedAttachedImage(String encodedAttachedImage) {
        this.encodedAttachedImage = encodedAttachedImage;
    }
}
