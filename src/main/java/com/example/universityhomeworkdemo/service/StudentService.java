package com.example.universityhomeworkdemo.service;

import com.example.universityhomeworkdemo.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentID, StudentDto studentDto);

    String deleteStudent(Long studentID);
}
