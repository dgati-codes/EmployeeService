package io.dave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dave.entity.Employee;
import io.dave.repo.EmployeeRepository;
import io.dave.service.IEmployeeService;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	public Long addEmployee(Employee employee) {
		return employeeRepo.save(employee).getEmpId();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee emp) {
		employeeRepo.save(emp);

	}

	@Override
	@Transactional
	public int modifyEmployeeName(String newName, Long employeeId) {
		 return employeeRepo.updateEmployeeName(newName, employeeId);
	}

}
