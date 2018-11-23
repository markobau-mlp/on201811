package com.corso.activities.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.dao.jpa.JpaEmployeeDao;
import com.corso.activities.core.model.Employee;
import com.corso.activities.web.util.Constants;

/**
 * Servlet implementation class SimpleEmployeeServlet
 */
@WebServlet("/employees_simple")
public class SimpleEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf =
				(EntityManagerFactory) request.getServletContext()
				.getAttribute(Constants.ENTITY_MANAGER_FACTORY_ATTRIBUTE);
		EntityManager em = emf.createEntityManager();
		EmployeeDao employeeDao = new JpaEmployeeDao(em);
		List<Employee> employees = employeeDao.findAll();
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("/WEB-INF/jsp/employees/list.jsp").forward(request, response);
		em.close();
	}

}
