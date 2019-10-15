package com.akvasoft.construction.entity.accounting;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_SUPPLIER_PAYMENT")
public class SupplierPayment implements Serializable {
    private static final long serialVersionUID = -567714166445400848L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIER_PAYMENT_ID")
    private Integer supplierPaymentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "PAID_DATE")
    private Date paidDate;

    @Column(name = "PAID_AMOUNT")
    private BigDecimal paidAmount;

    public Integer getSupplierPaymentId() {
        return supplierPaymentId;
    }

    public void setSupplierPaymentId(Integer supplierPaymentId) {
        this.supplierPaymentId = supplierPaymentId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "SupplierPayment{" +
                "supplierPaymentId=" + supplierPaymentId +
                ", supplier=" + supplier +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", paidDate=" + paidDate +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
