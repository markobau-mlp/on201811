<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<ul>
			<li>
				<c:url value="/" var="homeUrl"/>
				<a href="${homeUrl}">Home</a>
			</li>
		</ul>
	</nav>	
	
	<%-- Commento JSP --%>
	<h1>List using if</h1>
	<c:if test="${empty prenotazioni}">
		<div>No results</div>
	</c:if>
	<c:if test="${!empty prenotazioni }">
		<c:forEach items="${prenotazioni}" var="prenotazione"
			varStatus="status">
			<div>${status.index}Codice: ${prenotazione.codPren}, Giorni:
				${prenotazione.nGiorni}</div>
		</c:forEach>
	</c:if>

	<h1>List using choose</h1>
	<c:choose>
		<c:when test="${!empty prenotazioni}">
			<c:forEach items="${prenotazioni}" var="prenotazione"
				varStatus="status">
				<div>${status.index}Codice: ${prenotazione.codPren}, Giorni:
					${prenotazione.nGiorni}</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>No results</div>
		</c:otherwise>
	</c:choose>

</body>
</html>
