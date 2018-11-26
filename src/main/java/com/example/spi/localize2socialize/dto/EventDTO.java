package com.example.spi.localize2socialize.dto;

import java.util.Date;

public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Double locationLatitude;
    private Double locationLongitude;
    private String locationName;


    public EventDTO() {
    }

    public EventDTO(String title, String description, String eventLocation,
                    Double locationLatitude, Double locationLongitude, Date dtStart, Date dtEnd) {
        this.title = title;
        this.description = description;
        this.startDate = dtStart;
        this.endDate = dtEnd;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.locationName = eventLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public Double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
