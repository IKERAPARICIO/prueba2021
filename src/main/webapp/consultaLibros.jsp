<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<meta charset="ISO-8859-1">
<title>Resultado de la consulta</title>
</head>
<script type="text/javascript">
 	 // Uso de AJAX para consultar el listado de libros cada vez que se escribe un caracter,
 	 // se puede realizar la busqueda por ISBN o Titulo
 	 
     // Variable que almacena la referencia del objeto XMLHttpRequest
     var xhr;

     function buscar(campo) {
         // Para Explorer 6 y anteriores
         if (window.ActiveXObject)
             xhr = new ActiveXObject("Microsoft.XMLHttp");
         // Resto de navegadores
         else if ((window.XMLHttpRequest) ||
                 (typeof XMLHttpRequest) != undefined)
             xhr = new XMLHttpRequest();
         else {
             alert("Su navegador no soporta AJAX");
             return;
         }
         // Si ya tenemos el objeto enviamos la peticion asincrona
         enviarPeticion(campo);
     }

     function enviarPeticion(campo) {
         // Obtener la referencia a la lista de los titulos
         var url = "";

         if (campo == "isbn"){
        	 url = "ConsultaLibros?isbn=" + document.getElementById("isbn").value;
         }
         else{
        	 url = "ConsultaLibros?titulo=" + document.getElementById("titulo").value;
         }

         // preparar la peticion
         xhr.open("GET", url, true);

         // Informar de la funcion que procesara el resultado
         xhr.onreadystatechange = procesarRespuesta;

         // Enviar la peticion
         xhr.send();
     }

     function procesarRespuesta() {
         // Condicionamos a que solo se ejecute al 
         // recibir la respuesta completa
         if (xhr.readyState == 4) {
             document.getElementById("resultado").innerHTML =
                     xhr.responseText;
         }
     }
 </script>
<body>
	<div id="contenedor">
		<%@include file="/includes/header.inc.jsp"%>
		<%@include file="/includes/nav.inc.jsp"%>

		<section>
			<h1>Consultas</h1>
			
			<input type="text" name="isbn" id="isbn" placeholder="Inserte el ISBN a buscar" onkeyup="buscar('isbn')">
			<!-- <button onclick="buscar('isbn')">Busca por ISBN</button>  -->
			<input type="text" name="titulo" id="titulo" placeholder="Inserte el Título a buscar" onkeyup="buscar('titulo')">
			<!-- <button onclick="buscar('titulo')">Busca por Título</button>  -->
			
			<div id="resultado" style="margin: 10px; color: darkmagenta"> 
		    </div>
		</section>   
    		
    	<%@include file="/includes/footer.inc.jsp"%>
	</div>
</body>
</html>