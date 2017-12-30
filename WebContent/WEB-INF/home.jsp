<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style2.css" />
<title>Test library</title>
</head>
<body>
	<table>
	<tr>
		<th>Prénom</th>
		<th>Nom</th>
		<th>Pays d'origine</th>
	</tr>
		<c:forEach items="${authors}" var="author">
	<tr>
		<td>${author.firstname}</td><%--$author.nom--%>
		<td>${author.lastname}</td><%--$author.prenom--%>
		<td>${author.nativeCountry }</td><%--$author.country--%>
	</tr>	
		</c:forEach>
		
	</table>

</body>
</html>