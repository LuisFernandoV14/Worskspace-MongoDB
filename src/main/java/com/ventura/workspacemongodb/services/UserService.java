package com.ventura.workspacemongodb.services;

import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.repositories.UserRepository;
import com.ventura.workspacemongodb.exceptions.BadRequestException;
import com.ventura.workspacemongodb.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository uRepo;

    @Autowired
    public UserService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public List<User> findAll() throws ObjectNotFoundException {
        if (uRepo.count() == 0) { throw new ObjectNotFoundException("User collection is empty."); }
        return uRepo.findAll();
    }

    public User findById(String id) throws ObjectNotFoundException {
        return uRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Tried to find a user that does not exist"));
    }

    public User insert(User user) throws BadRequestException {
        Optional<User> u = uRepo.findByEmail(user.getEmail());

        if (u.isPresent()) {
            throw new BadRequestException("Tried to create a user that already exists.");
        } else return uRepo.save(user);

    }

    public void delete(String id) throws ObjectNotFoundException {
        User u = findById(id);

        if (u == null) {
            throw new ObjectNotFoundException("Tried to delete a user that does not exist.");
        } else uRepo.deleteById(id);
    }

    public User update(String id, User user) throws BadRequestException {
        Optional<User> u = uRepo.findById(id);
        if (u.isPresent()) {
            u.get().setEmail(user.getEmail());
            u.get().setName(user.getName());
            return uRepo.save(u.get());
        } else throw new BadRequestException("Tried to update a user that does not exist.");
    }
}
