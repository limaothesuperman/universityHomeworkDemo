package com.example.universityhomeworkdemo.service;

import com.example.universityhomeworkdemo.dto.TeacherDto;


import java.util.List;

public interface TeacherService {
    TeacherDto addTeacher(TeacherDto teacherDto);

    List<TeacherDto> getAllTeachers();

    TeacherDto updateTeacher(Long teacherID, TeacherDto teacherDto);

    String deleteTeacher(Long teacherID);
}
