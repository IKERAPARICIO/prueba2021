package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Libro;

/**
 * Servlet implementation class ConsultaLibros
 */
@WebServlet("/ConsultaLibros")
public class ConsultaLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Constructor vacio 
    public ConsultaLibros() {
    }

    /**
     * Busca los libros encontrados en la Libreria segun los criterios especificados:
     *  - campo:'isbn' o 'titulo' a buscar
     *  - valor: caracteres que deben contener el isbn o titulo a buscar 
     */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        	String campo = "";
        	if (request.getParameter("isbn") != "" && request.getParameter("isbn") != null) {
        		campo = "isbn";
        	}
        	else {
        		campo = "titulo";
        	}
        	String valor = request.getParameter(campo);

        	Libro libro = new Libro();
        	
        	ArrayList<Libro> libros = libro.buscarLibros(campo, valor);
        	
        	StringBuilder result = new StringBuilder();
        	if (libros != null) {
        		result.append("<ul>");
				for (Libro l : libros) {
					result.append("<li>"+l.getTitulo()+" - "+l.getAutor()+" ("+l.getIsbn()+")</li>");
				}
				result.append("</ul>");
        	}
        	else {
        		result.append("Sin resultados.");
        	}
        	
            out.println("Resultado de la búsqueda con ISBN: " + result.toString());
        }
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
