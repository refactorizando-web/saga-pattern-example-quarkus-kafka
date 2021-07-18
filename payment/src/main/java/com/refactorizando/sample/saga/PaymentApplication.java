package com.refactorizando.sample.saga;

import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.model.User;
import com.refactorizando.sample.saga.repository.SeatRepository;
import com.refactorizando.sample.saga.repository.UserRepository;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;


@QuarkusMain
@Slf4j
public class PaymentApplication {



    public static void main(String... args) {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {


        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");

            log.info("Populate data base");

            var user = new User();
            user.setEmail("refactorizando.web@gmail.com");
            user.setName("noel");
            user.setSurname("rodriguez");


            var seat = new Seat();

            seat.setColumn(1);
            seat.setRow(1);
            seat.setStatus("empty");


            Quarkus.waitForExit();
            return 0;
        }
    }
}