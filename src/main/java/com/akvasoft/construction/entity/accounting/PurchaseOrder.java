package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_PURCHASE_ORDER")
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 230234030604387128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_ORDER_ID")
    private Integer purchaseOrderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURCHASE_REQUEST_ID")
    private PurchaseRequest purchaseRequest;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Column(name = "ORDER_VALUE")
    private BigDecimal orderValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_BY")
    private User orderBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
    @Column(name = "COMPLETED_STATUS")
    private DomainConstant.OrderCompletedStatus completedStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.PurchaseStatus status;

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    public User getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(User orderBy) {
        this.orderBy = orderBy;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public DomainConstant.OrderCompletedStatus getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(DomainConstant.OrderCompletedStatus completedStatus) {
        this.completedStatus = completedStatus;
    }

    public DomainConstant.PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.PurchaseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "purchaseOrderId=" + purchaseOrderId +
                ", purchaseRequest=" + purchaseRequest +
                ", orderDate=" + orderDate +
                ", orderValue=" + orderValue +
                ", orderBy=" + orderBy +
                ", supplier=" + supplier +
                ", completedStatus=" + completedStatus +
                ", status=" + status +
                '}';
    }
}
