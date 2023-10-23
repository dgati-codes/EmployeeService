package io.dave.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dave.dto.ApiResponse;
import io.dave.entity.Employee;
import io.dave.service.impl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/employee")
@Tag(name = "Approval Level API",
description = "Retrieve, Update, Create and remove employee Data")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping("/list")
	@Operation(summary = "Retrieve all Employees")
	public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {
		return Optional.ofNullable(employeeService.getAllEmployees()).filter(list -> !list.isEmpty())
				.map(data -> ResponseEntity.ok(new ApiResponse<>(data, "success"))).orElseGet(() -> ResponseEntity
						.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, "No Record Found")));
	}

	@GetMapping("/{empId}")
	@Operation(summary = "Retrieve Employee by Id")
	public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable("empId") Long empId) {
		return Optional.ofNullable(employeeService.getEmployeeById(empId))
				.map(data -> ResponseEntity.ok(new ApiResponse<>(data, "success"))).orElseGet(() -> ResponseEntity
						.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, "Record Not Found")));
	}

	@PostMapping("/create")
	@Operation(summary = "Create Employee")
	public ResponseEntity<ApiResponse<Long>> createEmployee(@RequestBody Employee employee) {
		return Optional.ofNullable(employeeService.addEmployee(employee))
				.map(data -> ResponseEntity.ok(new ApiResponse<>(data, "success")))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiResponse<>(null, "Failed to create employee")));
	}

	@DeleteMapping("/remove/{empId}")
	@Operation(summary = "Remove Employee by Id")
	public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable("empId") Long empId) {
		try {
			employeeService.deleteEmployeeById(empId);
			return ResponseEntity.ok(new ApiResponse<>(null, "success"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(null, "Failed to delete employee"));
		}
	}

	@PutMapping("/update")
	@Operation(summary = "Update Employee record")
	public ResponseEntity<ApiResponse<Void>> updateEmployee(@RequestBody Employee employee) {
		try {
			employeeService.updateEmployee(employee);
			return ResponseEntity.ok(new ApiResponse<>(null, "success"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(null, "Failed to update employee"));
		}
	}
	@PatchMapping("/modify")
	@Operation(summary = "Modify Employee name and Id")
    public ResponseEntity<ApiResponse<Integer>> modifyEmployeeName(@RequestBody Long empId, @RequestBody String newName) {
        return Optional.of(employeeService.modifyEmployeeName(newName, empId))
        		.map(data -> ResponseEntity.ok(new ApiResponse<>(data, "success")))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiResponse<>(null, "Failed to modify employee"))); 

}
}
