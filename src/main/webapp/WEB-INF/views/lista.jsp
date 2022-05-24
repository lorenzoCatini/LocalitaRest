<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <c:if test="${chiaveLista.size() > 0} ">
 	<table>
 		<tr>
 			<th>citta</th>
 			<th>temperatura</th>
 		</tr>
 		<c:forEach var="localita" items="${chiaveLista}">
	 		<tr>
	 			<th>${localita.nome}</th>
	 			<th>${localita.temperatura}</th>
	 		</tr>
 		</c:forEach>
 	</table>
 </c:if>
 <c:if test="${chiaveLista.size() == 0} ">
 	<p>NESSUNA LOCALITA TROVATA</p>
 </c:if>
</body>
</html>