package practica.mocky.practica2pwa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practica.mocky.practica2pwa.models.User;
import practica.mocky.practica2pwa.services.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public User findUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }
    @GetMapping("/user-id/{id}")
    @PreAuthorize("isAuthenticated()")
    public User findUserById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user, @PathVariable Integer id){
        User old = userService.findById(id);
        return userService.updateUser(old, user);

    }

    @DeleteMapping("/user/{username}")
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUser(@PathVariable String username){
        return userService.deleteUserByUsername(username);
    }


}
