<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta Libro</title>
</head>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    function validarDatos() {
    	if (document.getElementById("isbn").value == "" || document.getElementById("titulo").value == "" ||
    			document.getElementById("autor").value == "" || document.getElementById("precio").value == ""){
    		alert("Todos los campos son obligatorios!");
    		return false;
    	}
    	else{
    		return true;
    	}
    }
</script>
<body>
	<div id="contenedor">
		<%@include file="/includes/header.inc.jsp"%>
		<%@include file="/includes/nav.inc.jsp"%>
		
		<section>
			<h1>Alta Libro</h1>
			<form name="libro" action="GestionLibros" method="post">
				<label for="isbn">ISBN:</label><input type="text" name="isbn" id="isbn"><br>
				<label for="titulo">Título:</label><input type="text" name="titulo" id="titulo"><br>
				<label for="autor">Autor:</label><input type="text" name="autor" id="autor"><br>
				<label for="precio">Precio:</label><input type="text" name="precio" id="precio"><br>
		
				<input type="hidden" name="opcion" value="1">
				<input type="submit" name="guardar" value="Guardar" onclick="return validarDatos();">
			</form>
			<%@include file="/includes/msg.inc.jsp"%>
		</section>
		<%@include file="/includes/footer.inc.jsp"%>
	</div>
</body>
</html>