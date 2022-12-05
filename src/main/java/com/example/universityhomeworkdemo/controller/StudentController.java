package com.example.universityhomeworkdemo.controller;


import com.example.universityhomeworkdemo.dto.StudentDto;
import com.example.universityhomeworkdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto std = studentService.addStudent(studentDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable(name = "id") Long id,
                                                    @RequestBody StudentDto student) {
        StudentDto std = studentService.updateStudent(id, student);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Long studentId) {
        String message = studentService.deleteStudent(studentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler() {
        return new ResponseEntity<>("Exception", HttpStatus.BAD_REQUEST);
    }
}
