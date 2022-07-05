package com.accumed.ae.client;

import com.accumed.ae.vc.VerificationCodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Column;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan("com.accumed.ae")
public class ClientApplication {

//    @Autowired
//    ClientRepository clientRepository;
//
//    @Bean
//    public ClientRepository clientRepository(){
//        return clientRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}