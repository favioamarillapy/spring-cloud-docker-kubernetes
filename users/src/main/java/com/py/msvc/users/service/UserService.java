package com.py.msvc.users.service;

import com.py.msvc.users.entity.User;
import com.py.msvc.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User entity);

    public void deleteById(Long id);

    public Optional<User> findByEmail(String email);
}
