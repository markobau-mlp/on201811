package com.corso.activities.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corso.activities.core.dao.DaoFactory;
import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.model.Employee;
import com.corso.activities.web.util.WebDaoFactory;

/**
 * Servlet implementation class EmployeesServlet
 */
@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory daoFactory = WebDaoFactory.getInstance(request);
		EmployeeDao employeeDao = daoFactory.getEmployeeDao();
		List<Employee> employees = employeeDao.findAll();
		
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("/WEB-INF/jsp/employees/list.jsp").forward(request, response);
	}

}
