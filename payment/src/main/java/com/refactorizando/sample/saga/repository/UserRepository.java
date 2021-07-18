package com.refactorizando.sample.saga.repository;

import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {


}
