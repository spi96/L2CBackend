package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Calendar;
import com.example.spi.localize2socialize.domain.Post;
import com.example.spi.localize2socialize.persistence.AccountRepository;
import com.example.spi.localize2socialize.persistence.CalendarRepository;
import com.example.spi.localize2socialize.persistence.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SharingService  implements  ISharingService{

    AccountRepository accountRepository;
    CalendarRepository calendarRepository;
    IRelationshipService relationshipService;
    PostRepository postRepository;


    public SharingService (AccountRepository accountRepository,
                           IRelationshipService relationshipService,
                           CalendarRepository calendarRepository,
                           PostRepository postRepository){
        this.accountRepository = accountRepository;
        this.relationshipService = relationshipService;
        this.calendarRepository = calendarRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    @Override
    public boolean shareCalendar(Calendar calendar){
        List<Account> accounts =
                accountRepository.findByPersonId(calendar.getOwner().getPersonId());
        if(accounts.size() == 0)
            return false;

        Account owner = accounts.get(0);
        boolean exists =
                calendarRepository.existsByCalIdAndOwnerAndDisplayName(calendar.getCalId(), calendar.getOwner(), calendar.getDisplayName());

        if(exists)
            return  false;

        calendar.setOwner(owner);
        owner.addCalendar(calendar);

        calendar.getEvents().forEach(x -> x.setCalendar(calendar));
        accountRepository.save(owner);
        return true;
    }

    @Transactional
    @Override
    public List<Calendar> collectCalendarsForAccount(Account account){
        return calendarRepository.findAllByParticipantsContains(account);
    }

    @Transactional
    @Override
    public List<Post> getPostsByFriends(Account account){
        List<Account> friends = relationshipService.getFriends(account.getPersonId());
        if(friends.isEmpty()){
            return new ArrayList<>();
        }

        return postRepository.findAllByOwnerIn(friends);
    }

    @Transactional
    @Override
    public boolean savePost(Post post){
        Post entity = postRepository.save(post);
        if(entity.getId() == null)
            return false;
        else
            return true;
    }
}
