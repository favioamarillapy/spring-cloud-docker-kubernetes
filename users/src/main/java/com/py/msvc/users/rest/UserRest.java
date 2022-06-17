package com.py.msvc.users.rest;

import com.py.msvc.users.entity.User;
import com.py.msvc.users.service.UserService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(user.get());
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping
    public ResponseEntity<User> save(User data){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody User data){
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){

            User entity = user.get();
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
    public ResponseEntity<Long> deleteById(Long id){
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
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