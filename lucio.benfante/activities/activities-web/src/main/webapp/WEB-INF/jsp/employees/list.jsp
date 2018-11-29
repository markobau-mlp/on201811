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
	<table border="1">
		<thead>
			<tr>
				<th></th>
				<th><fmt:message key="label.FirstName" /></th>
				<th><fmt:message key="label.LastName" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">
						<table>
							<c:forEach items="${employee.timeSpents}" var="timeSpent">
								<tr>
									<td>${timeSpent.start}</td>
									<td>${timeSpent.end}</td>
									<td>${timeSpent.activity.customer.name}</td>
									<td>${timeSpent.activity.description}</td>
								</tr>
							</c:forEach>
						</table>
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