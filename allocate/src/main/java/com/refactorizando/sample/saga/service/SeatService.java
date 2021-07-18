package com.refactorizando.sample.saga.service;

import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SeatService {

    private final SeatRepository seatRepository;


    @Transactional(REQUIRES_NEW)
    public void updateSeat(Seat seat) {

        log.info("Block a  seat {}", seat.toString());

        seatRepository.update("status = 'OCCUPIED' where id = ?1", seat.getId());

    }


    public Seat findById(Long id) {

        return seatRepository.findById(id);
    }

}
