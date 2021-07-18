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
    public Seat lockSeat(Long id) {

        log.info("Update seat with id", id);

        seatRepository.update("status = 'LOCKED' where id = ?1", id);

        return seatRepository.findById(id);
    }

    @Transactional(REQUIRES_NEW)
    public Seat unlockSeat (Long id) {

        log.info("Update seat with id", id);

        seatRepository.update("status = 'FREE' where id = ?1", id);

        return seatRepository.findById(id);
    }

    @Transactional(REQUIRES_NEW)
    public Seat busySeat (Long id) {

        log.info("Update seat with id", id);

        seatRepository.update("status = 'BUSY' where id = ?1", id);

        return seatRepository.findById(id);
    }

    public Seat findById(Long id) {

        return  seatRepository.findById(id);
    }

    public Seat findFree() {

        return seatRepository.find("status= 'FREE' ").firstResult();
    }

}
