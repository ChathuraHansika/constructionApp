package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_MATERIAL")
public class Material implements Serializable {
    private static final long serialVersionUID = -8220086878832723133L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIAL_ID")
    private Integer materialId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STOCK")
    private BigDecimal stock;

    @Column(name = "UNIT")
    private String unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public DomainConstant.Status getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", unit='" + unit + '\'' +
                ", status=" + status +
                '}';
    }
}
