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
		<p>Bienvenue <c:out value="${user.firstname} ${user.lastname}" /><br>
		<a href="<c:url value='/home/deconnect' />">D&eacute;connection</a>
		</p>
	
		<br>
 		<c:if test="${user.role.toString() == 'ADMIN' }">		
			<div id="ajout">
				<a href="<c:url value='/ajout' />">Ajouter un livre</a>
			</div>
		</c:if> 
		
		<br>
		<br>
	</aside>
	<section>
		<br>
		<div id="secondtable">
			<table id="second">
				<tr>
					<th>Titre</th>
					<th>Auteur</th>
					<th>Prix</th>
					<th>Disponibilit&eacute;</th>
					<c:if test="${user.role.toString() == 'ADMIN' }">
					<th>Actions</th>
					</c:if>
				</tr>
				<c:forEach items="${books}" var="book" varStatus="stat">
				
					<tr class="${stat.index%2==0 ? 'pair':'unpair'}">
						<td class="left"><c:out value="${book.title}"></c:out></td>
						<td class="left">
							<c:forEach items="${book.authors}" var="author"> 
								<p><c:out value="${author.firstname += ' ' += author.lastname}" /></p>					
							</c:forEach>
						</td>
						<td class="center"><c:out value="${book.price}"></c:out></td>
						<td class="center"><c:out value="${book.availability=='1' ? 'Non':'Oui'}"></c:out></td>
						<c:if test="${user.role.toString() == 'ADMIN' }">								
							<td class="center">
								<a href='<c:url value="/update/${book.id}" />'>Modifier</a> - 
								<a href='<c:url value="/delete/${book.id}" />'>Supprimer</a>
							</td>
						</c:if>
					
					</tr>
					
				</c:forEach>
				
				<!-- <tr class="unpair">
					<td class="left">La bête humaine</td>
					<td class="left">Emile Zola</td>
					<td class="center">8,00 €</td>
					<td class="center">Oui</td>
				</tr>
				<tr class="pair">
					<td class="left">Le comte de Monte-Christo</td>
					<td class="left">Alexandre Dumas</td>
					<td class="center">10,00 €</td>
					<td class="center">Non</td>
				</tr>
				<tr class="unpair">
					<td class="left">L'aiguille creuse</td>
					<td class="left">Maurice Leblanc</td>
					<td class="center">8,00 €</td>
					<td class="center">Oui</td>
					
				</tr>-->
			</table>
			<p><div id="ajout">
				<c:if test="${nb_page != 0}">
					<a href='<c:url value="/library/last" />'>< Pr&eacute;c&eacute;dent</a> 			
				</c:if>
				<c:if test="${page == 'next'}">
					<a href='<c:url value="/library/next" />'>Suivant ></a>			
				
				</c:if>
			</div></p>
		</div>
	</section>
	<aside2>
	</aside2>
	<footer>
	</footer>
		
</body>
</html>