package com.py.msvc.courses.entity;

import com.py.msvc.courses.dto.UserDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<CourseUser> courseUsers;

    @Transient
    private List<UserDTO> users;

    public Course() {
        courseUsers = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Course(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseUser> getCourseUsers() {
        return courseUsers;
    }

    public void setCourseUsers(List<CourseUser> courseUser) {
        this.courseUsers = courseUser;
    }

    public void addCourseUser(CourseUser entity){
        courseUsers.add(entity);
    }

    public void removeCourseUser(CourseUser entity){
        courseUsers.remove(entity);
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
