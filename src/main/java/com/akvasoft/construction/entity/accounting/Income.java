package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_INCOME")
public class Income implements Serializable {
    private static final long serialVersionUID = -6711680097770324363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INCOME_ID")
    private Integer incomeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ID")
    private ConstructionSite site;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_DETAIL_ID")
    private BankDetail bankDetail;

    @Column(name = "ACCOUNT_DATE")
    private Date accountDate;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "ACCOUNT_NO")
    private String accountNo;

    @Column(name = "REF_NO")
    private String refNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENTERED_BY")
    private User enteredBy;

    @Column(name = "ENTERED_DATE")
    private Date enteredDate;

    @Column(name = "ISSUE_DATE")
    private Date issueDate;

    @Column(name = "RECEIVED_DATE")
    private Date receivedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_MODE")
    private DomainConstant.PaymentMode paymentMode;

    @Column(name = "CHEQUE_NUMBER")
    private String chequeNumber;

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public ConstructionSite getSite() {
        return site;
    }

    public void setSite(ConstructionSite site) {
        this.site = site;
    }

    public BankDetail getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(User enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public DomainConstant.PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(DomainConstant.PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", site=" + site +
                ", bankDetail=" + bankDetail +
                ", accountDate=" + accountDate +
                ", amount=" + amount +
                ", accountNo='" + accountNo + '\'' +
                ", refNo='" + refNo + '\'' +
                ", description='" + description + '\'' +
                ", enteredBy=" + enteredBy +
                ", enteredDate=" + enteredDate +
                ", issueDate=" + issueDate +
                ", receivedDate=" + receivedDate +
                ", paymentMode=" + paymentMode +
                ", chequeNumber='" + chequeNumber + '\'' +
                '}';
    }
}
