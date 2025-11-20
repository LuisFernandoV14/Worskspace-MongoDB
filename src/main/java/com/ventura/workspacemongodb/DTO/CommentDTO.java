package com.ventura.workspacemongodb.DTO;

import com.ventura.workspacemongodb.domain.Comment;
import com.ventura.workspacemongodb.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
public class CommentDTO implements Serializable {

    // Serial Number
    @Serial
    private static final long serialVersionUID = 1L;

    // Attributes
    private String id;
    private String text;
    private LocalDate date;
    private String authorId;

    private String postId;

    // Constructors
    public CommentDTO (Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.date = comment.getDate();
        this.authorId = comment.getAuthorId();
        this.postId = comment.getPost().getId();
    }
    public CommentDTO () {}
}
