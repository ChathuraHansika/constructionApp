package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.dto.accounting.BankDetailDto;
import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
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

    public BankDetailDto getBankDetailDto(BankDetail bankDetail) {
        BankDetailDto dto = new BankDetailDto();
        dto.setAccountName(bankDetail.accountName);
        dto.setAccountNumber(bankDetail.accountNumber);
        dto.setBalance(bankDetail.balance);
        dto.setBankDetailId(bankDetail.bankDetailId);
        dto.setBranch(bankDetail.branch);
        dto.setSiteId(bankDetail.site.getSiteId());
        dto.setStatus(bankDetail.status.toString());
        dto.setType(bankDetail.type.toString());
        dto.setBankId(bankDetail.bank.getBankId());
        return dto;
    }


}
