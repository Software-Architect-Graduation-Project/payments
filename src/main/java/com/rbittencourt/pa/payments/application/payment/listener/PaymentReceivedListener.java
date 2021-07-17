package com.rbittencourt.pa.payments.application.payment.listener;

import com.rbittencourt.pa.payments.application.payment.PaymentProcessor;
import com.rbittencourt.pa.payments.infrastructure.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentReceivedListener {

    @Autowired
    private PaymentProcessor paymentProcessor;

    @KafkaListener(topics = "payment_received", containerFactory = "paymentKafkaListenerContainerFactory")
    public void receiveNewPayment(Payment payment) {
        paymentProcessor.process(payment);
    }

}
