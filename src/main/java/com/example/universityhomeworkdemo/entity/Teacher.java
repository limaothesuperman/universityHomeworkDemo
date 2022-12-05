package com.example.universityhomeworkdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "teachers")
    @JsonIgnore
    private List<Student> students;

    public void removeStudent(Student student) {
        this.getStudents().remove(student);
        student.getTeachers().remove(this);
    }

    public void removeStudents() {
        for (Student student : new LinkedList<>(students)) {
            removeStudent(student);
        }
    }
}
