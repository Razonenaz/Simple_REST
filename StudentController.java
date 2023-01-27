package com.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.model.Student;
import com.springboot.rest.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    @ResponseBody
    public void getAll() {
        service.showAllStudents();
    }

    @GetMapping(value = "/student")
    @ResponseBody
    public void getById(@RequestParam long id) {
        service.showStudentById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Student student) {
        service.insertStudent(student);
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student, @RequestParam long id) {
        service.updateStudentById(student, id);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam long id) {
        service.deleteStudentById(id);
    }
}
