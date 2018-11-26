package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Calendar;
import com.example.spi.localize2socialize.domain.Post;

import java.util.List;

public interface ISharingService {
    boolean shareCalendar(Calendar calendar);
    List<Calendar> collectCalendarsForAccount(Account account);
    List<Post> getPostsByFriends(Account account);
    boolean savePost(Post post);
}
