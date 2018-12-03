package com.example.spi.localize2socialize.persistence;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query("SELECT sender FROM Relationship r INNER JOIN r.sender sender WHERE r.receiver = ?1 AND " +
            "r.pending IS NULL")
    List<Account> findSenderAccounts (Account caller);

    @Query("SELECT receiver FROM Relationship r INNER JOIN r.receiver receiver WHERE r.sender = ?1 AND " +
            "r.pending IS NULL")
    List<Account> findReceiverAccounts(Account caller);

    @Query("SELECT r FROM Relationship r WHERE (r.sender = ?1 OR r.sender = ?2) AND " +
                                              "(r.receiver = ?1 OR r.receiver = ?2)")
    List<Relationship> findRelationship(Account senderPerson, Account receiverPerson);

    @Query("SELECT r.sender FROM Relationship r WHERE (r.receiver = ?1 AND r.pending = false )")
    List<Account> findAllByReceiverIdAndPendingFalse(Account receiver);
    @Query("SELECT r.sender FROM Relationship r WHERE (r.receiver = ?1 AND r.pending = true )")
    List<Account> findAllByReceiverIdAndPendingTrue(Account receiver);
    @Query("SELECT r.receiver FROM Relationship r WHERE (r.sender = ?1 AND r.pending = false )")
    List<Account> findAllBySenderIdAndPendingFalse(Account sender);

    List<Relationship> findAllBySenderPersonIdEqualsAndReceiverPersonIdIn(String callerPersonId, List<String> friendPersonIds);
    List<Relationship> findAllByReceiverPersonIdEqualsAndSenderPersonIdIn(String callerPersonID, List<String> friendPersonIds);
}