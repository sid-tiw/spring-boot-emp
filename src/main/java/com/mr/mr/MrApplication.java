package com.mr.mr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MrApplication {

	@Autowired
	private EmployeeService emps;
	@Autowired
	private DepartmentService deps;

	public static void main(String[] args) {
		SpringApplication.run(MrApplication.class, args);
	}

	@GetMapping("/check")
	public String check(@RequestParam(value = "name", defaultValue = "Siddhartha") String name) {
		// find whether the name is a palindrome
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != name.charAt(name.length() - i - 1)) {
				return name + " is not a palindrome!!";
			}
		}
		return name + " is a palindrome";
	}

	/* Implement login and logout here. */

	@GetMapping("/emp")
	public Employee emp(@RequestParam(value = "id", defaultValue = "1") Integer id) {
		return emps.findByID(id);
	}
}
