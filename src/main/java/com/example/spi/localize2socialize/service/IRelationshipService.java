package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.domain.Account;

import java.util.List;

public interface IRelationshipService {
    List<Account> getFriends(String personId);
    List<Account> getPendingRelationships(String personId);
    boolean sendFriendRequest(String senderPersonId, String receiverPersonId);
    Account handleFriendRequest(String receiverPersonId, String senderPersonId, boolean isAccepted);
}
