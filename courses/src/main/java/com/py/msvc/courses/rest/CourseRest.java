package com.py.msvc.courses.rest;

import com.py.msvc.courses.entity.Course;
import com.py.msvc.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("course")
public class CourseRest {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> findAll(){
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Course> Course = courseService.findById(id);
        if (Course.isPresent()){
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(Course.get());
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping
    public ResponseEntity<Course> save(Course data){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> edit(@PathVariable Long id, @RequestBody Course data){
        Optional<Course> Course = courseService.findById(id);
        if (Course.isPresent()){

            Course entity = Course.get();
            entity.setName(data.getName());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(courseService.save(entity));
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteById(Long id){
        Optional<Course> Course = courseService.findById(id);
        if (Course.isPresent()){
            courseService.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(id);
        }

        return ResponseEntity
                .notFound()
                .build();
    }

}
