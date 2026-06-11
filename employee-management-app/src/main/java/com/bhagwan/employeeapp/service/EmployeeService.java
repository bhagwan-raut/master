package com.bhagwan.employeeapp.service;

import com.bhagwan.employeeapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private List<Employee> employeeList = new ArrayList<>();
    private int idCounter = 1;

    // Constructor - Add some dummy data
    public EmployeeService() {
        employeeList.add(new Employee(idCounter++, "Bhagwan Raut",   "IT",      75000));
        employeeList.add(new Employee(idCounter++, "Rahul Sharma",   "HR",      55000));
        employeeList.add(new Employee(idCounter++, "Priya Patil",    "Finance", 65000));
        employeeList.add(new Employee(idCounter++, "Amit Desai",     "IT",      80000));
        employeeList.add(new Employee(idCounter++, "Sneha Kulkarni", "Sales",   60000));
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        Optional<Employee> emp = employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        return emp.orElse(null);
    }

    // Add new employee
    public Employee addEmployee(Employee employee) {
        employee.setId(idCounter++);
        employeeList.add(employee);
        return employee;
    }

    // Delete employee by ID
    public boolean deleteEmployee(int id) {
        return employeeList.removeIf(e -> e.getId() == id);
    }

    // Get employees by department
    public List<Employee> getByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employeeList) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                result.add(e);
            }
        }
        return result;
    }
}