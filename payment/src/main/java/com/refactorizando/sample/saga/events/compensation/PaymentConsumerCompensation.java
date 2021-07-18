package com.refactorizando.sample.saga.events.compensation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorizando.sample.saga.model.Payment;
import com.refactorizando.sample.saga.usecase.DeletePaymentUseCase;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumerCompensation {

    private final DeletePaymentUseCase deletePaymentUseCase;

    private final SeatEventProducer seatEventProducer;

    @SneakyThrows
    @Incoming("allocate-in")
    public void receive(Record<Long, String> record) {

        log.info("Payment compensation with key {}", record.key());

        ObjectMapper objectMapper = new ObjectMapper();

        var payment = objectMapper.readValue(record.value(), Payment.class);

        deletePaymentUseCase.deletePayment(record.key());

        seatEventProducer.sendSeatEvent(payment.getSeat());

    }
}