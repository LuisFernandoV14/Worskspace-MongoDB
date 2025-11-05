package com.ventura.workspacemongodb.services;

import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;

    public List<User> findAll() {
        return uRepo.findAll();
    }
}
