package com.rbittencourt.pa.payments.application.payments;

import com.rbittencourt.pa.payments.infrastructure.ecommerceorder.EcommerceOrder;
import com.rbittencourt.pa.payments.infrastructure.payment.Payment;
import com.rbittencourt.pa.payments.infrastructure.payment.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, EcommerceOrder> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    @KafkaListener(topics = "payment_received", containerFactory = "paymentKafkaListenerContainerFactory")
    public void process(EcommerceOrder ecommerceOrder) {
        kafkaTemplate.send("payment_processing_started", ecommerceOrder);

        Payment payment = new Payment(ecommerceOrder.getClientId(), ecommerceOrder.getId(), ecommerceOrder.getPaymentPlan());
        paymentRepository.save(payment);

        kafkaTemplate.send("payment_processed", ecommerceOrder);

        logger.info("Payment from order " + ecommerceOrder.getId() + " was processed");
    }

}
