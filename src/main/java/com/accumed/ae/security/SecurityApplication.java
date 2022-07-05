package com.accumed.ae.security;

import com.accumed.ae.security.role.Role;
import com.accumed.ae.security.user.User;
import com.accumed.ae.security.user.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner cmd(UserServices userServices){
        return args -> {
          userServices.saveRole(new Role("ROLE_USER"));
          userServices.saveRole(new Role("ROLE_ADMIN"));
          userServices.saveRole(new Role("ROLE_SUPER_ADMIN"));

          userServices.saveUser(new User("Omar","omar","12345",new ArrayList<>()));
          userServices.saveUser(new User("Mariam","mariam","12345",new ArrayList<>()));
          userServices.saveUser(new User("Shoyo","shoyo","12345",new ArrayList<>()));
          userServices.saveUser(new User("Judy","judy","12345",new ArrayList<>()));

          userServices.addRoleToUser("omar", "ROLE_USER");
          userServices.addRoleToUser("mariam", "ROLE_ADMIN");
          userServices.addRoleToUser("judy", "ROLE_ADMIN");
          userServices.addRoleToUser("shoyo", "ROLE_SUPER_ADMIN");
        };

    }
}
