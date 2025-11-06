package com.ventura.workspacemongodb.services;

import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.repositories.UserRepository;
import com.ventura.workspacemongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;


    public List<User> findAll() {
        return uRepo.findAll();
    }

    public User findById(String id) throws ObjectNotFoundException {
        Optional<User> user = uRepo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
}
