package com.example.spi.localize2socialize.controller;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Calendar;
import com.example.spi.localize2socialize.domain.Post;
import com.example.spi.localize2socialize.dto.CalendarDTO;
import com.example.spi.localize2socialize.dto.GetSharingsForAccountResponse;
import com.example.spi.localize2socialize.dto.PostDTO;
import com.example.spi.localize2socialize.service.IAccountService;
import com.example.spi.localize2socialize.service.ISharingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SharingController {

    ISharingService sharingService;
    IAccountService accountService;

    public SharingController(ISharingService sharingService,
                             IAccountService accountService){
        this.sharingService = sharingService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/sharings/shareCalendar", method = RequestMethod.POST)
    public Map<String, Boolean> shareCalendar(@RequestBody CalendarDTO shareRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Calendar calendar = modelMapper.map(shareRequest, Calendar.class);
        boolean successful = sharingService.shareCalendar(calendar);

        Map<String, Boolean> result = new HashMap<>();
        result.put("successful",successful);
        return result;
    }

    @RequestMapping(value = "/sharings/map", method = RequestMethod.POST)
    public GetSharingsForAccountResponse getSharingsForAccount(@RequestBody Account account){
        GetSharingsForAccountResponse response = new GetSharingsForAccountResponse();
        Account entity = accountService.getAccount(account.getPersonId());

        if(entity == null)
            return response;

        List<Calendar> calendars = sharingService.collectCalendarsForAccount(entity);
        List<Post> posts = sharingService.getPostsByFriends(entity);

        ModelMapper modelMapper = new ModelMapper();
        Type calendarListType = new TypeToken<List<CalendarDTO>>(){}.getType();
        Type postListType = new TypeToken<List<PostDTO>>(){}.getType();
        List<CalendarDTO> calendarDTO = modelMapper.map(calendars, calendarListType);
        List<PostDTO> postDTO = modelMapper.map(posts, postListType);

        response.setCalendars(calendarDTO);
        response.setPosts(postDTO);
        return response;
    }

    @RequestMapping(value = "/sharings/post", method = RequestMethod.POST)
    public Map<String, Boolean> savePost(@RequestBody PostDTO post){
        ModelMapper modelMapper = new ModelMapper();
        Post postEntity = modelMapper.map(post, Post.class);

        boolean success = sharingService.savePost(postEntity);

        Map<String, Boolean> response = new HashMap<>();
        response.put("postResponse", success);
        return response;
    }

}
