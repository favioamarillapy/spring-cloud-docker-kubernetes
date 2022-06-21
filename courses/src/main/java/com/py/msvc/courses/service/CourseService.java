package com.py.msvc.courses.service;

import com.py.msvc.courses.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> findAll();

    public Optional<Course> findById(Long id);

    public Course save(Course entity);

    public void deleteById(Long id);

}
