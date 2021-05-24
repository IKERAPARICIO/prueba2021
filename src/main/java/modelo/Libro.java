package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOLibro;

public class Libro {
	private int id;
	private String isbn;
	private String titulo;
	private String autor;
	private float precio;
	
	public Libro() {
	}
	
	public Libro(int id) {
		this.setId(id);
	}
	
	public Libro(int id, String isbn, String titulo, String autor, float precio) {
		this.setId(id);
		this.setIsbn(isbn);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setPrecio(precio);
	}
	
	public Libro(String isbn, String titulo, String autor, float precio) {
		this.setIsbn(isbn);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setPrecio(precio);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		if (precio < 0) {
			System.out.println("El precio no puede ser negativo.");
			precio = 0;
		}
		this.precio = precio;
	}

	public void insertar() {
		try {
			DAOLibro.getInstance().insert(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminar(int id) {
		try {
			DAOLibro.getInstance().delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarPrecio(float precio) {
		try {
			DAOLibro.getInstance().updatePrice(this, precio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Libro> buscarLibros(String campo, String valor) {
		ArrayList<Libro> lista = null;
		try {
			lista = DAOLibro.getInstance().findLibros(campo, valor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Libro> obtenerLibros() {
		ArrayList<Libro> lista = null;
		try {
			lista = DAOLibro.getInstance().listaLibros();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void buscarID(int id) {
		Libro l = null;
		try {
			l = DAOLibro.getInstance().finID(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (l != null) {
			this.id = l.getId();
			this.isbn = l.getIsbn();
			this.titulo = l.getTitulo();
			this.autor = l.getAutor();
			this.precio = l.getPrecio();
		}
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio
				+ "]";
	}
}
