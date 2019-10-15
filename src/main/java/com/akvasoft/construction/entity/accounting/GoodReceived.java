package com.akvasoft.construction.entity.accounting;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_GOOD_RECEIVED")
public class GoodReceived implements Serializable {
    private static final long serialVersionUID = -549263798976015172L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOOD_RECEIVE_ID")
    private Integer goodReceiveId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    public Integer getGoodReceiveId() {
        return goodReceiveId;
    }

    public void setGoodReceiveId(Integer goodReceiveId) {
        this.goodReceiveId = goodReceiveId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "GoodReceived{" +
                "goodReceiveId=" + goodReceiveId +
                ", material=" + material +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
