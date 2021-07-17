package com.rbittencourt.pa.payments.application.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbittencourt.pa.payments.infrastructure.payment.Payment;
import com.rbittencourt.pa.payments.infrastructure.payment.PaymentRepository;
import com.rbittencourt.pa.payments.infrastructure.record.OrderRecord;
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
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    public void process(Payment payment) throws JsonProcessingException {
        sendPaymentProcessingStartedEvent(payment);

        paymentRepository.save(payment);

        sendPaymentProcessedEvent(payment);

        logger.info("Payment from order " + payment.getOrderId() + " was processed");
    }

    private void sendPaymentProcessingStartedEvent(Payment payment) throws JsonProcessingException {
        OrderRecord orderRecord = new OrderRecord(payment.getOrderId(), "PROCESSING_PAYMENT");
        String payload = objectMapper.writeValueAsString(orderRecord);

        kafkaTemplate.send("payment_processing_started", payload);
    }

    private void sendPaymentProcessedEvent(Payment payment) throws JsonProcessingException {
        OrderRecord orderRecord = new OrderRecord(payment.getOrderId(), "PAYMENT_PROCESSED");
        String payload = objectMapper.writeValueAsString(orderRecord);

        kafkaTemplate.send("payment_processed", payload);
    }

}
