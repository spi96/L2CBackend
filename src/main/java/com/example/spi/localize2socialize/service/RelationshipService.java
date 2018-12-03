package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Calendar;
import com.example.spi.localize2socialize.domain.Relationship;
import com.example.spi.localize2socialize.persistence.AccountRepository;
import com.example.spi.localize2socialize.persistence.CalendarRepository;
import com.example.spi.localize2socialize.persistence.RelationshipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationshipService implements IRelationshipService {

    RelationshipRepository relationshipRepository;
    AccountRepository accountRepository;
    CalendarRepository calendarRepository;

    public RelationshipService(RelationshipRepository relationshipRepository,
                               AccountRepository accountRepository,
                               CalendarRepository calendarRepository) {
        this.relationshipRepository = relationshipRepository;
        this.accountRepository = accountRepository;
        this.calendarRepository = calendarRepository;
    }

    @Transactional
    @Override
    public List<Account> getFriends(String personId) {
        List<Account> person = accountRepository.findByPersonId(personId);
        if(person.isEmpty())
            return new ArrayList<>();

        List<Account> friends =
                relationshipRepository.findAllByReceiverIdAndPendingFalse(person.get(0));
        friends.addAll(relationshipRepository.findAllBySenderIdAndPendingFalse(person.get(0)));

        return friends;
    }

    @Transactional
    @Override
    public List<Account> getPendingRelationships(String personId) {
        List<Account> person = accountRepository.findByPersonId(personId);
        if(person.isEmpty())
            return new ArrayList<>();

        List<Account> pendingAccounts =
                relationshipRepository.findAllByReceiverIdAndPendingTrue(person.get(0));
        return pendingAccounts;
    }

    @Transactional
    @Override
    public boolean sendFriendRequest(String senderPersonId, String receiverPersonId) {
        List<Account> senderResult = accountRepository.findByPersonId(senderPersonId);
        List<Account> receiverResult = accountRepository.findByPersonId(receiverPersonId);

        List<Relationship> existingRelationShip =
                relationshipRepository.findRelationship(senderResult.get(0), receiverResult.get(0));

        if (!existingRelationShip.isEmpty()) {
            return false;
        } else {
            List<Account> sender = accountRepository.findByPersonId(senderPersonId);
            List<Account> receiver = accountRepository.findByPersonId(receiverPersonId);
            Relationship relationship = new Relationship(sender.get(0), receiver.get(0), true);
            relationshipRepository.save(relationship);
            return true;
        }
    }

    @Transactional
    @Override
    public Account handleFriendRequest(String receiverPersonId, String senderPersonId, boolean isAccepted) {
        List<Account> senderResult = accountRepository.findByPersonId(senderPersonId);
        List<Account> receiverResult = accountRepository.findByPersonId(receiverPersonId);

        List<Relationship> existingRelationShip =
                relationshipRepository.findRelationship(senderResult.get(0), receiverResult.get(0));

        if (existingRelationShip.isEmpty()) {
            return null;
        } else {
            Relationship relationship = existingRelationShip.get(0);
            if (isAccepted) {
                relationship.setPending(false);
                relationshipRepository.save(relationship);
            } else {
                relationshipRepository.delete(relationship);
            }
            Account senderAccount = accountRepository.findByPersonId(senderPersonId).get(0);
            return senderAccount;
        }
    }

    @Transactional
    @Override
    public boolean deleteRelationships(List<Account> toDelete, Account caller) {
        List<Account> searchResult = accountRepository.findByPersonId(caller.getPersonId());
        if (searchResult.isEmpty()) {
            return false;
        }
        List<String> friendsPersonIds = toDelete.stream().map(Account::getPersonId).collect(Collectors.toList());
        Account callerEntity = searchResult.get(0);
        List<Account> friends = accountRepository.findAllByPersonIdIn(friendsPersonIds);
        List<Relationship> sentRelationships =
                relationshipRepository.findAllBySenderPersonIdEqualsAndReceiverPersonIdIn(caller.getPersonId(), friendsPersonIds);
        List<Relationship> receivedRelationships =
                relationshipRepository.findAllByReceiverPersonIdEqualsAndSenderPersonIdIn(caller.getPersonId(), friendsPersonIds);

        List<Calendar> calendarsToDelete = new ArrayList<>();
        for (Relationship r : sentRelationships) {
            List<Calendar> calendars = r.getReceiver().getSharedCalendars();
            deleteParticipants(calendars, callerEntity, r.getReceiver(), calendarsToDelete);
        }

        for (Relationship r : receivedRelationships) {
            List<Calendar> calendars = r.getSender().getSharedCalendars();
            deleteParticipants(calendars, callerEntity, r.getSender(), calendarsToDelete);
        }

        Iterator<Calendar> iterator = callerEntity.getSharedCalendars().iterator();
        while(iterator.hasNext()){
            Calendar cal = iterator.next();
            cal.getParticipants().removeAll(friends);
            if(cal.getParticipants().isEmpty()) {
                cal.getParticipants().clear();
                iterator.remove();
            }
        }
        relationshipRepository.deleteAll(receivedRelationships);
        relationshipRepository.deleteAll(sentRelationships);
        //calendarRepository.saveAll(friendCalendars);

        return true;
    }

    private void deleteParticipants(List<Calendar> calendars, Account caller, Account friend, List<Calendar> toDelete){
        Iterator<Calendar> iterator = calendars.iterator();
        while (iterator.hasNext()){
            Calendar c = iterator.next();
            c.getParticipants().remove(caller);
            if(c.getParticipants().isEmpty()) {
                c.getParticipants().clear();
                iterator.remove();
            }
        }
    }
}


