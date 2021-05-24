package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Libro;
import singleton.DBConnection;

public class DAOLibro {
	private Connection con = null;

	public static DAOLibro instance = null;

	public DAOLibro() throws SQLException {
		con = DBConnection.getConnection();
	}

	public static DAOLibro getInstance() throws SQLException {
		if (instance == null)
			instance = new DAOLibro();
		return instance;
	}
	
	public void insert(Libro l) throws SQLException {
		try {
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO libro (isbn, titulo, autor, precio) VALUES (?,?,?,?)");
			ps.setString(1, l.getIsbn());
			ps.setString(2, l.getTitulo());
			ps.setString(3, l.getAutor());
			ps.setFloat(4, l.getPrecio());
			ps.executeUpdate();
			ps.close();
		 } catch (NumberFormatException e) {
			 System.out.println("Ha introcido mal el formato de numero!!");
		 }
	}
	
	public void delete(int id) throws SQLException {
		PreparedStatement ps = con.prepareStatement("DELETE FROM libro WHERE id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}
	
	public void updatePrice(Libro l, float price) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE libro SET precio = ? WHERE id = ?");
		ps.setFloat(1, price);
		ps.setInt(2, l.getId());

		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Busca los libros que coinciden con los caracteres pasados
	 * @param campo: campo por el que realizar la busqueda: 'isbn' o 'titulo'
	 * @param valor: caracteres que debe contener el campo indicado
	 * @return listado de libros que coinciden con la busqueda
	 * @throws SQLException
	 */
	public ArrayList<Libro> findLibros(String campo, String valor) throws SQLException {	
		PreparedStatement ps;
		if ("isbn".equals(campo)) {
			ps = con.prepareStatement("SELECT * FROM libro WHERE isbn LIKE ?");
		}
		else {
			ps = con.prepareStatement("SELECT * FROM libro WHERE titulo LIKE ?");
		}
		ps.setString(1, "%" + valor + "%");
		ResultSet rs = ps.executeQuery();
		ArrayList<Libro> result = null;		
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();
				result.add(new Libro(rs.getInt("id"), rs.getString("isbn"), rs.getString("titulo"),
										rs.getString("autor"), rs.getFloat("precio")));
		}
		
		rs.close();
		ps.close();
		return result;
	}
	
	public ArrayList<Libro> listaLibros() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * from libro");
		ResultSet rs = ps.executeQuery();
		ArrayList<Libro> result = null;
		
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();
				result.add(new Libro(rs.getInt("id"), rs.getString("isbn"), rs.getString("titulo"),
										rs.getString("autor"), rs.getFloat("precio")));
		}
		
		rs.close();
		ps.close();
		return result;
	}
	
	public Libro finID(int id) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM libro WHERE id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Libro result = null;
		if (rs.next()) {
			result = new Libro(rs.getInt("id"), rs.getString("isbn"), rs.getString("titulo"),
					rs.getString("autor"), rs.getFloat("precio"));
		}
		rs.close();
		ps.close();
		return result;
	}
}
