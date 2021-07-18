package com.refactorizando.sample.saga;

import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.repository.SeatRepository;
import com.refactorizando.sample.saga.repository.UserRepository;
import com.refactorizando.sample.saga.service.SeatService;
import com.refactorizando.sample.saga.usecase.ReservedSeat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/seats")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class OrderSeatResource {

    @Inject
    private final ReservedSeat service;

    @Inject
    private final UserRepository userRepository;

    @Inject
    private final SeatRepository seatRepository;


    private final SeatService seatService;

    @POST
    public Response orderSeat(Seat seat) {

        log.info("New order received ");

        return Response.status(Response.Status.CREATED)
                .entity(service.reservedSeat(seat)).build();
    }

    @GET
    @Path("/{id}")
    public Response orderSeat(@PathParam("id") Long id) {

        log.info("Seat status by id");

        return Response.status(Response.Status.OK)
                .entity(seatService.findById(id)).build();
    }

}