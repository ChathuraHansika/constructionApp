package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_MATERIAL_DAILY_USAGE")
public class MaterialDailyUsage implements Serializable {
    private static final long serialVersionUID = -5557013947328496372L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIAL_USAGE_ID")
    private Integer materialUsageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @Column(name = "MATERIAL_USAGE")
    private BigDecimal materialUsage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MARKED_BY")
    private User markedBy;

    @Column(name = "MARKED_DATE")
    private Date marketDate;

    public Integer getMaterialUsageId() {
        return materialUsageId;
    }

    public void setMaterialUsageId(Integer materialUsageId) {
        this.materialUsageId = materialUsageId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BigDecimal getMaterialUsage() {
        return materialUsage;
    }

    public void setMaterialUsage(BigDecimal materialUsage) {
        this.materialUsage = materialUsage;
    }

    public User getMarkedBy() {
        return markedBy;
    }

    public void setMarkedBy(User markedBy) {
        this.markedBy = markedBy;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    @Override
    public String toString() {
        return "MaterialDailyUsage{" +
                "materialUsageId=" + materialUsageId +
                ", material=" + material +
                ", materialUsage=" + materialUsage +
                ", markedBy=" + markedBy +
                ", marketDate=" + marketDate +
                '}';
    }
}
