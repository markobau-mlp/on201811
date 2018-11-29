package com.corso.activities.core.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.model.Employee;
import com.corso.activities.core.model.TimeSpent;
import com.corso.activities.core.util.Calc;

public class EmployeeBo {
	private static final Logger log = LoggerFactory.getLogger(EmployeeBo.class);
	
	private EmployeeDao employeeDao;

	/**
	 * @param employeeDao
	 */
	public EmployeeBo(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public float calculateTotalWorkedHours(Long employeeId) {
		Employee employee = employeeDao.findById(employeeId);
		long totalTime = 0;
		for (TimeSpent timeSpent : employee.getTimeSpents()) {
			if (timeSpent.getEnd() != null) {
				totalTime += timeSpent.getTimeSpentInMilliseconds();
			} else {
				log.warn("TimeSpent still not completed ({})", timeSpent.getId());
			}
		}
		return Calc.millisecondsToHours(totalTime);
	}
	
}
