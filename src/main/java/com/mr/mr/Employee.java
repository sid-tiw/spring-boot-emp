package com.mr.mr;

import java.sql.Date;
import java.util.Random;

import javax.persistence.*;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eID;
	private String fname, lname;

	private Date DOB;
	private String hashed_pass;

	@ManyToOne
	@JoinColumn(name = "deptID")
	private Department dept;

	public Employee() { }

	public Employee(Integer eID, String fname, String lname) {
		this.eID = eID;
		this.fname = fname;
		this.lname = lname;
	}

	// This function is to be called only for the first time, when the password is being generated.
	public String generatePassword() {
		int size = 15; // the first password is 15 characters long
		this.hashed_pass = "";
		
		// A simple seed for the random number generator is the current system time.
		// A problem with this seed is that if two users are made within 1 miliseconds on the system, their password will be same.
		Random rn = new Random(System.currentTimeMillis());

		// the random number will extract the data from these different buckets. Some charcter groups are copied multiple times to increase
		// the probability of those to be included in the password.
		String buckets = "#&*!@#&*!@#&*!@#&*!abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#&*!@#&*!@#&*!@#&*!@01234567890123456789";
		int len = buckets.length();
		
		for (int i = 0; i < size; i++)
			hashed_pass += buckets.charAt(rn.nextInt(len));
		
		hashed_pass = Hashed.hashIt(hashed_pass);
		return hashed_pass;
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

	public String getHashed_pass() {
		return this.hashed_pass;
	}

	public void setHashed_pass(String hashed_pass) {
		this.hashed_pass = hashed_pass;
	}
}
