package com.ventura.workspacemongodb.mappers;

import com.ventura.workspacemongodb.DTO.CommentDTO;
import com.ventura.workspacemongodb.domain.Comment;
import com.ventura.workspacemongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private static PostService pService = null;

    @Autowired
    public CommentMapper(PostService pService) {
        CommentMapper.pService = pService;
    }

    public static Comment fromDTO(CommentDTO commentDTO) {
        return new Comment(commentDTO.getId(), commentDTO.getText(), commentDTO.getDate(), pService.findById(commentDTO.getId()), commentDTO.getAuthorId());
    }

    public static CommentDTO toDTO (Comment comment) {
        return new CommentDTO(comment);
    }
}
