package com.py.msvc.courses.repository;

import com.py.msvc.courses.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
