package com.ventura.workspacemongodb;

import com.ventura.workspacemongodb.DTO.UserDTO;
import com.ventura.workspacemongodb.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(user);
    }
}
