package com.refactorizando.sample.saga.service;

import com.refactorizando.sample.saga.events.compensation.SeatEventProducer;
import com.refactorizando.sample.saga.model.Payment;
import com.refactorizando.sample.saga.repository.PaymentRepository;
import com.refactorizando.sample.saga.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final SeatEventProducer seatEventProducer;

    private final SeatRepository seatRepository;

    @Transactional
    public Payment savePayment(Payment payment) {

        paymentRepository.persist(payment);

        return payment;
    }

    public void deletePayment(Long paymentId) {

        paymentRepository.deleteById(paymentId);
        //Refund money to user
    }

    public Payment findById(Long id) {

        return paymentRepository.findById(id);
    }

    public List<Payment> findAll() {

        return paymentRepository.findAll().stream().collect(Collectors.toList());

    }


}
