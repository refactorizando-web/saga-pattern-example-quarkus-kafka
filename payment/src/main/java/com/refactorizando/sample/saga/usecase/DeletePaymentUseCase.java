package com.refactorizando.sample.saga.usecase;

import com.refactorizando.sample.saga.events.compensation.SeatEventProducer;
import com.refactorizando.sample.saga.service.PaymentService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@RequiredArgsConstructor
@ApplicationScoped
public class DeletePaymentUseCase {

    private final PaymentService paymentService;

    private final SeatEventProducer seatEventProducer;

    public void deletePayment(Long paymentId) {

        try {

            paymentService.deletePayment(paymentId);

        } catch (Exception ex) {

            seatEventProducer.sendSeatEvent(paymentService.findById(paymentId).getSeat());

        }
        //Refund money to user

    }
}
