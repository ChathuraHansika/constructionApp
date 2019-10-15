package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_BANK_DETAIL")
public class BankDetail implements Serializable {
    private static final long serialVersionUID = 809123159059791368L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_DETAIL_ID")
    private Integer bankDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "ACCOUNT_NO")
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ID")
    private ConstructionSite site;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private DomainConstant.BankAccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS")
    private DomainConstant.Status status;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    public Integer getBankDetailId() {
        return bankDetailId;
    }

    public void setBankDetailId(Integer bankDetailId) {
        this.bankDetailId = bankDetailId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public ConstructionSite getSite() {
        return site;
    }

    public void setSite(ConstructionSite site) {
        this.site = site;
    }

    public DomainConstant.BankAccountType getType() {
        return type;
    }

    public void setType(DomainConstant.BankAccountType type) {
        this.type = type;
    }

    public DomainConstant.Status getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.Status status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String
    toString() {
        return "BankDetail{" +
                "bankDetailId=" + bankDetailId +
                ", bank=" + bank +
                ", branch='" + branch + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                ", site=" + site +
                ", type=" + type +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}
