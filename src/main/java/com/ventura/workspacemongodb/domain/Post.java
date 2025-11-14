package com.ventura.workspacemongodb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @Document
public class Post implements Serializable {

    // Serial number
    @Serial
    private static final long serialVersionUID = 2L;

    // Attributes
    @Id
    private String id;
    private LocalDate date;
    private String title;
    private String body;

    private String authorId;

    @DBRef
    private List<Comment> comments = new ArrayList<>();

    // Constructors
    public Post(String id, LocalDate date, String title, String body, String authorId) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
    }
    public Post() {}

    // HashCode and Equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getAuthorId(), post.getAuthorId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getBody(), getAuthorId());
    }

    // ToString
    @Override
    public String toString() {
        return "Post: {" +
                "id = '" + id + '\'' +
                ", date = " + date +
                ", title = '" + title + '\'' +
                ", body = '" + body + '\'' +
                ", authorId = '" + authorId + '\'' +
                ",  comments = " + comments +
                '}';
    }
}
