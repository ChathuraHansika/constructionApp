package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.LeaveTypeDto;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "T_LEAVE_TYPE")
public class LeaveType implements Serializable {
    private static final long serialVersionUID = -634033041930441078L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAVE_TYPE_ID")
    private Integer leaveTypeId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DAY_PORTION")
    private BigDecimal dayPortion;

    @Column(name = "REDUCT_FROM")
    private String reductFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    public LeaveTypeDto getLeaveTypeDto() {
        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setDay_portion(this.dayPortion.doubleValue());
        dto.setId(this.leaveTypeId);
        dto.setReduct_from(this.reductFrom);
        dto.setStatus(this.status.name());
        dto.setType(this.type);
        return dto;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LeaveType{");
        sb.append("leaveTypeId=").append(leaveTypeId);
        sb.append(", type='").append(type).append('\'');
        sb.append(", dayPortion=").append(dayPortion);
        sb.append(", reductFrom='").append(reductFrom).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
