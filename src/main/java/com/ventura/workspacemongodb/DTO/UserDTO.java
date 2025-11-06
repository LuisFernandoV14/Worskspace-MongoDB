package com.ventura.workspacemongodb.DTO;

import com.ventura.workspacemongodb.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    // Serial Number
    @Serial
    private static final long serialVersionUID = 1L;

    // Attributes
    private String id;
    private String name;
    private String email;

    // Constructors
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
    public UserDTO() {}



}
