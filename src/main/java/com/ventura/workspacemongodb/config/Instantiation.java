package com.ventura.workspacemongodb.config;

import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository uRepo;

    @Override
    public void run(String... args) throws Exception {
        uRepo.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        uRepo.saveAll(Arrays.asList(maria, alex, bob));
    }
}

/* What is POJO?
    * Without a connotation, a class does not exist in Spring.
    * Without a connotation, a class is just a Plain Old Java Object (POJO).
    * In this class I struggled with @Autowired and couldn't make it work,
    * That was because I forgot the connotation @Configuration

    * * Some useful connotations to start classes:
        * @SpringBootApplication - Works as main;
        * @Component - Says, "This is a bean" to Spring, the simplest one;
        * @Service - Specialized version of @Component, serves as main business class logic;
        * @Repository - Says, "This class talks to the database";
        * @Autowired - Dependency Injection, auto instantiates something;
*/
