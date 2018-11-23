package com.corso.activities.web.util;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class JpaUtils {
	public static EntityManager getEntityManager(HttpServletRequest request) {
		return (EntityManager) request.getAttribute(Constants.ENTITY_MANAGER_ATTRIBUTE);
	}
}
