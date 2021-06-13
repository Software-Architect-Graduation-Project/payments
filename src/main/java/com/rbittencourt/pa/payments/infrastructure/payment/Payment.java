package com.rbittencourt.pa.payments.infrastructure.payment;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "payment_plan")
    private String paymentPlan;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public Payment() {
        createdOn = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

}
