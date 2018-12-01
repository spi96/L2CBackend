package com.example.spi.localize2socialize.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ownerId", "calId"}))
@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToMany
    @JoinTable(name = "calendar_account",
            joinColumns = {@JoinColumn(name = "calendar_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")})
    private List<Account> participants;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @JsonBackReference
    private Account owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Event> events;

    private Long calId;

    private Date startOfSharing;

    private Date endOfSharing;

    private String displayName;

    public Calendar() {
    }

    public Calendar(Account owner, String displayName,Long calId, Date startOfSharing, Date endOfSharing,
                    List<Account> participants, List<Event> events){
        this.owner = owner;
        this.displayName = displayName;
        this.calId = calId;
        this.startOfSharing = startOfSharing;
        this.endOfSharing = endOfSharing;
        this.participants = participants;
        this.events = events;
    }

    public List<Account> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Account> participants) {
        this.participants = participants;
    }

    public Date getStartOfSharing() {
        return startOfSharing;
    }

    public void setStartOfSharing(Date startOfSharing) {
        this.startOfSharing = startOfSharing;
    }

    public Date getEndOfSharing() {
        return endOfSharing;
    }

    public void setEndOfSharing(Date endOfSharing) {
        this.endOfSharing = endOfSharing;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Long getCalId() {
        return calId;
    }

    public void setCalId(Long calId) {
        this.calId = calId;
    }
}
