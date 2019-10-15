package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_GOOD_RECEIVE_NOTE")
public class GoodReceiveNote implements Serializable {
    private static final long serialVersionUID = -7487792129030122213L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOOD_RECEIVE_ID")
    private Integer goodReceiveNoteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURCHASE_ORDER_ID")
    private PurchaseOrder purchaseOrder;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "RECEIVED_DATE")
    private Date receivedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIVED_BY")
    private User receivedBy;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "PAID_AMOUNT")
    private BigDecimal paidAmount;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "COMPLETED_STATUS")
    private DomainConstant.OrderCompletedStatus completedStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    public Integer getGoodReceiveNoteId() {
        return goodReceiveNoteId;
    }

    public void setGoodReceiveNoteId(Integer goodReceiveNoteId) {
        this.goodReceiveNoteId = goodReceiveNoteId;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public User getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(User receivedBy) {
        this.receivedBy = receivedBy;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public DomainConstant.OrderCompletedStatus getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(DomainConstant.OrderCompletedStatus completedStatus) {
        this.completedStatus = completedStatus;
    }

    public DomainConstant.Status getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GoodReceiveNote{" +
                "goodReceiveNoteId=" + goodReceiveNoteId +
                ", purchaseOrder=" + purchaseOrder +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", receivedDate=" + receivedDate +
                ", receivedBy=" + receivedBy +
                ", totalAmount=" + totalAmount +
                ", paidAmount=" + paidAmount +
                ", balanceAmount=" + balanceAmount +
                ", completedStatus=" + completedStatus +
                ", status=" + status +
                '}';
    }
}
