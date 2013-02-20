<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type = "text/css" rel="stylesheet" href="/BlogJavaClass/css/general.css">
<title>The Java Class Blog</title>
</head>
<body>


<c:choose>
	<c:when test="${sessionScope.sesAtrrUserId != null}">Welcome ${sessionScope.sesAtrrUserId} | <a href = "/BlogJavaClass/logout.htm">Logout</a>
	| <a href = "/BlogJavaClass/newpost.htm">New post</a>
	</c:when>
	<c:otherwise><a href = "/BlogJavaClass/login.htm">Login</a> | <a href = "/BlogJavaClass/registration.htm">Signup</a></c:otherwise>
</c:choose>
| <a href = "/BlogJavaClass/">Blog page</a>

<h2>Search page by "${tag}". Return ${size} posts</h2>
<div>
	<c:forEach items = "${blogs}" var = "blog">
			<h2><a href = "/BlogJavaClass/posts/${blog.permalink}.htm">${blog.title}</a></h2>
			<p>Posted ${blog.date} By ${blog.author}</p>
			
			<p>Comments: <a href = "/BlogJavaClass/posts/${blog.permalink}.htm">${fn:length(blog.comments)}</a></p>
			<hr>
			
		<p>Filled under:
		<a href = "/BlogJavaClass/tag/${blog.tags[0]}.htm">${blog.tags[0]}</a>
		
		<c:forEach var = "i" begin = "1" end = "${fn:length(blog.tags) - 1}">
			, <a href = "/BlogJavaClass/tag/${blog.tags[i]}.htm">${blog.tags[i]}</a>
		</c:forEach>
		<c:set var = "lastItem" value = "${fn:length(blog.tags)}"></c:set> 
			<a href = "/BlogJavaClass/tag/${blog.tags[lastItem]}.htm">${blog.tags[lastItem]}</a>
		</p>
	</c:forEach>
	</div>
</body>
</html>