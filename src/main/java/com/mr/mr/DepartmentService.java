package com.mr.mr;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class DepartmentService {
	@Autowired
	private DepartmentRepository ds;

	public List<Department> listAll() {
		Iterable<Department> dept = ds.findAll();
		List<Department> ans = new ArrayList<Department>();
		dept.forEach(ans::add);
		return ans;
	}

	public void save(Department dept) {
		ds.save(dept);
	}

	public Department findByID(Integer id) {
		return ds.findById(id).get();
	}

	public void delete(Integer id) {
		ds.deleteById(id);
	}
}
