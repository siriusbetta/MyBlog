<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User registration</title>
</head>
<body>
	<form:form method = "post" action = "/BlogJavaClass/registration.htm" commandName = "registration">
		<table>
			<tr>
				<td>User name:<font color = "red"><form:errors path = "id" /></font></td>
			</tr>
			<tr>
				<td><form:input path = "id" /></td>
			</tr>
			
			<tr>
				<td>Password: <font color = "red"> <form:errors path = "password"/></font></td>
			</tr>
			<tr>
				<td><form:password path = "password"/></td>
			</tr>
			
			<tr>
				<td>Confirm password: <font color = "red" > <form:errors path = "confirmPassword"/></font></td>
			</tr>
			<tr>
				<td><form:password path = "confirmPassword"/></td>
			</tr>
			
			<tr>
				<td><input type = "submit" value = "Submit"/></td>
			</tr>
		
		</table>
	
	</form:form>
</body>
</html>