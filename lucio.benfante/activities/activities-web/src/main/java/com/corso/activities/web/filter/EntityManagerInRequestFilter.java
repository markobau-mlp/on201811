package com.corso.activities.web.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.corso.activities.web.util.Constants;

/**
 * Servlet Filter implementation class EntityManagerInRequestFilter
 */
@WebFilter("/*")
public class EntityManagerInRequestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EntityManagerInRequestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute(Constants.ENTITY_MANAGER_FACTORY_ATTRIBUTE);
    	EntityManager em = emf.createEntityManager();
    	request.setAttribute(Constants.ENTITY_MANAGER_ATTRIBUTE, em);
		
    	chain.doFilter(request, response);
		
    	em.close();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
