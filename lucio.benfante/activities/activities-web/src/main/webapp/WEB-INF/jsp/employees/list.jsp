<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<c:set var="employeesMenuActivation" value="true"/>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.Employees" /></title>
<%@ include file="/WEB-INF/jsp/head.jspf"%>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/header.jspf"%>

	<!-- Begin page content -->
	<main role="main" class="container">

	<h1>
		<fmt:message key="label.Employees" />
	</h1>
	<table class="table" border="1" style="text-align:center">
		<thead class="thead-dark">
			<tr>
				<th></th>
				<th><fmt:message key="label.FirstName" /></th>
				<th><fmt:message key="label.LastName" /></th>
				<th><fmt:message key="label.Action"/></th>
			</tr>
		</thead>
		<tbody">
			<c:forEach items="${employees}" var="employee" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td>
						<div class="dropdown">
						<a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    <fmt:message key="label.ActionButton"/>
						</a>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					    <a class="dropdown-item" href="#"><fmt:message key="label.Modify"/></a>
					    <a class="dropdown-item" href="#"><fmt:message key="label.Delete"/></a>
						</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	</main>

	<%@ include file="/WEB-INF/jsp/footer.jspf"%>

	<%@ include file="/WEB-INF/jsp/javascript.jspf"%>

</body>
</html>