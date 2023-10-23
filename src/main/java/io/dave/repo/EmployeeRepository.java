package io.dave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.dave.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Modifying
    @Query("UPDATE Employee e SET e.empName = :ename WHERE e.empId = :eid")
    int updateEmployeeName(@Param("ename") String ename, @Param("eid") Long eid);
}
