package com.corso.activities.core.dao.mock;

import java.util.ArrayList;
import java.util.List;

import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.model.Employee;

public class MockEmployeeDao implements EmployeeDao {
	List<Employee> archive = new ArrayList<>();

	/**
	 * @return the archive
	 */
	public List<Employee> getArchive() {
		return archive;
	}

	/**
	 * @param archive the archive to set
	 */
	public void setArchive(List<Employee> archive) {
		this.archive = archive;
	}

	@Override
	public Employee findById(Long id) {
		Employee result = null;
		for (Employee employee : archive) {
			if (id.equals(employee.getId())) {
				result = employee;
				break;
			}
		}
		return result;
	}

	@Override
	public List<Employee> findAll() {
		return archive;
	}

	@Override
	public Employee findByFirstNameAndLastName(String firstName, String lastName) {
		Employee result = null;
		for (Employee employee : archive) {
			if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
				result = employee;
				break;
			}
		}
		return result;
	}

}
