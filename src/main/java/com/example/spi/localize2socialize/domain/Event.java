package com.example.spi.localize2socialize.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("2")
public class Event extends SharedItem {
    @ManyToOne
    @JoinColumn(name = "calendarId")
    @JsonBackReference
    private Calendar calendar;

    private String title;

    public Event(){}

    public Event(Location location, Date startDate, Date endDate, String description, Calendar calendar, String title) {
        super(location, startDate, endDate, description);
        this.calendar = calendar;
        this.title = title;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
