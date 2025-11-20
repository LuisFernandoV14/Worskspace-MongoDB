package com.ventura.workspacemongodb.resources;

import com.ventura.workspacemongodb.DTO.CommentDTO;
import com.ventura.workspacemongodb.domain.Comment;
import com.ventura.workspacemongodb.mappers.CommentMapper;
import com.ventura.workspacemongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController @RequestMapping(value = "posts/")
public class CommentResource {

    private final PostService pService;

    @Autowired
    public  CommentResource(PostService pService) {
        this.pService = pService;
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentsFromPost(@PathVariable String id) {
        List<CommentDTO> comments = pService.findComments(id).stream().map(CommentMapper::toDTO).toList();

        return ResponseEntity.ok(comments);
    }

    @GetMapping(value = "/{pId}/comments/{cId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable String pId, @PathVariable String cId) {
        CommentDTO commentDTO = CommentMapper.toDTO(pService.findCommentById(pId, cId));
        return ResponseEntity.ok(commentDTO);
    }

    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<CommentDTO> insertComment(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        Comment comment = CommentMapper.fromDTO(commentDTO);
        comment = pService.insertComment(id, comment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{pId}/comments/{cId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String pId, @PathVariable String cId) {
        pService.deleteComment(pId, cId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{pId}/comments/{cId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String pId, @PathVariable String cId, @RequestBody CommentDTO commentDTO) {
        Comment comment = CommentMapper.fromDTO(commentDTO);
        comment.setId(cId);
        comment = pService.updateComment(pId, comment);

        return ResponseEntity.ok(CommentMapper.toDTO(comment));
    }

}
