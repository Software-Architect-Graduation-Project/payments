package com.rbittencourt.pa.payments.infrastructure.record;

import java.time.LocalDateTime;

public class OrderRecord {

    private long orderId;

    private String newStatus;

    private LocalDateTime updateTime;

    public OrderRecord(long orderId, String newStatus) {
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
