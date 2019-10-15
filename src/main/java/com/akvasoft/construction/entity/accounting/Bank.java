package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.dto.accounting.BankDto;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_BANK")
public class Bank implements Serializable {

    private static final long serialVersionUID = -8367114082565913719L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_ID")
    private Integer bankId;

    @Column(name = "NAME")
    private String name;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{"
                + "bankId=" + bankId
                + ", name='" + name + '\''
                + '}';
    }

    public BankDto getBankDto() {
        BankDto dto = new BankDto();
        dto.setId(this.bankId);
        dto.setName(this.name);
        return dto;
    }
}
