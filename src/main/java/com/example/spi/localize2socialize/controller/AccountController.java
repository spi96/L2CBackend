package com.example.spi.localize2socialize.controller;

import com.example.spi.localize2socialize.dao.AccountRepository;
import com.example.spi.localize2socialize.dao.RelationshipRepository;
import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Relationship;
import com.example.spi.localize2socialize.dto.FriendRequest;
import com.example.spi.localize2socialize.dto.GetRelationshipsRequest;
import com.example.spi.localize2socialize.dto.GetRelationshipsResponse;
import com.example.spi.localize2socialize.dto.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RelationshipRepository relationshipRepository;

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Long>> saveOrUpdateAccount(@RequestBody Account account) {
        Account entity = null;
        List<Account> result = accountRepository.findByPersonId(account.getPersonId());

        if (result.isEmpty()) {
            entity = accountRepository.save(account);
        } else {
            entity = result.get(0);
            if (!result.get(0).equals(account)) {
                entity.setPersonEmail(account.getPersonEmail());
                entity.setPersonFamilyName(account.getPersonFamilyName());
                entity.setPersonGivenName(account.getPersonGivenName());
                entity.setPersonName(account.getPersonName());
                entity.setEncodedPhoto(account.getEncodedPhoto());
                accountRepository.save(entity);
            }
        }
        Map<String, Long> response = new HashMap<>();
        response.put("accountId", entity.getId());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public Map<String, List<Account>> findAccounts(@RequestBody SearchRequest searchRequest) {
        List<Account> result = accountRepository.findAccountsByNamePattern(searchRequest.getFilter(),
                searchRequest.getCallerPerson().getPersonId());

        List<Account> sentRequestsByPerson =
                relationshipRepository.findReceiverAccounts(searchRequest.getCallerPerson().getPersonId(), null);
        List<Account> receivedRequestsByPerson =
                relationshipRepository.findSenderAccounts(searchRequest.getCallerPerson().getPersonId(),null);

        result.removeIf(a -> sentRequestsByPerson.contains(a) || receivedRequestsByPerson.contains(a));

        Map<String, List<Account>> resultSet = new HashMap<>();

        resultSet.put("accounts", result);
        return resultSet;
    }

    @RequestMapping(value = "/users/friendRequest", method = RequestMethod.POST)
    public Map<String, String> sendFriendRequest(@RequestBody FriendRequest friendRequest) {
        List<Relationship> existingRelationShip =
                relationshipRepository.findRelationship(friendRequest.getSenderPersonId(), friendRequest.getReceiverPersonId());
        Map<String, String> resultSet = new HashMap<>();

        if (!existingRelationShip.isEmpty())
            resultSet.put("friendRequest", "exists");
        else {
            List<Account> sender = accountRepository.findByPersonId(friendRequest.getSenderPersonId());
            List<Account> receiver = accountRepository.findByPersonId(friendRequest.getReceiverPersonId());
            Relationship relationship = new Relationship(sender.get(0), receiver.get(0), true);
            relationshipRepository.save(relationship);
            resultSet.put("friendRequest", "sent");
        }
        return resultSet;
    }

    @RequestMapping(value = "/users/getRelationships", method = RequestMethod.POST)
    public GetRelationshipsResponse getRelationships(@RequestBody GetRelationshipsRequest relationshipsRequest) {
        List<Account> pendingAccounts =
                relationshipRepository.findSenderAccounts(relationshipsRequest.getPersonId(), true);
        List<Account> friends =
                relationshipRepository.findReceiverAccounts(relationshipsRequest.getPersonId(), false);
        friends.addAll(relationshipRepository.findSenderAccounts(relationshipsRequest.getPersonId(), false));

        GetRelationshipsResponse response = new GetRelationshipsResponse(pendingAccounts, friends);

        return response;
    }

}
