package com.py.msvc.courses.service.impl;

import com.py.msvc.courses.entity.Course;
import com.py.msvc.courses.repository.CourseRepository;
import com.py.msvc.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll(){
        return (List<Course>) courseRepository.findAll();
    }

    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }

    public Course save(Course entity){
        return courseRepository.save(entity);
    }

    public void deleteById(Long id){
        courseRepository.deleteById(id);
    }

}
