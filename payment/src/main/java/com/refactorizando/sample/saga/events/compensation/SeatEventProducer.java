package com.refactorizando.sample.saga.events.compensation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorizando.sample.saga.model.Seat;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SeatEventProducer {

    @Inject
    @Channel("seats-out")
    Emitter<Record<Long, String>> emitter;

    @SneakyThrows
    public void sendSeatEvent(Seat seat) {
        log.info("Sent event to order service to compensate with seatId {}", seat.getId());

        seat.setType("compensation");

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