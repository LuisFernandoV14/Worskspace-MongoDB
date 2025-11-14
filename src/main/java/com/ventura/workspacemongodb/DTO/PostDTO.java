package com.ventura.workspacemongodb.DTO;

import com.ventura.workspacemongodb.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
public class PostDTO implements Serializable {

    // Serial Number
    @Serial
    private static final long serialVersionUID = 1L;

    // Attributes
    private String id;
    private LocalDate date;
    private String title;
    private String body;

    private String authorId;

    // Constructors
    public PostDTO (Post post) {
        this.id = post.getId();
        this.date = post.getDate();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.authorId = post.getAuthorId();
    }
    public PostDTO(){}

}

