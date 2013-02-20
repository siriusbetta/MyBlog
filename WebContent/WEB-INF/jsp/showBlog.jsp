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

<h1>My Blog</h1>
<div>
	<c:forEach items = "${blogs}" var = "blog">
			<h2><a href = "/BlogJavaClass/posts/${blog.permalink}.htm">${blog.title}</a></h2>
			<p>Posted ${blog.date} By ${blog.author}</p>
			
			<p>Comments: <a href = "/BlogJavaClass/posts/${blog.permalink}.htm">${fn:length(blog.comments)}</a></p>
			<hr>
			<p>${blog.body}</p>
		<p>Filled under:
		<a href = "/BlogJavaClass/tag/${blog.tags[0]}.htm">${blog.tags[0]}</a>
		
		<c:forEach var = "i" begin = "1" end = "${fn:length(blog.tags) - 1}">
			, <a href = "/BlogJavaClass/tag/${blog.tags[i]}.htm">${blog.tags[i]}</a>
		</c:forEach>
		<c:set var = "lastItem" value = "${fn:length(blog.tags)}"></c:set> 
			<a href = "/BlogJavaClass/tag/${blog.tags[lastItem]}.htm">${blog.tags[lastItem]}</a>
		</p>
	</c:forEach>
	
	<div id = "links">
	<c:if test="${beginItem != 0}"><a href = "/BlogJavaClass/blog/${(beginItem - 1)*10}.htm">Prev</a></c:if>
	<c:choose>
		<c:when test="${beginItem < 5}">
			<c:set var = "minusFive" value = "0"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var = "minusFive" value = "5"></c:set>
		</c:otherwise>
	</c:choose>
	
	
	<c:forEach var = "i" begin = "${beginItem - minusFive}" end = "${(endItem - 1)+5}">
	<a href = "/BlogJavaClass/blog/${i * 10}.htm">${i + 1}</a>
	
	</c:forEach>
	<c:if test="${endItem != (cnt/ 10)}">
	<a href = "/BlogJavaClass/blog/${endItem * 10 }.htm">Next</a>
	</c:if>
	</div>
</div>

</body>
</html>