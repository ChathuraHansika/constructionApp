package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.PayRoll;
import com.akvasoft.construction.entity.hrm.PayRollDescription;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Getter
@Setter
public class EmployeePaymentDetails {
    private int detailID;
    private String desc;
    private double amount;
    private String salaryType;
    private boolean editable = false;
    private String status;

    public EmployeePaymentDetails() {
    }

    public EmployeePaymentDetails(String status, double amount, String desc, String salaryType) {
        if (salaryType.equalsIgnoreCase("VARIABLE")) this.editable = true;
        this.salaryType = salaryType;
        this.desc = desc;
        this.amount = amount;
        this.status = status;
    }

    public PayRollDescription paymentDescription(PayRoll payRoll) {
        PayRollDescription description = new PayRollDescription();
        description.setAmount(BigDecimal.valueOf(this.amount));
        description.setPayRoll(payRoll);
        description.setPayRollDescriptionId(this.detailID);
        description.setSalaryDescription(this.desc);
        description.setSalaryType(this.salaryType);
        description.setStatus(this.status);
        return description;
    }
}
