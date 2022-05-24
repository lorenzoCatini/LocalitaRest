<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>crea localita
</title>
</head>
<body>
	<form:form action="/inserisci" metod="post" modelAttribute="chiaveInserimento">
		INSERISCI NOME:
		<form:input path="nome"/>
		<br>
		INSERISCI TEMPERATURA:
		<form:input path="temperatura"/>
		<br>
	</form:form>

</body>
</html>