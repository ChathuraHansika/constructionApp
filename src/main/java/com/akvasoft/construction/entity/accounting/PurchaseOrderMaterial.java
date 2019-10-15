package com.akvasoft.construction.entity.accounting;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_PURCHASE_ORDER_MATERIAL")
public class PurchaseOrderMaterial implements Serializable {
    private static final long serialVersionUID = -8013629116699067610L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_ORDER_MATERIAL_ID")
    private Integer orderMaterialId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "RECEIVED_QUANTITY")
    private Integer recivedQuantity;

    public Integer getOrderMaterialId() {
        return orderMaterialId;
    }

    public void setOrderMaterialId(Integer orderMaterialId) {
        this.orderMaterialId = orderMaterialId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public Integer getRecivedQuantity() {
        return recivedQuantity;
    }

    public void setRecivedQuantity(Integer recivedQuantity) {
        this.recivedQuantity = recivedQuantity;
    }

    @Override
    public String toString() {
        return "PurchaseOrderMaterial{" +
                "orderMaterialId=" + orderMaterialId +
                ", material=" + material +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", recivedQuantity=" + recivedQuantity +
                '}';
    }
}
