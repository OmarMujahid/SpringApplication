package com.accumed.ae.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
public class ClientConfiguration {
    public final ClientRepository clientRepository;
    @Autowired
    public ClientConfiguration(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
//    @Bean
//    CommandLineRunner cmd() {
//        return args -> {
//            Client X = new Client(
//                    991456L,
//                    "Omar Mujahid"
//
//
//            );
//            clientRepository.save(X);
//        };

//}
    }
