package com.mr.mr;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "deptID")
	private List<Employee> emp;

	public Department() { }

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
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
