package com.akvasoft.construction.dto.accounting;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankDetailDto {
    private Integer bankDetailId;
    private Integer bankId;
    private String bank;
    private String branch;
    private String accountNumber;
    private String accountName;
    private Integer siteId;
    private String site;
    private String type;
    private String status;
    private BigDecimal balance;
}
