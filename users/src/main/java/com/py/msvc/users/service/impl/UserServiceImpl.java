package com.py.msvc.users.service.impl;

import com.py.msvc.users.entity.User;
import com.py.msvc.users.repository.UserRepository;
import com.py.msvc.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User entity){
        return userRepository.save(entity);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAllById(List<Long> ids){
        return (List<User>) userRepository.findAllById(ids);
    }
}
