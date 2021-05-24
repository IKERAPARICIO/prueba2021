package app.negocio;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.Libro;

/**
 * Clase carrito que contiene un ArrayList de Libros
 * Implementa al interfaz Iterable para poder recorrer el objeto
 */
public class Carrito implements Iterable<Libro>{
	private ArrayList<Libro> miCarrito;
	
	public Carrito() {
		miCarrito = new ArrayList<Libro>();
	}
	
	/**
	 * Agrega un libro en el carrito
	 * @param l: libro a agregar
	 */
	public void agregarLibro(Libro l) {
		miCarrito.add(l);
	}

	/**
	 * Suma el importe de los libros que contiene el carrito
	 * @return importe total
	 */
	public double verImporte() {
		double importe = 0;
		for(Libro l : miCarrito) {
			importe += l.getPrecio();
		}
		return importe;
	}

	/**
	 * Elimina un libro del carrito
	 * @param l: libro a eliminar
	 */
	public void eliminarLibro(Libro l) {	
		for (int i = 0; i < miCarrito.size(); i++) {
			if (miCarrito.get(i).getId() == l.getId()) {
				miCarrito.remove(i);
			}
		}
	}

	@Override
	public Iterator<Libro> iterator() {
		// TODO Auto-generated method stub
		return miCarrito.iterator();
	}
}
