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
			<h1>Connection à la librairie</h1>
			<form action="<c:url value='/library/0'/>" method="POST">
			<div id="secondtable">
				<table id="second">
					<tr>
						<th>Login</th>
						<th>Mot de passe</th>
					</tr>
					<tr class="pair">
						
						<td class="center" ><input type="text" name="pseudo" id="pseudo" placeholder="Mail ou pseudo" maxlength="30" autofocus required="required" size="30"/></td>
						<td class="center" ><input type="text" name="pwd" id="pwd" placeholder="******" maxlength="12" required="required" size="10"/></td>
						
					</tr>
					
				</table>
				<br>
				<button type="submit"><h2>Ok</h2></button>
			</div>	
			</form>
			</div>
	</section>
	<aside2>
	</aside2>
	<footer>
	</footer>
		
</body>
</html>