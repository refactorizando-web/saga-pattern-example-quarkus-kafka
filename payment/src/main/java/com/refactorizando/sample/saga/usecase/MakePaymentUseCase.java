package com.refactorizando.sample.saga.usecase;

import com.refactorizando.sample.saga.events.PaymentProducer;
import com.refactorizando.sample.saga.events.compensation.SeatEventProducer;
import com.refactorizando.sample.saga.model.Payment;
import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class MakePaymentUseCase {

    private final PaymentService paymentService;

    private final PaymentProducer paymentProducer;

    private final SeatEventProducer seatEventProducer;

    public Payment makeAPayment(Seat seat) {

        log.info("Create payment  with seat  {}", seat.getId());

        var payment  = createPayment(seat);

        try {

            payment.setStatus("PAID");
            paymentService.savePayment(payment);

        }catch (Exception ex) {

            seatEventProducer.sendSeatEvent(payment.getSeat());

            return payment;
        }

        paymentProducer.sendPaymentEvent(payment);

        return payment;
    }

    private Payment createPayment(Seat seat) {

        Payment payment = new Payment();

        payment.setStatus("PAID");
        payment.setAmount(new BigDecimal(10));
        payment.setSeat(seat);
        payment.setUser(seat.getUser());
        payment.setDate(LocalDate.now());

        return payment;
    }
}
