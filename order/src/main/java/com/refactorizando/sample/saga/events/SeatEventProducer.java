package com.refactorizando.sample.saga.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorizando.sample.saga.model.Seat;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class SeatEventProducer {


    @Inject
    @Channel("seats-out")
    Emitter<Record<Long, String>> emitter;

    @SneakyThrows
    public void sendOrder(Seat seat) {

        log.info("Event sent {}", seat.getId());

        ObjectMapper objectMapper = new ObjectMapper();

        var seatJson = objectMapper.writeValueAsString(seat);

        emitter.send(Record.of(seat.getId(), seatJson))
                .whenComplete((success, failure) -> {
                    if (failure != null) {
                        log.error("D'oh! " + failure.getMessage());
                    } else {
                        log.info("Message processed successfully");
                    }
                });
    }

}
