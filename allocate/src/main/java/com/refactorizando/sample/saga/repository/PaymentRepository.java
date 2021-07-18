package com.refactorizando.sample.saga.repository;

import com.refactorizando.sample.saga.model.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
}
