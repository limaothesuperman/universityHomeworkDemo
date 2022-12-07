package com.example.universityhomeworkdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;

}
