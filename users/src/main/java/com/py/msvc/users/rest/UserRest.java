package com.py.msvc.users.rest;

import com.py.msvc.users.entity.User;
import com.py.msvc.users.service.UserService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllById(@RequestParam("ids") List<Long> ids) {
        List<User> users = userService.findAllById(ids);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user.get());
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping
    public ResponseEntity<?> save(User data) {

        if (userService.findByEmail(data.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", "User with email " + data.getEmail() + " already exist"));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody User data) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User entity = user.get();

            if (!data.getEmail().equalsIgnoreCase(entity.getEmail()) &&
                    userService.findByEmail(data.getEmail()).isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body(Collections.singletonMap("message", "User with email " + data.getEmail() + " already exist"));
            }

            entity.setName(data.getName());
            entity.setEmail(data.getEmail());
            entity.setPassword(data.getPassword());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.save(entity));
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteById(Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(id);
        }

        return ResponseEntity
                .notFound()
                .build();
    }

}
