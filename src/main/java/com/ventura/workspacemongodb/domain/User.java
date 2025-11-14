package com.ventura.workspacemongodb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @Document
public class User implements Serializable {

    // Serial number
    @Serial
    private static final long serialVersionUID = 3L;

    // Attributes
    @Id
    private String id;
    private String name;
    private String email;

    private List<String> postsId = new ArrayList<>();

    // Constructors
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public User() {
    }

    // HashCode and Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    // ToString
    @Override
    public String toString() {
        return "User: {" +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", posts = " + postsId +
                '}';
    }
}
