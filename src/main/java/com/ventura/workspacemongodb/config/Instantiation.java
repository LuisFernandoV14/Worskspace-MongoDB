package com.ventura.workspacemongodb.config;

import com.ventura.workspacemongodb.domain.Comment;
import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.repositories.CommentRepository;
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
    private final CommentRepository cRepo;


    @Autowired
    public Instantiation (UserRepository uRepo, PostRepository pRepo, CommentRepository cRepo) {
        this.uRepo = uRepo;
        this.pRepo = pRepo;
        this.cRepo = cRepo;
    }

    @Override
    public void run(String... args) {
        uRepo.deleteAll();
        pRepo.deleteAll();
        cRepo.deleteAll();

        // Create Users
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        // Save users
        uRepo.saveAll(Arrays.asList(maria, alex, bob));

        // Create posts
        Post post1 = new Post(null, LocalDate.of(2025, 3, 31), "I'm going to the beach!", "I'm going to Salvador in December, really excited.", maria.getId());
        Post post2 = new Post(null, LocalDate.of(2025, 3, 23), "Good day!", "Woke up happy today", alex.getId());
        Post post3 = new Post(null, LocalDate.of(2025, 10, 8), "Happy birthday to me!", "Today is my birthday", maria.getId());

        // Save posts
        pRepo.saveAll(Arrays.asList(post1, post2, post3));

        // Link users with posts
        maria.getPostsId().addAll(Arrays.asList(post1.getId(), post3.getId()));
        alex.getPostsId().add(post2.getId());

        // Save users with posts
        uRepo.saveAll(Arrays.asList(maria, alex));

        // Create comments
        Comment comment1 = new Comment(null, "Hope you have a nice trip!", LocalDate.of(2025, 4, 1), post1, bob.getId());
        Comment comment2 = new Comment(null, "Really? I'm also going to Salvador in December, what a coincidence!", LocalDate.of(2025, 4, 2), post1, alex.getId());
        Comment comment3 = new Comment(null, "Good day!", LocalDate.of(2025, 3, 23), post2, bob.getId());

        // Save comments
        cRepo.saveAll(Arrays.asList(comment1, comment2, comment3));

        // Link posts with comments
        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().add(comment3);

        // Save posts with comments
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
