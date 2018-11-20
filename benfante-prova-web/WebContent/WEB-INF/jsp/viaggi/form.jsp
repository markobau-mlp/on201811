<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Viaggio</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty viaggio}">
			<h1 style="text-align: center">Inserisci viaggio</h1>
		</c:when>
		<c:otherwise>
			<h1 style="text-align: center">Modifica viaggio</h1>
		</c:otherwise>
	</c:choose>
	<c:if test="${!empty error }">
		<div>${error}</div>
	</c:if>
	<c:url value="/Viaggi" var="saveViaggio" />
	<form action="${saveViaggio}" method="post">
		<c:set var="id" value="${viaggio.codViaggio}" />
		<c:set var="localita" value="${viaggio.localita}" />
		<c:set var="struttura" value="${viaggio.struttura}" />
		<c:set var="costo" value="${viaggio.costo}" />
		<c:if test="${empty viaggio}">
			<c:set var="id" value="${param.id}" />
			<c:set var="localita" value="${param.localita}" />
			<c:set var="struttura" value="${param.struttura}" />
			<c:set var="costo" value="${param.costo}" />
		</c:if>
		<input type="hidden" name="option" value="save" /> <input
			type="hidden" name="id" value="${id}">
		<div>
			Località: <input name="localita" value="${localita}">
		</div>
		<div>
			Struttura: <input name="struttura" value="${struttura}">
		</div>
		<div>
			Costo: <input name="costo" value="${costo}">
		</div>
		<div>
			<input type="submit" value="salva">
		</div>
	</form>
</body>
</html>