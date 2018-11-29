package com.corso.activities.core.bo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.model.Activity;
import com.corso.activities.core.model.Customer;
import com.corso.activities.core.model.Employee;
import com.corso.activities.core.model.TimeSpent;

public class EmployeeBoTestWithMockito {

	@Test
	public void testCalculateTotalWorkedHours() {
		EmployeeBo employeeBo = new EmployeeBo(getTestEmployeeDao());
		float result = employeeBo.calculateTotalWorkedHours(1L);
		assertEquals(13.0f, result, 0.00001);
	}

	private EmployeeDao getTestEmployeeDao() {
		EmployeeDao employeeDao = mock(EmployeeDao.class);
		when(employeeDao.findById(1L)).thenReturn(getEmployeeForTest());
		return employeeDao;
	}

	private Employee getEmployeeForTest() {
		return getEmployeesForTesting().get(0);
	}

	private List<Employee> getEmployeesForTesting() {
		Customer customer = new Customer("First Consulting");

		Activity javaActivity = new Activity("Teaching Java");
		customer.addActivity(javaActivity);
		Activity sqlActivity = new Activity("Teaching SQL");
		customer.addActivity(sqlActivity);
		Activity jpaActivity = new Activity("Teaching JPA");
		customer.addActivity(jpaActivity);

		Employee employee1 = new Employee("Lucio", "Benfante");
		employee1.setId(1L);
		Employee employee2 = new Employee("James", "Gosling");
		employee2.setId(2L);

		TimeSpent timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.addEmployee(employee2);
		timeSpent.setStart(2018, 11, 20, 9, 0);
		timeSpent.setEnd(2018, 11, 20, 13, 0);
		javaActivity.addTimeSpent(timeSpent);

		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee2);
		timeSpent.setStart(2018, 11, 20, 14, 0);
		timeSpent.setEnd(2018, 11, 20, 18, 0);
		javaActivity.addTimeSpent(timeSpent);

		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.setStart(2018, 11, 21, 9, 0);
		timeSpent.setEnd(2018, 11, 21, 18, 0);
		jpaActivity.addTimeSpent(timeSpent);

		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.setStart(2018, 11, 28, 9, 0);
		jpaActivity.addTimeSpent(timeSpent);
		
		List<Employee> result = new ArrayList<>();
		result.add(employee1);
		result.add(employee2);
		return result;
	}
	
}
