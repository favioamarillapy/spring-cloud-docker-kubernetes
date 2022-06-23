package com.py.msvc.courses.service.impl;

import com.py.msvc.courses.client.UserClient;
import com.py.msvc.courses.dto.UserDTO;
import com.py.msvc.courses.entity.Course;
import com.py.msvc.courses.entity.CourseUser;
import com.py.msvc.courses.repository.CourseRepository;
import com.py.msvc.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClient userClient;

    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        Optional<Course> o = courseRepository.findById(id);
        if (o.isPresent()) {
            Course course = o.get();
            if (!course.getCourseUsers().isEmpty()) {
                List<Long> ids = course.getCourseUsers()
                        .stream()
                        .map(courseUser -> courseUser.getUserId())
                        .collect(Collectors.toList());

                List<UserDTO> users = userClient.findAllById(ids);
                course.setUsers(users);

                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    public Course save(Course entity) {
        return courseRepository.save(entity);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public Optional<UserDTO> assignUser(UserDTO user, Long courseId) {
        Optional<Course> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            UserDTO userDTO = userClient.findById(user.getId());

            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userDTO.getId());

            Course entity = o.get();
            entity.addCourseUser(courseUser);
            courseRepository.save(entity);

            return Optional.of(userDTO);
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<UserDTO> createUser(UserDTO user, Long courseId) {
        Optional<Course> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            UserDTO userDTO = userClient.save(user);

            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userDTO.getId());

            Course course = o.get();
            course.addCourseUser(courseUser);
            courseRepository.save(course);

            return Optional.of(userDTO);
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<UserDTO> unassignUser(UserDTO user, Long courseId) {
        Optional<Course> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            UserDTO userDTO = userClient.findById(user.getId());

            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userDTO.getId());

            Course course = o.get();
            course.removeCourseUser(courseUser);
            courseRepository.save(course);

            return Optional.of(userDTO);
        }

        return Optional.empty();
    }

}
