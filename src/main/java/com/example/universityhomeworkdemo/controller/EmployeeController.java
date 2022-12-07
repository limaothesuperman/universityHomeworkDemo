package com.example.universityhomeworkdemo.controller;

import com.example.universityhomeworkdemo.entity.EmployeeData;
import com.example.universityhomeworkdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmpService empService;


    @Autowired
    public EmployeeController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public EmployeeData[] getAllEmployees() {
        return (empService.getAllEmployees());
    }

    @GetMapping("/olderthan30")
    public List<EmployeeData> getAllEmployeesOlderThan30() {
        return (empService.getEmployeesOlderThan30());
    }


}
