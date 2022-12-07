package com.example.universityhomeworkdemo.service;

import com.example.universityhomeworkdemo.entity.EmployeeData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
    EmployeeData[] getAllEmployees();

    List<EmployeeData> getEmployeesOlderThan30();


}
