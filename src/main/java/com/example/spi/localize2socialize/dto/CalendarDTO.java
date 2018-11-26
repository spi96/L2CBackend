package com.example.spi.localize2socialize.dto;

import com.example.spi.localize2socialize.domain.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarDTO {
    private Long id;
    private Long calId;
    private String displayName;
    private Account owner;
    private List<Account> participants;
    private List<EventDTO> events;
    private Date startOfSharing;
    private Date endOfSharing;

    public CalendarDTO() {
        events = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
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

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public List<Account> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Account> participants) {
        this.participants = participants;
    }

    public Long getCalId() {
        return calId;
    }

    public void setCalId(Long calId) {
        this.calId = calId;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
