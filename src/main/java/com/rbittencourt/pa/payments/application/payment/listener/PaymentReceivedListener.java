package com.rbittencourt.pa.payments.application.payment.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbittencourt.pa.payments.application.payment.PaymentProcessor;
import com.rbittencourt.pa.payments.infrastructure.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentReceivedListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentProcessor paymentProcessor;

    @KafkaListener(topics = "payment_received", groupId = "group")
    public void receiveNewPayment(String message) throws JsonProcessingException {
        Payment payment = objectMapper.readValue(message, Payment.class);
        paymentProcessor.process(payment);
    }

}
