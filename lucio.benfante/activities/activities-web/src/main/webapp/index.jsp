<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<c:set var="homeMenuActivation" value="true"/>
<!doctype html>
<html lang="en">
<head>

<title><fmt:message key="application.name" /></title>
<%@include file="/WEB-INF/jsp/head.jspf"%>

</head>

<body>

	<%@include file="/WEB-INF/jsp/header.jspf"%>

	<!-- Begin page content -->
	<main role="main" class="container"> <!--  Here the content of the page -->
	</main>
	<%@include file="/WEB-INF/jsp/footer.jspf"%>
	<%@include file="/WEB-INF/jsp/javascript.jspf"%>
</body>
</html>
