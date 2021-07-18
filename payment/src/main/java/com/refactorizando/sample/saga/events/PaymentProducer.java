package com.refactorizando.sample.saga.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorizando.sample.saga.events.compensation.SeatEventProducer;
import com.refactorizando.sample.saga.model.Payment;
import com.refactorizando.sample.saga.usecase.DeletePaymentUseCase;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class PaymentProducer {

    @Channel("payments-out")
    Emitter<Record<Long, String>> emitter;

    private final SeatEventProducer seatEventProducer;

    private final DeletePaymentUseCase deletePaymentUseCase;


    @SneakyThrows
    public void sendPaymentEvent(Payment payment) {
        log.info("Event sent {}", payment.getId());

        ObjectMapper objectMapper = new ObjectMapper();

        var paymentJson = objectMapper.writeValueAsString(payment);

        emitter.send(Record.of(payment.getId(), paymentJson))
                .whenComplete((success, failure) -> {
                    if (failure != null) {
                        log.error("D'oh! " + failure.getMessage());

                        seatEventProducer.sendSeatEvent(payment.getSeat());

                        deletePaymentUseCase.deletePayment(payment.getId());

                    } else {

                        log.info("Message processed successfully");

                    }
                });
    }

}
