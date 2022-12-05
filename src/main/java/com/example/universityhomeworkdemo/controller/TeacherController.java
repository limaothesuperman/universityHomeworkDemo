package com.example.universityhomeworkdemo.controller;


import com.example.universityhomeworkdemo.dto.TeacherDto;
import com.example.universityhomeworkdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherDto teacher = teacherService.addTeacher(teacherDto);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable(name = "id") Long id,
                                                    @RequestBody TeacherDto teacherDto) {
        TeacherDto teacher = teacherService.updateTeacher(id, teacherDto);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable(name = "id") Long id) {
        String message = teacherService.deleteTeacher(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler() {
        return new ResponseEntity<>("Exception", HttpStatus.BAD_REQUEST);
    }
}
