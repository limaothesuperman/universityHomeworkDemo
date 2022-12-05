package com.example.universityhomeworkdemo.repository;

import com.example.universityhomeworkdemo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByName(String teacherName);
}
