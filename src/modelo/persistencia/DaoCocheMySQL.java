package modelo.persistencia;

import java.awt.RadialGradientPaint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoCocheMySQL implements DaoCoche{
	
private Connection conexion;
private DaoPasajero daoPasajero;

	public DaoCocheMySQL() {
		
	}

	public DaoCocheMySQL(DaoPasajero daoPasajero) {
		this.daoPasajero = daoPasajero;
	}
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/actividad02";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	@Override
	public boolean add(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coche (MARCA,MODELO,ANHOFABRICACION,KILOMETRAJE) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAnhoFabricacion());
			ps.setDouble(4, c.getKilometraje());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean borrar(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coche where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("Sistema de baja: No hemos podido dar de baja el coche."
					+ " Revise si el identificador" + id + "es correcto.");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "UPDATE coche SET MARCA=?, MODELO=?, ANHOFABRICACION=?, KILOMETRAJE=? WHERE ID=?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAnhoFabricacion());
			ps.setDouble(4, c.getKilometraje());
			ps.setInt(5, c.getId());//fallo raro
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Sistema de modificación: No hemos podido modificar el  "
					+ " coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Coche consultar(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,ANHOFABRICACION,KILOMETRAJE from coche "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAnhoFabricacion(rs.getInt(4));
				coche.setKilometraje(rs.getDouble(5));
			}
		} catch (SQLException e) {
			System.out.println("SIstema de obtención de vehículo: error al obtener el"
					+ "coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return coche;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaPersonas = new ArrayList<>();
		
		String query = "select ID,MARCA, MODELO, ANHOFABRICACION, KILOMETRAJE from coche";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAnhoFabricacion(rs.getInt(4));
				coche.setKilometraje(rs.getDouble(5));
				
				listaPersonas.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("Listado: error al listar los vehículos");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPersonas;
	}

	 @Override
	    public boolean addPasajero(int idPasajero, int idCoche) {
	        abrirConexion();
	        try {
	            
				
	            Pasajero pasajero = daoPasajero.consultarPasajero(idPasajero);
	            if (pasajero == null) {
	                System.out.println("El pasajero no existe.");
	                return false;
	            }
	            
	            
	            PreparedStatement ps = conexion.prepareStatement("SELECT id FROM Coche WHERE id = ?");
	            ps.setInt(1, idCoche);
	            ResultSet rs = ps.executeQuery();
	            if (!rs.next()) {
	                System.out.println("El coche no existe.");
	                return false;
	            }
	            
	            
	            ps = conexion.prepareStatement("UPDATE coche SET idPasajero = ? WHERE id = ?");
	            ps.setInt(1, idPasajero);
	            ps.setInt(2, idCoche);
	            int filasAfectadas = ps.executeUpdate();
	            if (filasAfectadas == 0) {
	                System.out.println("No se pudo asociar el pasajero al coche.");
	                return false;
	            }
	            
	            System.out.println("Pasajero asociado al coche correctamente.");
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            cerrarConexion();
	        }
	    }
	    
	    @Override
	    public boolean borrarPasajeroDeCoche(int idPasajero, int idCoche) {
	        abrirConexion();
	        try {
	            
	            PreparedStatement ps = conexion.prepareStatement("SELECT id FROM Coche WHERE id = ?");
	            ps.setInt(1, idCoche);
	            ResultSet rs = ps.executeQuery();
	            if (!rs.next()) {
	                System.out.println("El coche no existe.");
	                return false;
	            }
	            
	            
	            ps = conexion.prepareStatement("UPDATE Coche SET idPasajero = NULL WHERE id = ?");
	            ps.setInt(1, idCoche);
	            int filasAfectadas = ps.executeUpdate();
	            if (filasAfectadas == 0) {
	                System.out.println("No se pudo desasociar el pasajero del coche.");
	                return false;
	            }
	            
	            System.out.println("Pasajero eliminado del coche correctamente.");
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            cerrarConexion();
	        }
	    }
	    
	    @Override
	    public List<Pasajero> listarPasajerosEnCoche(int idCoche) {
	        abrirConexion();
	        List<Pasajero> pasajeros = new ArrayList<>();
	        try {
	            
	            PreparedStatement ps = conexion.prepareStatement("SELECT p.* FROM Pasajero p JOIN Coche c ON p.id = c.idPasajero WHERE c.id = ?");
	            ps.setInt(1, idCoche);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Pasajero pasajero = new Pasajero( rs.getString("nombre"), rs.getInt("edad"), rs.getFloat("peso"));
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
