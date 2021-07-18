package com.refactorizando.sample.saga.usecase;

import com.refactorizando.sample.saga.events.AllocateProducer;
import com.refactorizando.sample.saga.events.compensation.PaymentProducerCompensation;
import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class AllocateUseCase {

    private final SeatService seatService;

    private final PaymentProducerCompensation paymentProducerCompensation;

    private final AllocateProducer allocateProducer;


    public Seat updateSeat(Seat seat) {

        log.info("Save seat {}", seat.toString());

        try {
            seatService.updateSeat(seat);

            allocateProducer.sendSeatEvent(seat);

        } catch (Exception ex) {

            paymentProducerCompensation.sendPaymentEvent(seat.getPayment());

        }

        return seatService.findById(seat.getId());

    }


}
