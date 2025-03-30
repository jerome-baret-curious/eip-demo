package dev.jerome.baret.eipdemo.endpoints;

import dev.jerome.baret.eipdemo.services.UserService;
import org.openapitools.model.CreateUser;
import org.openapitools.model.User;
import org.openapitools.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController implements InputApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_rw_access')")
    public ResponseEntity<Users> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @Override
    public ResponseEntity<User> postUser(CreateUser createUser) {
        User savedUser = this.userService.postUser(createUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }
}
