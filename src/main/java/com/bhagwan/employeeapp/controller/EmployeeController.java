package com.bhagwan.employeeapp.controller;

import com.bhagwan.employeeapp.model.Employee;
import com.bhagwan.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET all employees
    // URL: http://localhost:8085/api/employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET employee by ID
    // URL: http://localhost:8085/api/employees/1
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.notFound().build();
    }

    // GET employees by department
    // URL: http://localhost:8085/api/employees/department/IT
    @GetMapping("/department/{dept}")
    public List<Employee> getByDepartment(@PathVariable String dept) {
        return employeeService.getByDepartment(dept);
    }

    // POST - Add new employee
    // URL: http://localhost:8085/api/employees
    // Body: { "name": "John", "department": "IT", "salary": 70000 }
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // DELETE employee by ID
    // URL: http://localhost:8085/api/employees/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.ok("Employee deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    // GET - App status
    // URL: http://localhost:8085/api/employees/status
    @GetMapping("/status")
    public String status() {
        return "Employee Management App is Running! ✅";
    }
}