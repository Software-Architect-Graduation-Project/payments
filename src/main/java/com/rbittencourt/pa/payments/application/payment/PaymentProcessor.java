package com.rbittencourt.pa.payments.application.payment;

import com.rbittencourt.pa.payments.infrastructure.payment.Payment;
import com.rbittencourt.pa.payments.infrastructure.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    @Autowired
    private PaymentRepository paymentRepository;

    public void process(Payment payment) {
        paymentRepository.save(payment);
    }

}
