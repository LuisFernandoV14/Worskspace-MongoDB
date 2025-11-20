package com.ventura.workspacemongodb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter @Document
public class Comment implements Serializable {

    // Serial Number
    @Serial
    private static final long serialVersionUID = 1L;

    // Attributes
    @Id
    private String id;
    private String text;
    private LocalDate date;
    private String authorId;

    @DBRef @JsonBackReference
    private Post post;

    // Constructors
    public Comment(String id, String text, LocalDate date, Post post, String authorId) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.post = post;
        this.authorId = authorId;
    }
    public Comment() {}

    // HashCode and Equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) && Objects.equals(getAuthorId(), comment.getAuthorId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthorId());
    }

    // ToString
    @Override
    public String toString() {
        return "Comment: {" +
                "id = '" + id + '\'' +
                ", text = '" + text + '\'' +
                ", date = " + date +
                ", authorId = '" + authorId + '\'' +
                ", post = " + post +
                '}';
    }
}
