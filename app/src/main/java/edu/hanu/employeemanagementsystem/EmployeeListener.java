package edu.hanu.employeemanagementsystem;

import edu.hanu.employeemanagementsystem.models.Employee;

public interface EmployeeListener {
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
