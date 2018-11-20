<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align:center">Viaggi</h1>
	<div style="dispay:inline">
	<form action="Viaggi?option=cerca" method="post">
		<input type="text" name="id" >
		<input type="submit" value="cerca">
	</form>
	<br>
	<form action="Viaggi?option=inserisci" method="post">
		<input type="text" name="id1">
		<input type="text" name="id2">
		<input type="text" name="id3">
		<input type="submit" value="inserisci">
	</form>
	<br>
	<form action="Viaggi?option=rimuovi">
		<input type="text" name="id4">
		<input type="submit" value="rimuovi">
	</form>
	<br>
	<form action="Viaggi?option=aggiorna">
		<input type="text" name="id5">
		<input type="text" name="id6">
		<input type="text" name="id7">
		<input type="submit" value="aggiorna">
	</form>
	</div>

</body>
</html>