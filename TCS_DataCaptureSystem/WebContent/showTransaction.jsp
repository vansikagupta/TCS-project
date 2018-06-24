<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="tcs.groupB.Master" %>
    <%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Hello</h2>
	<%
	ArrayList<Master> list = (ArrayList<Master>)request.getAttribute("MasterRow");

	// print the information about every category of the list
	for (Master M:list)
	{
	    out.println(M);
	    out.println("<br/>");
	}
	%>
	
</body>
</html>