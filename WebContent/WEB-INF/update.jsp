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
		<a href="<c:url value='/library/deconnect' />">D&eacute;connection</a>
		<br><br>
		<a href="<c:url value='/library/0' />">Retour à la librairie</a>
		
		</p>
	</aside>
	<section>

		<div id="corps">
		<h1>Modifier un livre</h1>
			<div id="secondtable">
			
			
				<table id="second">
					<tr>
						<th>Livre</th>
					</tr>
					<tr class="pair">
						
						<td class="left">
							<form action="<c:url value='/update'/>" method="POST" id="go" />
							<input form="go" type="hidden" name="id_book" id="id_book" value="${modif_book.id }" />
							<label for="titre">Titre</label><input form="go" type="text" name="title" id="title" maxlength="30" autofocus required="required" size="30" value="${modif_book.title }" />
							<label for="price">Prix</label><input form="go" type="text" name="price" id="price"  maxlength="6" required="required" size="6" value="${modif_book.price }" />
							<label for="availability">Disponible</label><input form="go" type="checkbox" name="availability" value="1" id="availibility" ${modif_book.availability=='1' ? 'checked':''} />
							<br>
							<ul>
							<c:if test="${modif_book.authors.size() == 0}">
								<li>Ajoutez au moins un auteur avec les formulaires ci-dessous</li>
							</c:if>
							<c:forEach items="${modif_book.authors}" var="pa">
								<li>${pa.firstname} ${pa.lastname}, ${pa.nativeCountry} <a href='<c:url value="/modif/${pa.firstname}/${pa.lastname}/supprime"/>' >X</a></li>
							</c:forEach>
							
							
							</ul>
							<button type="submit" form="go"><h2>Ok</h2></button>
							</form>
						</td>
					</tr>					
				</table>
				<table id="second">
					<tr>
						<th>Auteur connu</th>
					</tr>
					<tr class="pair">
										
						<td class="left">
						<form action="<c:url value='/update/connu'/>" method="POST" id="author_connu">
						
						<label for="country">Sélectionnez</label>
							<select name="id_author" form="author_connu">
							<c:forEach items="${authors}" var="a">
								<option value="${a.id}"> ${a.firstname} ${a.lastname}, ${a.nativeCountry} </option> 
							</c:forEach>
							</select>
						<button type="submit" form="author_connu">Envoyer</button>
						</form>
						
						</td>
					</tr>					
				</table>
				<table id="second">
					<tr>
						<th>Auteur inconnu</th>
					</tr>
					<tr class="pair">
										
						<td class="left">
						<form action="<c:url value='/update/inconnu'/>" method="POST" id="author_inconnu">
						<label for="firstname">Prénom</label><input type="text" name="firstname" id="firstname" form="author_inconnu" placeholder="Prénom" maxlength="10" required="required" size="10"/>
						<label for="lastname">Nom</label><input type="text" name="lastname" id="lastname" form="author_inconnu" placeholder="Nom" maxlength="22" required="required" size="15"/>
						<label for="country">Pays d'origine</label>
							<select id="country" name="country" form="author_inconnu">
								<c:forEach items="${countrys}" var="c">
								<option value="${c}"> ${c} </option> 
								</c:forEach>
							</select>
						<button type="submit" form="author_inconnu">Envoyer</button>
						</form>
						</td>
					</tr>					
				</table>

				
					<br>
					
				
			</div>	
		</div>
	</section>
	<aside2>
	</aside2>
	<footer>
	</footer>
		
</body>
</html>