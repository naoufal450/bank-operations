package com.octo.bankoperations.dto;

import com.octo.bankoperations.enums.VirementStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class BankTransferDTO implements Serializable {
    private String reference;

    @Pattern(regexp = "\\d+", message = "RIB Should contain digits only")
    @Size(min = 24, max = 24, message = "RIB should be 24 digits long")
    private String senderRIB;

    @Pattern(regexp = "\\d+", message = "RIB Should contain digits only")
    @Size(min = 24, max = 24, message = "RIB should be 24 digits long")
    private String receiverRIB;

    @NotNull(message = "Amount must not be null")
    @Min(value = 1, message = "Amount must be bigger than 0")
    private BigDecimal amount;

    @NotNull(message = "Date d'execution must not be null")
    private Date executionDate;
    private VirementStatus status;
    private Date statusUpdate;

    public BankTransferDTO() {

    }

    public BankTransferDTO(String reference, String senderRIB, String receiverRIB, BigDecimal amount, Date executionDate,
                           VirementStatus status, Date statusUpdate) {
        this.reference = reference;
        this.senderRIB = senderRIB;
        this.receiverRIB = receiverRIB;
        this.amount = amount;
        this.executionDate = executionDate;
        this.status = status;
        this.statusUpdate = statusUpdate;
    }

    public VirementStatus getStatus() {
        return status;
    }

    public void setStatus(VirementStatus status) {
        this.status = status;
    }

    public Date getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(Date statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setSenderRIB(String senderRIB) {
        this.senderRIB = senderRIB;
    }

    public void setReceiverRIB(String receiverRIB) {
        this.receiverRIB = receiverRIB;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public String getSenderRIB() {
        return senderRIB;
    }

    public String getReceiverRIB() {
        return receiverRIB;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    @Override
    public String toString() {
        return "BankTransferDTO{" +
                "reference='" + reference + '\'' +
                ", senderRIB='" + senderRIB + '\'' +
                ", receiverRIB='" + receiverRIB + '\'' +
                ", amount=" + amount +
                ", executionDate=" + executionDate +
                ", status=" + status +
                ", statusUpdate=" + statusUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransferDTO that = (BankTransferDTO) o;
        return Objects.equals(reference, that.reference) &&
                Objects.equals(senderRIB, that.senderRIB) &&
                Objects.equals(receiverRIB, that.receiverRIB) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(executionDate, that.executionDate) &&
                status == that.status &&
                Objects.equals(statusUpdate, that.statusUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, senderRIB, receiverRIB, amount, executionDate, status, statusUpdate);
    }
}
