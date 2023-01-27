package com.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.model.Student;

public interface IRepository extends JpaRepository<Student, Long> {

}
