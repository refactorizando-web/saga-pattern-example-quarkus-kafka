package com.refactorizando.sample.saga;

import com.refactorizando.sample.saga.repository.SeatRepository;
import com.refactorizando.sample.saga.repository.UserRepository;
import com.refactorizando.sample.saga.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class PaymentResource {

    private final PaymentService paymentService;

    @GET
    public Response getPayments() {

        log.info("Find All payments");

        return Response.status(Response.Status.OK)
                .entity(paymentService.findAll()).build();
    }

}