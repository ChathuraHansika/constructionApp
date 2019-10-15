package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.EmployeePaymentDetails;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "T_PAY_ROLL_DESCRIPTION")

public class PayRollDescription implements Serializable {
    private static final long serialVersionUID = 4695946347404026561L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_ROLL_DESCRIPTION_ID")
    private Integer payRollDescriptionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAY_ROLL_ID")
    private PayRoll payRoll;

    @Column(name = "SALARY_DESCRIPTION")
    private String salaryDescription;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SALARY_TYPE")
    private String salaryType;

    EmployeePaymentDetails getDetailsDto() {
        EmployeePaymentDetails details = new EmployeePaymentDetails();
        details.setAmount(this.amount.doubleValue());
        details.setDesc(this.salaryDescription);
        details.setDetailID(this.payRollDescriptionId);
        details.setSalaryType(this.salaryType);
        details.setEditable(this.salaryType.equalsIgnoreCase("VARIABLE"));
        details.setStatus(this.status);
        return details;
    }
}
