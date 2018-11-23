package com.corso.activities.web.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.corso.activities.web.util.Constants;

/**
 * Application Lifecycle Listener implementation class EntityManagerFactoryListener
 *
 */
@WebListener
public class EntityManagerFactoryListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public EntityManagerFactoryListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	EntityManagerFactory emf =
    			(EntityManagerFactory) sce.getServletContext()
    			.getAttribute(
    					Constants.ENTITY_MANAGER_FACTORY_ATTRIBUTE);
    	emf.close();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	EntityManagerFactory emf =
    			Persistence.createEntityManagerFactory("activities");
    	sce.getServletContext().setAttribute(
    			Constants.ENTITY_MANAGER_FACTORY_ATTRIBUTE, emf);
    }
	
}
