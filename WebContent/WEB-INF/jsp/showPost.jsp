<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My blog - ${blog.title}</title>
</head>
<body>

<c:choose>
	<c:when test="${sessionScope.sesAtrrUserId != null}">Welcome ${sessionScope.sesAtrrUserId} | <a href = "/BlogJavaClass/logout.htm">Logout</a></c:when>
	<c:otherwise><a href = "/BlogJavaClass/login.htm">Login</a> | <a href = "/BlogJavaClass/registration.htm">Signup</a></c:otherwise>
</c:choose>
|<a href = "/BlogJavaClass/">Blog home</a>

<h2>${blog.title}</h2>
Posted ${blog.date} <i> By ${blog.author}</i><br>
<hr>
${blog.body}
<br>

<em>Filled Under: </em>
<a href = "/BlogJavaClass/tags/${blog.tags[0]}">${blog.tags[0]}</a>
<c:forEach var = "i" begin = "1" end = "${fn:length(blog.tags)-1}">
, <a href = "/BlogJavaClass/tags/${blog.tags[i]}">${blog.tags[i]}</a>
</c:forEach>
<c:set var = "lastItem" value = "${fn:length(blog.tags)}"></c:set> 
<a href = "/BlogJavaClass/tag/${blog.tags[lastItem]}">${blog.tags[lastItem]}</a>
<p>
Comments:
<c:if test="${fn:length(blog.comments) != 0}">

<ul>
	<c:forEach var = "i" begin = "0" end = "${fn:length(blog.comments) - 1}">
		Author: ${blog.comments[i].author}<br>
		email: ${blog.comments[i].email}<br>
		<br>
		${blog.comments[i].body}<br>
		<hr>
	</c:forEach>
</ul>
</c:if>
<form:form method = "post" action = "/BlogJavaClass/addComment.htm" commandName = "comment">
	<table>
		<tr>
			<td>
				<form:hidden path="permalink" value = "${blog.permalink}"/>
			</td>
		</tr>
		<tr>
			<td>Name: </td>
		</tr>
		<tr>
			<td>
				<form:input path="author"/>
			</td>
		</tr>
		<tr>
			<td>email: </td>
		</tr>
		<tr>
			<td>
				<form:input path="email"/>
			</td>
		</tr>
		<tr>
			<td>Comment: </td>
		</tr>
		<tr>
			<td>
				<form:textarea path="body" cols="60" rows="10"/>
			</td>
		</tr>
	</table>
<input type = "submit" value = "Submit"/>
</form:form>
</body>
</html>