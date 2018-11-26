package com.example.spi.localize2socialize.dto;

public class HandleRelationshipRequest {
    String senderPersonId;
    String receiverPersonId;
    boolean accepted;

    public HandleRelationshipRequest(){}

    public HandleRelationshipRequest(String senderPersonId, String receiverPersonId, boolean accepted) {
        this.senderPersonId = senderPersonId;
        this.receiverPersonId = receiverPersonId;
        this.accepted = accepted;
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

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
