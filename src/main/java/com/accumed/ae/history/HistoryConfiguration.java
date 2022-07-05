package com.accumed.ae.history;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class HistoryConfiguration {

    @Bean
    CommandLineRunner cmd3(HistoryRepository historyRepository){
        return args -> {
//          History a = new History(
//                  "192.168.1.1",
//                  false,
//                  "Forget",
//                  LocalDate.of(2022, Month.APRIL , 20),
//                  "ClientID : 1",
//                  1L,
//                  statusCode);
//          historyRepository.save(a);
        };
    }

}
