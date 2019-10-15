package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.EmployeePayment;
import com.akvasoft.construction.dto.hrm.EmployeePaymentDetails;
import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "T_PAY_ROLL")
public class PayRoll implements Serializable {
    private static final long serialVersionUID = -2389388821201249602L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_ROLL_ID")
    private Integer payRollId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "PAYMENT_DATE")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "APPROVAL_STATUS")
    private DomainConstant.ApprovalStatus approvalStatus = DomainConstant.ApprovalStatus.PENDING;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVAL_REQUESTED_BY")
    private User requestedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVED_BY")
    private User approvedBy;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payRoll", fetch = FetchType.LAZY)
    private List<PayRollDescription> details = new ArrayList<>();

    public PayRoll() {

    }

    public PayRoll(int payRollId, Employee employee, Date date, String month, String year, boolean approval, User req_by, User approved_by) {
        if (approval) this.approvalStatus = DomainConstant.ApprovalStatus.APPROVED;
        this.approvedBy = approved_by;
        this.date = date;
        this.employee = employee;
        this.month = Integer.parseInt(month);
        this.year = Integer.parseInt(year);
        this.payRollId = payRollId;
        this.requestedBy = req_by;
    }

    public List<EmployeePaymentDetails> getDetailsList() {
        return details.stream().map(PayRollDescription::getDetailsDto).collect(Collectors.toList());
    }

    public EmployeePayment getEmployeePayment() {
        EmployeePayment payment = new EmployeePayment();
        payment.setPayments(this.getDetailsList());
        payment.setPayrollData(this);
        payment.setEmployeeData(this.employee);
        return payment;
    }
}
