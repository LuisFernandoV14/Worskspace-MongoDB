package com.ventura.workspacemongodb.resources;

import com.ventura.workspacemongodb.DTO.UserDTO;
import com.ventura.workspacemongodb.domain.Post;
import com.ventura.workspacemongodb.mappers.UserMapper;
import com.ventura.workspacemongodb.domain.User;
import com.ventura.workspacemongodb.services.PostService;
import com.ventura.workspacemongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping(value = "/users")
public class UserResource {

    private final UserService uService;
    private final PostService pService;

    @Autowired
    public UserResource(UserService uService,  PostService pService) {
        this.uService = uService;
        this.pService = pService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = uService.findAll().stream().map(UserMapper::toDTO).toList();
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        UserDTO userDTO = UserMapper.toDTO(uService.findById(id));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.fromDTO(userDTO);
        user =  uService.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        uService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = uService.update(id, UserMapper.fromDTO(userDTO));

        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable String id) {
        User user = uService.findById(id);
        List<Post> posts = new ArrayList<>();

        user.getPostsId().forEach(x -> posts.add(pService.findById(x)));

        return ResponseEntity.ok(posts);
    }

}

/* What is a Rest API?
    * It is an API that sends and receives data, usually as JSON
 */

/* What is @RestController?
    * The @RestController annotation is a specialized version of @Controller
    * It is used to handle web requests (creates RESTful web services).
    * It means that methods with this tag will return data directly in the HTTP response body.
    * Meaning it won't render a view, it will return data in the HTTP
    * Typically it returns data in JSON or XML format.
 */

/* What is @RequestMapping?
    * Think of it as the address or URL path used by the methods.
    * You only use it in classes with @RestController
    * It tells Spring "When a web request comes for a specific URL (in this case "/users") run this specific method".
    * However, you can also use it in classes to create a base path for all methods inside it.
 */

/* What is @GetMapping?
    * @GetMapping is a shortcut annotation.
    * It is a specialized version of @RequestMapping that is only for handling HTTP GET requests
    * (GET requests are used when you want to fetch or read data from the server, like loading a web page)
    * The entire annotation: @RequestMapping(value = "/users", method = RequestMethod.GET)
 */

/* What is ResponseEntity<T>?
    * It is a Spring class that gives full control over the HTTP response returned.
    * If you return an object, Spring makes some guesses for you, codes that explain if the request was successful (HTTP 200 (OK))
    * However, if the request isn't successful, it won't return any code.
    * That's why we use ResponseEntity, it is a kind of package that contains the object and has other useful information as labels, such as
        * "NOT FOUND (404)", "CREATED (201)", "BAD REQUEST (400)", "OK (200)"
 */
