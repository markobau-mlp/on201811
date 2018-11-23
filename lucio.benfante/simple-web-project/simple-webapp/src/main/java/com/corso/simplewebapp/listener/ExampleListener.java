package com.corso.simplewebapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ExampleListener
 *
 */
public class ExampleListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ExampleListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// sce.getServletContext().getAttribute("jpaEmf").close();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
//    	ServletContext servletContext = sce.getServletContext();
//    	EntitiManagerFactory emf = Persistence;
//    	servletContext.setAttribute("jpaEmf", emf);
    }
	
}
