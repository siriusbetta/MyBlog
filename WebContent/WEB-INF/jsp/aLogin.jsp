<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
	.label{text-align: right;}
	.error{color: red}
</style>

</head>
<body>
<h1>Login</h1>

	<f:form method="post" action="/BlogJavaClass/login.htm" commandName="user" id="userLoginForm">  
    <label for="id">First Name</label>  
    <f:input path="id" id="id"/>  
    
    <br/>
    <br/>
      
    <label for="password">Password</label>  
    <f:password path="password" id="password"/>  
    
    <br/>
    <br/>
    
    <input type="submit" value = "Submit">
    
     
</f:form> 
</body>
</html>