package com.refactorizando.sample.saga.service;

import com.refactorizando.sample.saga.events.compensation.PaymentProducerCompensation;
import com.refactorizando.sample.saga.model.Payment;
import com.refactorizando.sample.saga.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentProducerCompensation paymentProducerCompensation;

    @Transactional
    public Payment savePayment(Payment payment) {

        try {

            paymentRepository.persistAndFlush(payment);

        } catch (Exception ex) {

            paymentProducerCompensation.sendPaymentEvent(payment);

        }

        return payment;
    }

    public void deletePayment(Long paymentId) {

        try {
            paymentRepository.deleteById(paymentId);

        } catch (Exception ex) {

            paymentProducerCompensation.sendPaymentEvent(paymentRepository.findById(paymentId));

        }
        //Refund money to user

    }


}
