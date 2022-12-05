package com.example.universityhomeworkdemo.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TeacherDto {
    private Long id;
    private String name;
    private List<String> students = new LinkedList<>();
}
