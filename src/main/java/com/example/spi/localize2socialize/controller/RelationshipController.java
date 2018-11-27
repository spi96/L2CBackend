package com.example.spi.localize2socialize.controller;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.dto.*;
import com.example.spi.localize2socialize.service.AccountService;
import com.example.spi.localize2socialize.service.RelationshipService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RelationshipController {

    RelationshipService relationshipService;


    public RelationshipController(RelationshipService relationshipService){
        this.relationshipService = relationshipService;
    }

    @RequestMapping(value = "/relationship/friendRequest", method = RequestMethod.POST)
    public Map<String, Boolean> sendFriendRequest(@RequestBody FriendRequest friendRequest) {
        Map<String, Boolean> resultSet = new HashMap<>();
        boolean successful =
                relationshipService.sendFriendRequest(friendRequest.getSenderPersonId(), friendRequest.getReceiverPersonId());

        resultSet.put("friendRequest", successful);
        return resultSet;
    }

    @RequestMapping(value = "/relationship/getRelationships", method = RequestMethod.POST)
    public GetRelationshipsResponse getRelationships(@RequestBody GetRelationshipsRequest relationshipsRequest) {
        String personId = relationshipsRequest.getPersonId();
        List<Account> pendingAccounts = relationshipService.getPendingRelationships(personId);
        List<Account> friends = relationshipService.getFriends(personId);

        GetRelationshipsResponse response = new GetRelationshipsResponse(pendingAccounts, friends);

        return response;
    }

    @RequestMapping(value = "/relationship/handleRequest", method = RequestMethod.POST)
    public Map<String, Account> handleRequest(@RequestBody HandleRelationshipRequest request) {
        Account sender =
                relationshipService.handleFriendRequest(request.getReceiverPersonId(), request.getSenderPersonId(), request.getAccepted());

        Map<String, Account> result = new HashMap<>();
        String key = request.getAccepted() ? "senderPersonAccepted" : "senderPersonDenied";
        result.put(key, sender);
        return result;
    }

    @RequestMapping(value = "/relationship/delete", method = RequestMethod.POST)
    public Map<String, List<Account>> deleteRelationships(@RequestBody DeleteRelationshipsRequest request){
        boolean succssful = relationshipService.deleteRelationships(request.getFriendsToDelete(), request.getCaller());

        Map<String, List<Account>> result = new HashMap<>();
        if(succssful)
            result.put("relationshipDelete", request.getFriendsToDelete());
        else
            result.put("relationshipDelete", new ArrayList<>());
        return result;
    }
}
