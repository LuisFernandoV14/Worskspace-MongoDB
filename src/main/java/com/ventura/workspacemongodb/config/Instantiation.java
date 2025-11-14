package com.ventura.workspacemongodb.config;

import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.repositories.PostRepository;
import com.ventura.workspacemongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final UserRepository uRepo;
    private final PostRepository pRepo;


    @Autowired
    public Instantiation (UserRepository uRepo, PostRepository pRepo) {
        this.uRepo = uRepo;
        this.pRepo = pRepo;
    }

    @Override
    public void run(String... args) {
        uRepo.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        uRepo.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, LocalDate.of(2025, 3, 31), "I'm going to the beach!", "I'm going to Salvador in December, really excited.", maria.getId());
        Post post2 = new Post(null, LocalDate.of(2025, 3, 23), "Good day!", "Woke up happy today", alex.getId());
        Post post3 = new Post(null, LocalDate.of(2025, 10, 8), "Happy birthday to me!", "Today is my birthday", maria.getId());

        pRepo.saveAll(Arrays.asList(post1, post2, post3));
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
