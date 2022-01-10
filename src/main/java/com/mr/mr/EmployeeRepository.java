package com.mr.mr;

import org.springframework.data.repository.*;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
}