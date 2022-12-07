package com.example.universityhomeworkdemo.service;

import com.example.universityhomeworkdemo.config.RestTemplateConfig;
import com.example.universityhomeworkdemo.entity.EmployeeData;
import com.example.universityhomeworkdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmpServiceImple implements EmpService {
    private final RestTemplateConfig restTemplateConfig;

    @Autowired
    public EmpServiceImple(RestTemplateConfig restTemplateConfig, RestTemplate restTemplate) {
        this.restTemplateConfig = restTemplateConfig;
    }

    /*

     *  TODO
     *      1. create 2 endpoints
     *          list all employees
     *          list employees whose age larger than 30
     *      2. @ControllerAdvice + @ExceptionHandler -> Exception.class -> internal server error
     *      3. @ControllerAdvice + common response
     */


    @Override
    public EmployeeData[] getAllEmployees() {
        String url = "https://dummy.restapiexample.com/api/v1/employees";

        return Objects.requireNonNull(restTemplateConfig.getRestTemplate()
                .getForObject(url, Employee.class)).getData();
    }

    @Override
    public List<EmployeeData> getEmployeesOlderThan30() {
        EmployeeData[] empData = this.getAllEmployees();
        List<EmployeeData> result = new ArrayList<>();
        for (EmployeeData d : empData) {
            if (d.getEmployee_age() > 30)
                result.add(d);
        }
        //result.add(empData[300]); used for test globalException
        return result;
    }

}
