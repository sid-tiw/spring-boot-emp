package com.mr.mr;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository er;

	public Employee findByID(Integer id) {
		return er.findById(id).get();
	}

	public void delete(Integer id) {
		er.deleteById(id);
	}

	public void save(Employee emp) {
		er.save(emp);
	}

	public boolean exists(Integer id) {
		return er.existsById(id);
	}

	public List<Employee> listAll() {
		Iterable<Employee> emp = er.findAll();
		List<Employee> lst = new ArrayList<Employee>();
		emp.forEach(lst::add);
		return lst;
	}
}
