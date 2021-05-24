<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import = "app.negocio.Carrito" %>
<%@ page import = "modelo.Libro" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi carrito</title>
</head>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<body>
	<div id="contenedor">
		<%@include file="/includes/header.inc.jsp"%>
		<%@include file="/includes/nav.inc.jsp"%>
		
		<section>
			<h1>Carrito de la compra</h1>
			<table>
				<tr>
					<th>TÍTULO</th>
					<th>AUTOR</th>
					<th>PRECIO</th>
					<th></th>
				</tr>
				<%
				Carrito miCarrito = (Carrito)request.getAttribute("miCarrito");
				if(miCarrito != null){
					for (Libro l : miCarrito){
					%>
						<tr>
							<td><%=l.getTitulo()%></td>
							<td><%=l.getAutor()%></td>
							<td><%=l.getPrecio()%></td>
							<td><a href="MiCarrito?opcion=4&id=<%=l.getId()%>">Eliminar</a></td>
						</tr>
					<%
				    }
					%>
					<tr>
						<td></td>
						<td></td>
						<td>Importe Total</td>
						<td><%=String.format("%1.2f", miCarrito.verImporte())%></td>
					</tr><%
				}
				%>
			</table>
			<%@include file="/includes/msg.inc.jsp"%>
		</section>
		<%@include file="/includes/footer.inc.jsp"%>
	</div>
</body>
</html>