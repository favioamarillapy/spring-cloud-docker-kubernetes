package com.py.msvc.courses.client;

import com.py.msvc.courses.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "users", url = "localhost:8001")
public interface UserClient {

    @GetMapping("/{id}")
    UserDTO findById(@PathVariable Long id);

    @GetMapping("/users")
    List<UserDTO> findAllById(@RequestParam("ids") List<Long> ids);

    @PostMapping
    UserDTO save(UserDTO data);

    @PutMapping("/{id}")
    UserDTO edit(@PathVariable Long id, @RequestBody UserDTO data);

    @DeleteMapping
    Long deleteById(Long id);
}
