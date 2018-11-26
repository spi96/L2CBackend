package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.dao.AccountRepository;
import com.example.spi.localize2socialize.dao.RelationshipRepository;
import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RelationshipService implements IRelationshipService {

    RelationshipRepository relationshipRepository;
    AccountRepository accountRepository;

    public RelationshipService(RelationshipRepository relationshipRepository,
                               AccountRepository accountRepository) {
        this.relationshipRepository = relationshipRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public List<Account> getFriends(String personId) {
        List<Account> friends =
                relationshipRepository.findReceiverAccounts(personId, false);
        friends.addAll(relationshipRepository.findSenderAccounts(personId, false));

        return friends;
    }

    @Transactional
    @Override
    public List<Account> getPendingRelationships(String personId) {
        List<Account> pendingAccounts =
                relationshipRepository.findSenderAccounts(personId, true);
        return pendingAccounts;
    }

    @Transactional
    @Override
    public boolean sendFriendRequest(String senderPersonId, String receiverPersonId) {
        List<Relationship> existingRelationShip =
                relationshipRepository.findRelationship(senderPersonId, receiverPersonId);

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
        List<Relationship> existingRelationShip =
                relationshipRepository.findRelationship(senderPersonId, receiverPersonId);

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


}


