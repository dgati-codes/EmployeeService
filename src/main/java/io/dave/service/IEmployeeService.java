package io.dave.service;

import java.util.List;

import io.dave.entity.Employee;

public interface IEmployeeService {

	Long addEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	void deleteEmployeeById(Long id);

	void updateEmployee(Employee emp);

	Integer modifyEmployeeName(String newName, Long employeeId);
}
