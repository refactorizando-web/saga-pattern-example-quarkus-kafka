package com.refactorizando.sample.saga.service;

import com.refactorizando.sample.saga.events.PaymentProducer;
import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SeatService {

    @Inject
    private final SeatRepository seatRepository;

    @Inject
    private final PaymentProducer paymentProducer;

    @Transactional(REQUIRES_NEW)
    public Seat blockSeat(Seat seat) {

        log.info("Block a  seat ", seat.toString());

        seat.setStatus("BLOCKED");

        seatRepository.persistAndFlush(seat);

        return seat;
    }


}
