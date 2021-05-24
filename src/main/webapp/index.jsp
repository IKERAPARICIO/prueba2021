<%@page import="modelo.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<meta charset="ISO-8859-1">
<title>Listado de Libros</title>
</head>
<script type="text/javascript">
	//ccomprueba que se haya marcado antes de eliminarlo
	function checkEliminar() {
		return checkMarcado();
	}

	//comprueba que se haya marcado y recoge el nuevo valor
	function checkModificar() {
		var result = false;
		if (checkMarcado()){
			var precio = prompt("Introduzca el nuevo precio para el libro: ", "");
			if (precio != null) {
				document.getElementById('opcion').value = "3";
				document.getElementById('precio').value = precio;
				result = true;
			}
		}
		return result;
	}

	//comprueba que haya marcado un solo elemento
    function checkMarcado() {
    	var checkedBoxes = getCheckedBoxes("id");
    	if (checkedBoxes != null) {
    		if (checkedBoxes.length > 1){
    			alert("Solo puede seleccionar un libro!");
        		return false;
    		}
    		else{
        		return true;
    		}
    	}
    	else {
    		alert("No hay ningún libro seleccionado!");
    		return false;
    	}
    }
    
 	//Devuleve los checkBox que tengan como nombre el valor pasado 
    function getCheckedBoxes(chkboxName) {
      	var checkboxes = document.getElementsByName(chkboxName);
	    var checkboxesChecked = [];
	    // loop over them all
	    for (var i=0; i<checkboxes.length; i++) {
	    	// And stick the checked ones onto an array...
	        if (checkboxes[i].checked) {
	        	checkboxesChecked.push(checkboxes[i]);
	        }
	    }
      	// Return the array if it is non-empty, or null
      	return checkboxesChecked.length > 0 ? checkboxesChecked : null;
    }
</script>
<body>
	<div id="contenedor">
		<%@include file="/includes/header.inc.jsp"%>
		<%@include file="/includes/nav.inc.jsp"%>
		
		<section>
			<h1>Listado de Libros</h1>
			<form action="GestionLibros" method="post">
				<table>
					<tr>
						<th></th>
						<th>ISBN</th>
						<th>TÍTULO</th>
						<th>AUTOR</th>
						<th>PRECIO</th>
						<th></th>
						<th></th>
					</tr>
					<%
					Libro libro = new Libro();
					if (libro.obtenerLibros() != null){
						for (Libro l : libro.obtenerLibros()) {
						%>
						<tr>
							<td><input type="checkbox" name="id" id="id" value="<%=l.getId()%>"></td>
							<td><%=l.getIsbn()%></td>
							<td><%=l.getTitulo()%></td>
							<td><%=l.getAutor()%></td>
							<td><%=l.getPrecio()%></td>
							<td><a href="MiCarrito?opcion=1&id=<%=l.getId()%>">Comprar</a></td>
							<td><a href="MiCarrito?opcion=2&id=<%=l.getId()%>"><img src="img/carrito.png" alt="Agregar al Carrito"></a></td>
						</tr>
						<%
						}
					}
					%>
				</table>
				<input type="hidden" name="opcion" id="opcion" value="2">
				<input type="hidden" name="precio" id="precio" value="">
				
				<input type="submit" value="Eliminar" onclick="return checkEliminar();">
				<input type="submit" value="Modificar Precio" onclick="return checkModificar();">		
			</form>
			<%@include file="/includes/msg.inc.jsp"%>
		</section>
		<%@include file="/includes/footer.inc.jsp"%>
	</div>
</body>
</html>