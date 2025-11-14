package com.ventura.workspacemongodb.resources;

import com.ventura.workspacemongodb.DTO.PostDTO;
import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.mappers.PostMapper;
import com.ventura.workspacemongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController @RequestMapping(value = "/posts")
public class PostResource {

    private final PostService pService;

    @Autowired
    public PostResource(PostService pService) {
        this.pService = pService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = pService.findAll().stream().map(PostMapper::toDTO).toList();
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String id) {
        PostDTO post = PostMapper.toDTO(pService.findById(id));
        return ResponseEntity.ok(post);
    }

    @RequestMapping
    public ResponseEntity<Void> insertPost (@RequestBody PostDTO postDTO) {
        Post post = PostMapper.fromDTO(postDTO);
        post = pService.insert(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        pService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable String id, @RequestBody PostDTO postDTO) {
        Post post = pService.update(id, PostMapper.fromDTO(postDTO));

        return ResponseEntity.ok(PostMapper.toDTO(post));
    }

}
