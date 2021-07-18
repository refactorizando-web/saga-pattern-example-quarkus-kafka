package com.refactorizando.sample.saga.repository;

import com.refactorizando.sample.saga.model.Seat;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeatRepository implements PanacheRepository<Seat> {


}
