<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New post</title>
</head>
<body>
<h2>New post</h2>
	<form:form method = "post" action = "/BlogJavaClass/newpost.htm" commandName = "post">
		Today: ${post.date}<br>
		
		<table>
			<tr>
				<td>Title:</td>
				<td><form:input path="title"/></td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><form:hidden path="author" value = "${sessionScope.sesAtrrUserId}"/></td>
			</tr>
			<tr>
				<td>Text: </td>
				<td><form:textarea path = "body" cols = "60" rows = "10"/></td>
			</tr>
			<tr>
				<td>Tags:</td>
				<td><form:input path="tags"/></td>
			</tr>
		</table>
		
		<form:hidden path="permalink" value = "${post.permalink}"/>
		<input type = "submit" value = "Submit"/>
	</form:form>
	
	

</body>
</html>