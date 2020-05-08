package com.octo.bankoperations.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class CordaIntraBankTransferDTO implements Serializable {
    private String senderRIB;

    private String receiverRIB;

    private String bank;

    private long amount;

    private String currency;

    private Date executionDate;

    private String externalId;

    private UUID linearId;

    public CordaIntraBankTransferDTO(String senderRIB, String receiverRIB, String bank, long amount, String currency, Date executionDate,
                                     String externalId, UUID linearId) {
        this.senderRIB = senderRIB;
        this.receiverRIB = receiverRIB;
        this.bank = bank;
        this.amount = amount;
        this.currency = currency;
        this.executionDate = executionDate;
        this.externalId = externalId;
        this.linearId = linearId;
    }

    public CordaIntraBankTransferDTO() {
    }

    public String getSenderRIB() {
        return senderRIB;
    }

    public String getReceiverRIB() {
        return receiverRIB;
    }

    public String getBank() {
        return bank;
    }

    public long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public String getExternalId() {
        return externalId;
    }

    public UUID getLinearId() {
        return linearId;
    }

    public void setSenderRIB(String senderRIB) {
        this.senderRIB = senderRIB;
    }

    public void setReceiverRIB(String receiverRIB) {
        this.receiverRIB = receiverRIB;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setLinearId(UUID linearId) {
        this.linearId = linearId;
    }
}