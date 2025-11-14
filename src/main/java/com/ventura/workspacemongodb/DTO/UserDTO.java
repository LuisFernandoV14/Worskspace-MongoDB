package com.ventura.workspacemongodb.DTO;

import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserDTO implements Serializable {

    // Serial Number
    @Serial
    private static final long serialVersionUID = 3L;

    // Attributes
    private String id;
    private String name;
    private String email;

    private List<String> postsId = new ArrayList<>();

    // Constructors
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.postsId = user.getPostsId();
    }
    public UserDTO() {}



}
