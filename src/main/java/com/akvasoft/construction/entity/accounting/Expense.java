package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_EXPENSE")
public class Expense implements Serializable {
    private static final long serialVersionUID = 809123159059791368L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXPENSE_ID")
    private Integer expenceId;

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

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENTERED_BY")
    private User enteredBy;

    @Column(name = "ENTERED_DATE")
    private Date enteredDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVED_BY")
    private User approvedBy;

    @Column(name = "APPROVED_DATE")
    private Date approvedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAY_BY")
    private User payBy;

    public Integer getExpenceId() {
        return expenceId;
    }

    public void setExpenceId(Integer expenceId) {
        this.expenceId = expenceId;
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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public User getPayBy() {
        return payBy;
    }

    public void setPayBy(User payBy) {
        this.payBy = payBy;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenceId=" + expenceId +
                ", site=" + site +
                ", bankDetail=" + bankDetail +
                ", accountDate=" + accountDate +
                ", amount=" + amount +
                ", accountNo='" + accountNo + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", description='" + description + '\'' +
                ", enteredBy=" + enteredBy +
                ", enteredDate=" + enteredDate +
                ", approvedBy=" + approvedBy +
                ", approvedDate=" + approvedDate +
                ", payBy=" + payBy +
                '}';
    }
}
