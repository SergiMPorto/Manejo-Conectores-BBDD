package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroSQL implements DaoPasajero{

	private Connection conexion;

	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/actividad02";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean nuevoPasajero(Pasajero p) {
	    if (!abrirConexion()) {
	        return false; 
	    }
	    String query = "INSERT INTO pasajero (nombre, edad, peso) VALUES (?, ?, ?)";
	    try (PreparedStatement ps = conexion.prepareStatement(query)) {
	        ps.setString(1, p.getNombre());
	        ps.setInt(2, p.getEdad());
	        ps.setFloat(3, p.getPeso());
	        int filasAfectadas = ps.executeUpdate();
	        return filasAfectadas > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        cerrarConexion(); 
	    }
	}


	@Override
	public boolean borrarPasajero(int id) {
	    if (!abrirConexion()) {
	        return false; // Si la conexión no se pudo abrir, devuelve false
	    }
	    String query = "DELETE FROM pasajero WHERE id = ?";
	    try (PreparedStatement ps = conexion.prepareStatement(query)) {
	        ps.setInt(1, id);
	        int filasAfectadas = ps.executeUpdate();
	        return filasAfectadas > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        cerrarConexion(); // Asegúrate de cerrar la conexión incluso si ocurre una excepción
	    }
	}


	@Override
	public Pasajero consultarPasajero(int id) {
	    if (!abrirConexion()) {
	        return null;
	    }
	    
	    String query = "SELECT * FROM pasajero WHERE id = ?";
	    try (PreparedStatement ps = conexion.prepareStatement(query)) {
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            String nombre = rs.getString("nombre");
	            int edad = rs.getInt("edad");
	            float peso = rs.getFloat("peso");
	            return new Pasajero(nombre, edad, peso);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion();
	    }
	    return null;
	}

	@Override
	public List<Pasajero> listarPasajeros() {
	    List<Pasajero> pasajeros = new ArrayList<>();
	    
	    if (!abrirConexion()) {
	        return pasajeros;
	    }
	    
	    String query = "SELECT * FROM pasajero";
	    try (PreparedStatement ps = conexion.prepareStatement(query)) {
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            int edad = rs.getInt("edad");
	            float peso = rs.getFloat("peso");
	            Pasajero pasajero = new Pasajero(nombre, edad, peso);
	            pasajeros.add(pasajero);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion();
	    }
	    return pasajeros;
	}


}
