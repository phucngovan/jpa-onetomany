package com.onetomany.jpaonetomany.Service.employee;

import com.onetomany.jpaonetomany.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee delete(int id);

    List<Employee> findAll();

    Employee findById(int id);

    Employee update(Employee employee);
}
