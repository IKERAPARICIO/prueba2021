package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Libro;

/**
 * Servlet implementation class GestionLibros
 */
@WebServlet("/GestionLibros")
public class GestionLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //Constructor vacio
    public GestionLibros() {
    }

    /**
     * Dependiendo la opcion indicada, da de alta un libro, lo elimina o modifica su precio
     */
	private void procesarLibros(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		switch (request.getParameter("opcion")) {
			case "1":
				altaLibro(request, response);
				break;
			case "2":
				eliminarLibro(request, response);
				break;
			case "3":
				modificarPrecio(request, response);
				break;
			default:
				System.out.println("Opcion no valida.");
		}
	}

	/**
	 * Da de alta el libro segun los parametros pasados y vuelve a la pagina de alta con un mensaje de resultado.
	 */
	private void altaLibro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String msg = "Libro incluido.";
		try {
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			float precio = Float.parseFloat(request.getParameter("precio"));
			
			Libro libro = new Libro(isbn, titulo, autor, precio);
			libro.insertar();
		} catch (NumberFormatException e) {
			msg = "ERROR al introducir el libro.";
		}
		
		request.setAttribute("mensaje",msg);
		RequestDispatcher vista = request.getRequestDispatcher("altaLibro.jsp");
		vista.forward(request, response);
	}
	
	/**
	 * Elimina el libro que tiene el id pasado y vuelve a la pagina del listado de libros con un mensaje de resultado.
	 */
	private void eliminarLibro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String msg = "Libro eliminado.";
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Libro libro = new Libro();
			libro.eliminar(id);
		
		} catch (Exception e) {
			msg = "ERROR al eliminar el libro.";
		}
		
		request.setAttribute("mensaje",msg);
		RequestDispatcher vista = request.getRequestDispatcher("index.jsp");
		vista.forward(request, response);
	}
	
	/**
	 * Modifica el precio del libro que tiene el id pasado y vuelve a la pagina del listado de libros con un mensaje de resultado.
	 */
	private void modificarPrecio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String msg = "Precio modificado.";
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			float precio = Float.parseFloat(request.getParameter("precio"));
			
			Libro libro = new Libro(id);
			libro.modificarPrecio(precio);
		} catch (NumberFormatException e) {
			msg = "ERROR al modificar el precio.";
		}
		
		request.setAttribute("mensaje",msg);
		RequestDispatcher vista = request.getRequestDispatcher("index.jsp");
		vista.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarLibros(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarLibros(request, response);
	}

}
