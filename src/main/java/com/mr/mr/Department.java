package com.mr.mr;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(targetEntity = Employee.class, mappedBy = "dept")
	private List<Employee> emp;

	public Department() { }

	public void setEmp(List<Employee> emp) {
		this.emp = emp;
	}

	public List<Employee> getEmp() {
		return emp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return id + " : " + name;
	}
}
