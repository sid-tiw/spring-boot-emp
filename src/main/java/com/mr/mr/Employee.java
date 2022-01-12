package com.mr.mr;

import java.sql.Date;
import javax.persistence.*;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eID;
	private String fname, lname;

	private Date DOB;

	@ManyToOne
	@JoinColumn(name = "deptID")
	private Department dept;

	public Employee() { }

	public Employee(Integer eID, String fname, String lname) {
		this.eID = eID;
		this.fname = fname;
		this.lname = lname;
	}

	@Override
	public String toString() {
		return Integer.toString(eID) + fname + lname + DOB.toString();
	}

	// Getters and Setters from here.

	public Integer getEID() {
		return eID;
	}

	public void setEID(Integer eID) {
		this.eID = eID;
	}

	public String getName() {
		return fname + lname;
	}

	public String getfname(){ 
		return fname;
	}

	public void setfname(String fname) {
		this.fname = fname;
	}

	public String getlname() {
		return lname;
	}

	public void setlname(String lname) {
		this.lname = lname;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date DOB) {
		this.DOB = DOB;
	}
}
