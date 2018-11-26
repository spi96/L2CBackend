package com.example.spi.localize2socialize.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {

    private Double locationLatitude;

    private Double locationLongitude;

    private String locationName;

    public Location() {
    }

    public Location(Double locationLatitude, Double locationLongitude, String locationName) {
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.locationName = locationName;
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
