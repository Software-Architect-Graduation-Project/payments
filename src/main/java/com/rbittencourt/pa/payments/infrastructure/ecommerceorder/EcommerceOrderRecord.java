package com.rbittencourt.pa.payments.infrastructure.ecommerceorder;

import java.time.LocalDateTime;

public class EcommerceOrderRecord {

    private long orderId;

    private String newStatus;

    private LocalDateTime updateTime;

    public EcommerceOrderRecord(long orderId, String newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
        this.updateTime = LocalDateTime.now();
    }

    public long getOrderId() {
        return orderId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

}
