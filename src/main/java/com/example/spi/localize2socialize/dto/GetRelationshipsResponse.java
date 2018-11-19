package com.example.spi.localize2socialize.dto;

import com.example.spi.localize2socialize.domain.Account;

import java.util.List;

public class GetRelationshipsResponse {
    private List<Account> pendingAccounts;
    private List<Account> friends;

    public GetRelationshipsResponse(){}

    public GetRelationshipsResponse(List<Account> pendingAccounts, List<Account> friends) {
        this.pendingAccounts = pendingAccounts;
        this.friends = friends;
    }

    public List<Account> getPendingAccounts() {
        return pendingAccounts;
    }

    public void setPendingAccounts(List<Account> pendingAccounts) {
        this.pendingAccounts = pendingAccounts;
    }

    public List<Account> getFriends() {
        return friends;
    }

    public void setFriends(List<Account> friends) {
        this.friends = friends;
    }
}
