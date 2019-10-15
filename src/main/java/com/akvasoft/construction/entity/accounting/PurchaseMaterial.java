package com.akvasoft.construction.entity.accounting;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_PURCHASE_MATERIAL")
public class PurchaseMaterial implements Serializable {
    private static final long serialVersionUID = -8536042811197456064L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_MATERIAL_ID")
    private Integer purchaseMaterialId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DESCRIPTION")
    private String description;

    public Integer getPurchaseMaterialId() {
        return purchaseMaterialId;
    }

    public void setPurchaseMaterialId(Integer purchaseMaterialId) {
        this.purchaseMaterialId = purchaseMaterialId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PurchaseMaterial{" +
                "purchaseMaterialId=" + purchaseMaterialId +
                ", material=" + material +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
