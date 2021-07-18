package com.refactorizando.sample.saga.usecase;

import com.refactorizando.sample.saga.events.SeatEventProducer;
import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.repository.UserRepository;
import com.refactorizando.sample.saga.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class ReservedSeat {

    @Inject
    private final SeatService seatService;

    @Inject
    private SeatEventProducer seatEventProducer;

    private final UserRepository userRepository;

    public Seat reservedSeat(Seat seat) {

        log.info("Update seat {}", seat.getId());

        var seatToSave = seatService.lockSeat(seat.getId());

        seatEventProducer.sendOrder(seatToSave);

        return seatToSave;
    }



}
