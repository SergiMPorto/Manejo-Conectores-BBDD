package modelo.persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMysql implements DaoCoche {
	
	private Connection conexion;
	
	
	public boolean abrirConexion() {
		String url = "jdbc:mysql : //localhost:3306/bbdd";
		String usuario = "root";
		String password = "";
		
		try {
			conexion = DriverManager.getConnection(url,usuario, password);
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public boolean cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean addCoche (Coche c) {
		if(!abrirConexion()) {
			return false;
		}
		
		boolean addCoche = true;
		
		String query = "insert into coches (ID, MODELO, YEARFABRICACION, KM)"  + "value (?,?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareCall(query);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getYearFabricacion());
			ps.setInt(4, c.getKm());
			
			
			int filasAfectadas = ps.executeUpdate();
			if(filasAfectadas == 0)
				addCoche = false;
			
		} catch (SQLException e) {
			System.out.println(" Error al añadir un coche nuevo: " + c); 
			addCoche = false;
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		return addCoche;
	}
	
	
	public boolean deleteCoche(int id) {
		if (!abrirConexion()) {
			return false;
		}
			boolean deleteCoche = true;
			
String query = "delete from persona where id = ?";
			
			
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setInt(1, id);
				
				
				int filasAfectadas = ps.executeUpdate();
				if(filasAfectadas == 0)
					deleteCoche = false;
				
				
			} catch (SQLException e) {
				deleteCoche = false;
				System.out.println(" Fallo al dar de baja el coche con id: "+ id);
				e.printStackTrace();
			}finally {
				cerrarConexion();
				}
			return deleteCoche;
			
			
			}
	
	
	
	public boolean updateCoche(Coche c) {
		if(!abrirConexion()) {
			return false;
		}
		
		boolean updateCoche = true;
		
		String query = "update coches set MODELO=?, YearFabricacion=?,  KM=? WHERE ID=?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getYearFabricacion());
			ps.setInt(4, c.getKm());
			
			
			int filasAfectadas = ps.executeUpdate();
			if(filasAfectadas == 0)
				updateCoche = false;
			
		} catch (SQLException e) {
			System.out.println("modificar -> fallo al modificar el coche " + c);
			updateCoche = false;
			e.printStackTrace();
			
		}finally {
			cerrarConexion();
		}
		return updateCoche;
	}
	
	public Coche getCoche(int id) {
		if(!abrirConexion()) {
			return null;
		}
		
		Coche coche = null;
		
		String query = "select ID, MODELO, YEARFABRICACION, KM from coches where id = ?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setModelo(rs.getString(2));
				coche.setYearFabricacion(rs.getInt(3));
				coche.setKm(rs.getInt(4));
				
			}
		} catch (SQLException e) {
			System.out.println("Fallo al obtener el coche con id: "+ id);
			e.printStackTrace();
			}finally {
				cerrarConexion();
			}
		return coche;
	
	
}
	
	public List<Coche>listar(){
		if(!abrirConexion()) {
			return null;
		}
		
	List<Coche>listaCoches = new ArrayList<>();
	
	String query = "select ID, MODELO, YEARFABRICACION, KM from coches";
	
	try {
		PreparedStatement ps = conexion.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Coche coche = new Coche();
			coche.setId(rs.getInt(1));
			coche.setModelo(rs.getString(2));
			coche.setYearFabricacion(rs.getInt(3));
			coche.setKm(rs.getInt(4));
			
		}
	} catch (SQLException e) {
		System.out.println("Fallo al obtener la lista de coches: ");
		e.printStackTrace();
		}finally {
			cerrarConexion();
		}
	return listaCoches;
	
	
	}
}

