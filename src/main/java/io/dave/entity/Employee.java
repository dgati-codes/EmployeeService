package io.dave.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="emptab")
@Schema(description = "Employee  table", name = "Employee Schema")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eid")
	@Schema(description = "ID which uniquely identifies an employee")
	private Long empId;
	
	@Column(name="ename")
	@Schema(description = "ID which uniquely identifies an employee")
	private String empName;
	
	@Column(name="esal")
	@Schema(description = "Employee salary")
	private Double empSal;
	
	@Column(name="egen")
	@Schema(description = "Employee gender")
	private String empGen;
	
	@Column(name="edept")
	@Schema(description = "Employee Department they belongs")
	private String empDept;
	
	@Column(name="eaddr")
	@Schema(description = "Employee  Address")
	private String empAddr;
	
}