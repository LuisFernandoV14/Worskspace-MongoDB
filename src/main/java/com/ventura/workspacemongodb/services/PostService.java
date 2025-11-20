package com.ventura.workspacemongodb.services;

import com.ventura.workspacemongodb.domain.Comment;
import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.exceptions.BadRequestException;
import com.ventura.workspacemongodb.exceptions.ObjectNotFoundException;
import com.ventura.workspacemongodb.repositories.CommentRepository;
import com.ventura.workspacemongodb.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository pRepo;
    private final CommentRepository cRepo;

    @Autowired
    public PostService(PostRepository pRepo, CommentRepository cRepo) {
        this.pRepo = pRepo;
        this.cRepo = cRepo;
    }

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

    public List<Comment> findComments(String postId) {
        Post post =  findById(postId);

        if (post.getComments().isEmpty()) {
            throw new ObjectNotFoundException("There is no comments in the post '" + post.getTitle() + "'");
        }

        return post.getComments();
    }

    public Comment findCommentById(String postId, String commentId) {
        Post post =  findById(postId);
        Comment comment = cRepo.findById(commentId).orElseThrow(() -> new ObjectNotFoundException("Tried to find a comment that does not exist"));

        if (comment.getPost().equals(post)) {
            return comment;
        }

        else throw new BadRequestException("The comment with id: " +  commentId + " does not belong to this post.");
    }

    public Comment insertComment(String id, Comment comment) throws BadRequestException {
        Post post = findById(id);

        if (post.getComments().contains(comment)) {
            throw new BadRequestException("Tried to post a comment that already exists");
        }

        comment.setPost(post);
        comment = cRepo.save(comment);

        post.getComments().add(comment);
        update(post.getId(), post);

        return comment;
    }

    public void deleteComment(String postId, String commentId) throws ObjectNotFoundException, BadRequestException {
        Post post = findById(postId);
        Comment comment = findCommentById(postId, commentId);

        cRepo.delete(comment);

        post.getComments().remove(comment);
        update(post.getId(), post);
    }

    public Comment updateComment(String postId, Comment comment) throws ObjectNotFoundException, BadRequestException {
        Comment u = findCommentById(postId, comment.getId());

        if (comment.getAuthorId() != null && !u.getAuthorId().equals(comment.getAuthorId())) {
            throw new BadRequestException("An user tried to update a comment from another user.");
        }

        u.setText(comment.getText());
        u.setDate(comment.getDate());

        return cRepo.save(u);
    }

}
