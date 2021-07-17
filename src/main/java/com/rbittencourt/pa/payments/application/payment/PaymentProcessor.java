package com.rbittencourt.pa.payments.application.payment;

import com.rbittencourt.pa.payments.infrastructure.payment.Payment;
import com.rbittencourt.pa.payments.infrastructure.payment.PaymentRepository;
import com.rbittencourt.pa.payments.infrastructure.ecommerceorder.EcommerceOrderRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, EcommerceOrderRecord> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    public void process(Payment payment) {
        sendPaymentProcessingStartedEvent(payment);

        paymentRepository.save(payment);

        sendPaymentProcessedEvent(payment);

        logger.info("Payment from order " + payment.getOrderId() + " was processed");
    }

    private void sendPaymentProcessingStartedEvent(Payment payment) {
        EcommerceOrderRecord ecommerceOrderRecord = new EcommerceOrderRecord(payment.getOrderId(), "PROCESSING_PAYMENT");
        kafkaTemplate.send("payment_processing_started", ecommerceOrderRecord);
    }

    private void sendPaymentProcessedEvent(Payment payment) {
        EcommerceOrderRecord ecommerceOrderRecord = new EcommerceOrderRecord(payment.getOrderId(), "PAYMENT_PROCESSED");
        kafkaTemplate.send("payment_processed", ecommerceOrderRecord);
    }

}
