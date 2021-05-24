package controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.negocio.Carrito;
import modelo.Libro;

/**
 * Servlet implementation class MiCarrito
 */
@WebServlet("/MiCarrito")
public class MiCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //Constructor vacio
    public MiCarrito() {
    }

    /**
     * Dependiendo la opcion indicada:
     * 	1 - compra un libro: lo agrega al carrito y muestra el carrito
     *  2 - agrega un libro al carrito: lo agrega y carga la pagina con el listado incluyendo el mensaje de resultado
     *  3 - muestra la pagina del carrito
     *  4 - elimina un libro del carrito: lo elimina del carrito y muestra el carrito
     */
	private void gestionarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//crear el carrito
		HttpSession sesion = request.getSession();
		Carrito miCarrito = (Carrito)sesion.getAttribute("miCarrito");
		if (miCarrito == null) {
			miCarrito = new Carrito();
			sesion.setAttribute("miCarrito", miCarrito);
		}
		
		switch (request.getParameter("opcion")) {
			case "1":
				incluirLibro(request, sesion, miCarrito);
				redirectToCarrito(request, response, miCarrito);
				break;
			case "2":
				incluirLibro(request, sesion, miCarrito);
				request.setAttribute("mensaje","Se ha incluido el libro.");
				RequestDispatcher vista = request.getRequestDispatcher("index.jsp");
				vista.forward(request, response);
				break;
			case "3":
				redirectToCarrito(request, response, miCarrito);
				break;
			case "4":
				eliminarLibro(request, sesion, miCarrito);
				redirectToCarrito(request, response, miCarrito);
				break;
			default:
				System.out.println("Opcion no valida.");
		}
	}

	//llama a la pagina del carrito con miCarrito actualizado
	private void redirectToCarrito(HttpServletRequest request, HttpServletResponse response, Carrito miCarrito) throws ServletException, IOException {
		request.setAttribute("miCarrito",miCarrito);
		RequestDispatcher vista = request.getRequestDispatcher("carrito.jsp");
		vista.forward(request, response);
	}

	//incluye el libro con el id pasado al carrito
	private void incluirLibro(HttpServletRequest request, HttpSession sesion, Carrito miCarrito) {
		int id = Integer.parseInt(request.getParameter("id"));
		Libro libro = new Libro();
		libro.buscarID(id);
		miCarrito.agregarLibro(libro);
        sesion.setAttribute("miCarrito", miCarrito);
	}
	
	//elimina el libro con el id pasado del carrito
	private void eliminarLibro(HttpServletRequest request, HttpSession sesion, Carrito miCarrito) {
		int id = Integer.parseInt(request.getParameter("id"));
		Libro libro = new Libro();
		libro.buscarID(id);
		miCarrito.eliminarLibro(libro);
        sesion.setAttribute("miCarrito", miCarrito);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionarCarrito(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gestionarCarrito(request, response);
	}

}
