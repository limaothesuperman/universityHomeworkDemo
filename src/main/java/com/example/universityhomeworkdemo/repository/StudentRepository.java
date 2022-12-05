package com.example.universityhomeworkdemo.repository;

import com.example.universityhomeworkdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String studentName);
}
