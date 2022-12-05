package com.example.universityhomeworkdemo.service;

import com.example.universityhomeworkdemo.dto.TeacherDto;
import com.example.universityhomeworkdemo.entity.Student;
import com.example.universityhomeworkdemo.entity.Teacher;
import com.example.universityhomeworkdemo.repository.StudentRepository;
import com.example.universityhomeworkdemo.repository.TeacherRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImple implements TeacherService {

    private final StudentRepository studentRepository;


    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImple(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        mapDtoToEntity(teacherDto, teacher);
        Teacher newTeacher = teacherRepository.save(teacher);
        return mapEntityToDto(newTeacher);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        teachers.forEach(teacher -> {
            TeacherDto teacherDto = mapEntityToDto(teacher);
            teacherDtos.add(teacherDto);
        });
        return teacherDtos;
    }

    @Override
    public TeacherDto updateTeacher(Long teacherID, TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.getOne(teacherID);
        teacher.getStudents().clear();
        mapDtoToEntity(teacherDto, teacher);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return mapEntityToDto(updatedTeacher);
    }

    @Override
    public String deleteTeacher(Long teacherID) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherID);
        if (teacher.isPresent()) {
            teacher.get().removeStudents();
            teacherRepository.deleteById(teacher.get().getId());
            return "Teacher with id: " + teacherID + " deleted successfully!";
        }
        return null;
    }

    private void mapDtoToEntity(TeacherDto teacherDto, Teacher teacher) {
        teacher.setName(teacherDto.getName());
        if (null == teacher.getStudents()) {
            teacher.setStudents(new LinkedList<>());
        }
        teacherDto.getStudents().forEach(studentName -> {
            Student student = studentRepository.findByName(studentName);
            if (null == student) {
                student = new Student();
                student.setTeachers(new LinkedList<>());
            }
            student.setName(studentName);
            student.addTeacher(teacher);
        });
    }

    private TeacherDto mapEntityToDto(Teacher teacher) {
        TeacherDto responseDto = new TeacherDto();
        responseDto.setName(teacher.getName());
        responseDto.setId(teacher.getId());
        responseDto.setStudents(teacher.getStudents().stream().map(Student::getName).collect(Collectors.toList()));
        return responseDto;
    }
}
