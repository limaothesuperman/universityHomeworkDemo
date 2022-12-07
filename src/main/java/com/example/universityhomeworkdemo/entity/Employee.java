package com.example.universityhomeworkdemo.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private String status;
    private EmployeeData[] data;

}
