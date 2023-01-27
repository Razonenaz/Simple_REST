package com.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.model.Student;
import com.springboot.rest.repository.IRepository;

@Service
public class StudentService {
    
    @Autowired
    private IRepository repository;
    
    public void showAllStudents() {
        List<Student> list = repository.findAll();
        
        for(Student student : list) {
            System.out.println(student);
        }
    }
    
    public void showStudentById(long id) {
        Student student = repository.findById(id).get();
        System.out.println(student);
    }
    
    
    public void insertStudent(Student student) {
        repository.save(student);
        System.out.println("Has been inserted: " + student);
    }
    
    public void updateStudentById(Student student, long id) {
        student.setId(id);
        repository.save(student);
        System.out.println("Has been updated: " + student);
    }
    
    public void deleteStudentById(long id) {
        repository.deleteById(id);
        System.out.println("Deleted!");
    }
}
