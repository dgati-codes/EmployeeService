package io.dave.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dave.entity.Employee;
import io.dave.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/employee")
@Tag(name = "Employee Record API", description = "Retrieve, Update, Create and remove employee Data")
@CrossOrigin("http://localhost:4200/")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("/create")
	@Operation(summary = "Create Employee")
	public ResponseEntity<Long> addEmployee(@RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
	}

	@GetMapping("/list")
	@Operation(summary = "Retrieve all Employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retrieve Employee by Id")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return employee != null ? ResponseEntity.status(HttpStatus.OK).body(employee)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/remove/{id}")
	@Operation(summary = "Remove Employee by Id")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/update")
	@Operation(summary = "Update Employee record")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> existingEmployee = Optional.ofNullable(employeeService.getEmployeeById(employee.getEmpId()));

		return existingEmployee.map(emp -> {
			employeeService.updateEmployee(employee);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PatchMapping("/edit")
	@Operation(summary = "Update Employee name")
	public ResponseEntity<Void> modifyEmployeeName(@RequestParam Long id, @RequestParam String newName) {
	    int rowsUpdated = employeeService.modifyEmployeeName(newName, id);
	    return rowsUpdated > 0 ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
	            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}


}
