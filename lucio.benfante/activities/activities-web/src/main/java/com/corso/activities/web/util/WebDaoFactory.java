package com.corso.activities.web.util;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.corso.activities.core.dao.DaoFactory;
import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.dao.jpa.JpaEmployeeDao;

public class WebDaoFactory implements DaoFactory {
	private EntityManager em;
	private EmployeeDao employeeDao;
	
	/**
	 * @param em
	 */
	public WebDaoFactory(EntityManager em) {
		this.em = em;
	}

	public static DaoFactory getInstance(HttpServletRequest request) {
		return new WebDaoFactory((EntityManager) request.getAttribute(Constants.ENTITY_MANAGER_ATTRIBUTE));
	}

	@Override
	public EmployeeDao getEmployeeDao() {
		if (employeeDao == null) {
			employeeDao = new JpaEmployeeDao(this.em);
		}
		return employeeDao;
	}
}
