package com.ventura.workspacemongodb.mappers;

import com.ventura.workspacemongodb.DTO.PostDTO;
import com.ventura.workspacemongodb.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public static Post fromDTO (PostDTO postDTO) {
        return new Post(postDTO.getId(), postDTO.getDate(), postDTO.getTitle(), postDTO.getBody(), postDTO.getAuthorId());
    }

    public static PostDTO toDTO (Post post) {
        return new PostDTO(post);
    }

}
