package com.ventura.workspacemongodb.services;

import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.exceptions.BadRequestException;
import com.ventura.workspacemongodb.exceptions.ObjectNotFoundException;
import com.ventura.workspacemongodb.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository pRepo;

    public List<Post> findAll() {
        if (pRepo.count() == 0) { throw new ObjectNotFoundException("Post collection is empty."); }
        return pRepo.findAll();
    }

    public Post findById(String id) throws ObjectNotFoundException {
        return pRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Tried to find a post that does not exist"));
    }

    public Post insert(Post post) {
        return pRepo.save(post);
    }

    public void delete(String id) throws ObjectNotFoundException {
        Post p  = findById(id);

        if (p == null) {
            throw new ObjectNotFoundException("Tried to delete a post that does not exist.");
        } else pRepo.deleteById(id);
    }

    public Post update(String id, Post post) throws BadRequestException {
        Optional<Post> u = pRepo.findById(id);

        if (u.isPresent()) {
            if (u.get().getAuthorId().equals(post.getAuthorId())) {
                u.get().setTitle(post.getTitle());
                u.get().setBody(post.getBody());
                u.get().setDate(post.getDate());
                return pRepo.save(u.get());
            } else throw new BadRequestException("An user tried to update a post from another user.");
        } else throw new BadRequestException("Tried to update a user that does not exist.");
    }

}
