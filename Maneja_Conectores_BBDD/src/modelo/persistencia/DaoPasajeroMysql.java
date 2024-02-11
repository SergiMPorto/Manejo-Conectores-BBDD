package modelo.persistencia;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;
//@Author SergiMP


//Implementamos DaoPasajero para que nos cargue los métodos. 
public class DaoPasajeroMysql implements DaoPasajero {
    private Connection conexion;

 // Cargamos los parametros de conexión y le pasamos las  properties que hemos escrito 
    // en el fichero CochhesBBDD.properties
    private void cargarParametrosConexion() {
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("CochesBBDD.properties")) {
            propiedades.load(entrada);
            String url = propiedades.getProperty("db.url");
            String usuario = propiedades.getProperty("db.usuario");
            String password = propiedades.getProperty("db.contrasena");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean abrirConexion() {
        cargarParametrosConexion();
        return conexion != null;
    }

    public boolean cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
//AÑADIR PASAJERO
    //PRIMERO VEMOS QUE LA CONEXIÓN ESTE ABIERTA PARA PODER PASARLE EL MÉTODO
    
    @Override
    public boolean addPasajero(Pasajero p) {
        if (!abrirConexion()) {
            return false;
        }
        
        boolean addPasajero = true;
        
        String query = "INSERT INTO pasajeros (ID, NOMBRE, EDAD, PESO) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getEdad());
            ps.setInt(4, p.getPeso());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0)
                addPasajero = false;
            
        } catch (SQLException e) {
            System.out.println("Error al añadir un nuevo pasajero " + p); 
            addPasajero = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return addPasajero;
    }

    // AÑADIR PASAJERO 
 
    @Override
    public Pasajero getPasajero(int id) {
        if (!abrirConexion()) {
            return null;
        }
        
        Pasajero pasajero = null;
        
        String query = "SELECT ID, NOMBRE, EDAD, PESO FROM pasajeros WHERE id = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                pasajero = new Pasajero();
                pasajero.setId(rs.getInt(1));
                pasajero.setNombre(rs.getString(2));
                pasajero.setEdad(rs.getInt(3));
                pasajero.setPeso(rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("Fallo al obtener el pasajero con id: " + id);
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return pasajero;
        }
    
    //LISTAR PASAJEROS
@Override
    public List<Pasajero> listar() {
        if (!abrirConexion()) {
            return null;
        }
        
        List<Pasajero> listaPasajeros = new ArrayList<>();
        
        String query = "SELECT ID, NOMBRE, EDAD, PESO FROM pasajeros";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt(1));
                pasajero.setNombre(rs.getString(2));
                pasajero.setEdad(rs.getInt(3));
                pasajero.setPeso(rs.getInt(4));
                
                listaPasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            System.out.println("Fallo al obtener la lista de pasajeros");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return listaPasajeros;
    }


//ELIMINAR PASAJERO
@Override
public boolean deletePasajero(int id) {
	if (!abrirConexion()) {
		return false;
	}
		boolean deletePasajero = true;
		
String query = "delete from pasajeros where id = ?";
		
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			
			int filasAfectadas = ps.executeUpdate();
			if(filasAfectadas == 0)
				deletePasajero = false;
			
			
		} catch (SQLException e) {
			deletePasajero = false;
			System.out.println(" Fallo al dar de baja el pasajero con id: "+ id);
			e.printStackTrace();
		}finally {
			cerrarConexion();
			}
		return deletePasajero;
		
		
		}

}
