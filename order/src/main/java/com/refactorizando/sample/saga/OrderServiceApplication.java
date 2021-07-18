package com.refactorizando.sample.saga;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;


@QuarkusMain
@Slf4j
public class OrderServiceApplication {


    public static void main(String... args) {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {


        @Override
        public int run(String... args) throws Exception {
            Quarkus.waitForExit();
            return 0;
        }
    }
}