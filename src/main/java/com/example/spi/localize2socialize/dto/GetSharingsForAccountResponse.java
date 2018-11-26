package com.example.spi.localize2socialize.dto;

import java.util.ArrayList;
import java.util.List;

public class GetSharingsForAccountResponse {

    List<CalendarDTO> calendars;
    List<PostDTO> posts;

    public GetSharingsForAccountResponse() {
        calendars = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public GetSharingsForAccountResponse(List<CalendarDTO> calendars, List<PostDTO> posts) {
        this.calendars = calendars;
        this.posts = posts;
    }

    public List<CalendarDTO> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<CalendarDTO> calendars) {
        this.calendars = calendars;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
