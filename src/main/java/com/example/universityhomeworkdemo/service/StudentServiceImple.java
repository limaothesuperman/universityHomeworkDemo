package com.example.universityhomeworkdemo.service;

import com.example.universityhomeworkdemo.dto.StudentDto;
import com.example.universityhomeworkdemo.entity.Student;
import com.example.universityhomeworkdemo.entity.Teacher;
import com.example.universityhomeworkdemo.repository.StudentRepository;
import com.example.universityhomeworkdemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImple implements StudentService {


    private final StudentRepository studentRepository;


    private final TeacherRepository teacherRepository;

    @Autowired
    public StudentServiceImple(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = new Student();
        mapDtoToEntity(studentDto, student);
        Student newStudent = studentRepository.save(student);
        return mapEntityToDto(newStudent);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> stuList = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        stuList.forEach(student -> {
            StudentDto studentDto = mapEntityToDto(student);
            studentDtos.add(studentDto);
        });
        return studentDtos;
    }

    @Override
    public StudentDto updateStudent(Long studentID, StudentDto studentDto) {
        Student std = studentRepository.getOne(studentID);
        std.getTeachers().clear();
        mapDtoToEntity(studentDto, std);
        Student student = studentRepository.save(std);
        return mapEntityToDto(student);
    }

    @Override
    public String deleteStudent(Long studentID) {
        Optional<Student> student = studentRepository.findById(studentID);
        if (student.isPresent()) {
            student.get().removeTeachers();
            studentRepository.deleteById(student.get().getId());
            return "Student with id: " + studentID + " deleted successfully!";
        }
        return null;
    }

    private void mapDtoToEntity(StudentDto studentDto, Student student) {
        student.setName(studentDto.getName());
        if (null == student.getTeachers()) {
            student.setTeachers(new LinkedList<>());
        }
        studentDto.getTeachers().forEach(teacherName -> {
            Teacher teacher = teacherRepository.findByName(teacherName);
            if (null == teacher) {
                teacher = new Teacher();
                teacher.setStudents(new LinkedList<>());
            }
            teacher.setName(teacherName);
            student.addTeacher(teacher);
        });
    }

    private StudentDto mapEntityToDto(Student student) {
        StudentDto responseDto = new StudentDto();
        responseDto.setName(student.getName());
        responseDto.setId(student.getId());
        responseDto.setTeachers(student.getTeachers().stream().map(Teacher::getName).collect(Collectors.toList()));
        return responseDto;
    }
}
