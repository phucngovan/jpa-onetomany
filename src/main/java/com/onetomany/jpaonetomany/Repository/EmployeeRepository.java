package com.onetomany.jpaonetomany.Repository;

import com.onetomany.jpaonetomany.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
