package com.example.spi.localize2socialize.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Relationship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_person", nullable = false)
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_person", nullable = false)
    private Account receiver;

    @Column(name = "sender_person_id", nullable = false)
    private String senderPersonId;

    @Column(name = "receiver_person_id", nullable = false)
    private String receiverPersonId;

    @Column(nullable = false)
    private boolean isPending;

    public Relationship(){}

    public Relationship(Account sender, Account receiver, boolean isPending) {
        this.sender = sender;
        this.receiver = receiver;
        this.senderPersonId = sender.getPersonId();
        this.receiverPersonId = receiver.getPersonId();
        this.isPending = isPending;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public String getSenderPersonId() {
        return senderPersonId;
    }

    public void setSenderPersonId(String senderPersonId) {
        this.senderPersonId = senderPersonId;
    }

    public String getReceiverPersonId() {
        return receiverPersonId;
    }

    public void setReceiverPersonId(String receiverPersonId) {
        this.receiverPersonId = receiverPersonId;
    }
}
