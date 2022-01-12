package com.mr.mr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class MrApplication {

	@Autowired
	private EmployeeService emps;
	@Autowired
	private DepartmentService deps;

	public static void main(String[] args) {
		SpringApplication.run(MrApplication.class, args);
	}

	@GetMapping("/login/")
	public String login(Model md) {
		md.addAttribute("login", new Login());
		return "login";
	}

	@PostMapping("/login/")
	public String loginPost(Login lg, Model md) {
		boolean status = emps.exists(lg.eID);
		if (!status) {
			md.addAttribute("login", new Login());
			md.addAttribute("warning", "Wrong Employee ID!! Please check carefully and try again.");
			return "login";
		}
		Employee temp = emps.findByID(lg.eID);
		if (Hashed.hashIt(lg.passwd).compareTo(temp.getHashed_pass()) != 0) {
			md.addAttribute("login", new Login());
			md.addAttribute("warning", "Invalid Password!! Please check carefully and try again.");
		} else {
			md.addAttribute("login", new Login());
			md.addAttribute("warning", "Login successfull!! You can proceed on doing your tasks.");
		}

		// Generate the jwt token here and send it to the client.
		// return to home page.
		return "login";
	}

	@GetMapping("/admin/manage")
	public String addEmp(Model md) {
		md.addAttribute("departments", deps.listAll());
		md.addAttribute("employee", new Employee());
		return "manage";
	}

	@PostMapping("/admin/manage/")
	public String addPost(Employee emp, Model mdl) {
		if (emp == null) {
			System.out.println("Null value!!"); // log the value here (in kafka or whatever)
			return "error_creation";
		}
		mdl.addAttribute("passwd", emp.generatePassword());
		emps.save(emp);
		return "post_creation";
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

	@PostMapping("/add")
	public void add(@RequestBody Employee employee) {
		emps.save(employee);
	}
}
