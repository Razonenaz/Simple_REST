package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.model.Student;
import com.springboot.rest.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public List<Student> getAll() {
        List<Student> students = service.getAll();

        return students;
    }

    @GetMapping(value = "/student/get/{id}")
    public Student getById(@PathVariable("id") long id) {
        Student student = service.getById(id);

        return student;
    }

    @PostMapping("/student/add")
    public Student add(@RequestBody Student student) {
        Student studentCreated = service.add(student);

        return studentCreated;
    }

    @PutMapping(value = "/update/{id}")
    public Student updateById(@RequestBody Student student, @PathVariable("id") long id) {
        Student studentUpdated = service.updateById(student, id);

        return studentUpdated;
    }

    @DeleteMapping(value = "/student/delete/{id}")
    public String deleteById(@PathVariable("id") long id) {
        String messageDeleted = service.deleteById(id);

        return messageDeleted;
    }
}
