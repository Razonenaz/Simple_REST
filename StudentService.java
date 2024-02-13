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

    public List<Student> getAll() {
        List<Student> students = repository.findAll();

        return students;
    }

    public Student getById(long id) {
        Student student = repository.findById(id)
            .get();

        return student;
    }

    public Student add(Student student) {
        Student studentCreated = repository.save(student);

        return studentCreated;
    }

    public Student updateById(Student student, long id) {
        student.setId(id);
        repository.save(student);

        return repository.findById(id)
            .get();
    }

    public String deleteById(long id) {
        repository.deleteById(id);

        return "Deleted!";
    }
}
