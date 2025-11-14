package com.ventura.workspacemongodb.mappers;

import com.ventura.workspacemongodb.DTO.CommentDTO;
import com.ventura.workspacemongodb.domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static Comment fromDTO(CommentDTO commentDTO) {
        return new Comment(commentDTO.getId(), commentDTO.getText(), commentDTO.getDate(), commentDTO.getPost(), commentDTO.getAuthorId());
    }

    public static CommentDTO toDTO (Comment comment) {
        return new CommentDTO(comment);
    }
}
