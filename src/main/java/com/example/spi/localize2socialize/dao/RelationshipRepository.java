package com.example.spi.localize2socialize.dao;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query("SELECT sender FROM Relationship r INNER JOIN r.sender sender WHERE r.receiverPersonId = ?1 AND " +
            "(?2 IS NULL OR r.isPending = ?2)")
    List<Account> findSenderAccounts (String callerPersonId, Boolean pending);

    @Query("SELECT receiver FROM Relationship r INNER JOIN r.receiver receiver WHERE r.senderPersonId = ?1 AND " +
            "(?2 IS NULL OR r.isPending = ?2)")
    List<Account> findReceiverAccounts(String callerPersonId, Boolean pending);

    @Query("SELECT r FROM Relationship r WHERE (r.senderPersonId = ?1 OR r.senderPersonId = ?2) AND " +
                                              "(r.receiverPersonId = ?1 OR r.receiverPersonId = ?2)")
    List<Relationship> findRelationship(String senderPersonId, String receiverPersonId);
}