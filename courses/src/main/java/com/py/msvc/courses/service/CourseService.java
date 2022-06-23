package com.py.msvc.courses.service;

import com.py.msvc.courses.dto.UserDTO;
import com.py.msvc.courses.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> findAll();

    public Optional<Course> findById(Long id);

    public Course save(Course entity);

    public void deleteById(Long id);

    public Optional<UserDTO> assignUser(UserDTO user, Long courseId);

    public Optional<UserDTO> createUser(UserDTO user, Long courseId);

    public Optional<UserDTO> unassignUser(UserDTO user, Long courseId);

}
