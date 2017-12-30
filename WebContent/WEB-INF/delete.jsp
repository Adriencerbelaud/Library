<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value='../style2.css'/>" />
<link rel="stylesheet" href="<c:url value='../style_page.css'/>" />

<title>Librairie LIMOISSA</title>
</head>
<body>
	<header>
		<h1>Librairie Limoissa</h1>
	</header>
	<aside>
	</aside>
	<section>

		<div id="corps">
		<h1>Validation</h1>
		<center>
		<p>Le livre a bien été supprimé.</p>
		<br><br>
		<a href="<c:url value='/library/0'/>">Retourner à la liste des livres</a> -
		<a href="<c:url value='/ajout'/>">Ajouter un nouveau livre</a>
		
		</center>
	</section>
	<aside2>
	</aside2>
	<footer>
	</footer>
		
</body>
</html>