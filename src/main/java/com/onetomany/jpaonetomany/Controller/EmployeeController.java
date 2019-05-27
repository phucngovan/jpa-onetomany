package com.onetomany.jpaonetomany.Controller;

import com.onetomany.jpaonetomany.Model.Employee;
import com.onetomany.jpaonetomany.Service.employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "")
@RestController
@RequestMapping("/api")
public class EmployeeController {
    public static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployee(){
        List<Employee> listEmployee= employeeService.findAll();
        if(listEmployee.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(listEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable("id") int id) {
        Employee employee= employeeService.findById(id);
        if(employee == null) {
            ResponseEntity.notFound().build();
        }
        return employee;
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                           @Valid @RequestBody Employee employeeForm) {
        Employee employee = employeeService.findById(employeeId);
        if(employee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(employeeForm.getId());
        employee.setName(employeeForm.getName());
        employee.setBirthday(employee.getBirthday());
        employee.setEmail(employee.getEmail());
        employee.setGender(employee.getGender());
        employee.setRoom(employee.getRoom());
        Employee updatedEmployee = employeeService.create(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Integer id) {
        Employee employee = employeeService.findById(id);
        if(employee == null) {
            return ResponseEntity.notFound().build();
        }

        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
