<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cerca viaggi</title>
</head>
<body>
	<h1 style="text-align: center">Cerca Viaggi</h1>
	<c:if test="${!empty message}">
		<div>${message}</div>
	<%--	<c:remove var="message"/> --%>
	</c:if>
	<c:if test="${!empty error }">
		<div>${error}</div>
	</c:if>
	<div style="dispay: inline">
		<c:url value="/Viaggi" var="cercaUrl" />
		<form action="${cercaUrl}" method="get">
			<input type="hidden" name="option" value="cerca"> <input
				type="text" name="id" value="${param.id}">
				<input type="submit" value="cerca">
			<c:url value="/Viaggi?option=nuovo" var="nuovoUrl" />
			<a href="${nuovoUrl}">Nuovo viaggio</a>
		</form>
	</div>
	<c:if test="${!empty viaggio }">
		<div>
			<div>Codice: ${viaggio.codViaggio}</div>
			<div>Località: ${viaggio.localita}</div>
			<div>Struttura: ${viaggio.struttura}</div>
			<div>Costo: ${viaggio.costo}</div>
		</div>
		<div>
			<c:url value="/Viaggi" var="editUrl">
				<c:param name="option">modifica</c:param>
				<c:param name="id">${viaggio.codViaggio}</c:param>
			</c:url>
			<div>${editUrl}</div>
			<a href="${editUrl}">Modifica viaggio</a>
		</div>
	</c:if>
</body>
</html>