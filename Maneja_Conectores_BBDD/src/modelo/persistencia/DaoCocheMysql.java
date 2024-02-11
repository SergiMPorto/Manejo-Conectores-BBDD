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
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

//@Author SergiMP


//implementamos DaoCoche y DaoPasajero para utilizar los métodos 
//que ya hemos añadido en DaoCoche y DaoPasajero. 
//Necesitamos DaoPasajero para poder añadir el pasajero al coche y obtener 
//que pasajero está asocidado al coche. 
public class DaoCocheMysql implements DaoCoche, DaoPasajero {
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

    //Añadimos coche para ello primero  nos cercioramos de que esté abierta la conexión
    // Escribimos la query y la enviamos con el PreparedStatent 
    
    @Override
    public boolean addCoche(Coche c) {
        if (!abrirConexion()) {
            return false;
        }

        boolean addCoche = true;

        String query = "insert into coches (ID, MARCA, MODELO, YEARFABRICACION, KM)" + "value (?,?,?,?,?)";

        try {
            PreparedStatement ps = conexion.prepareCall(query);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getYearFabricacion());
            ps.setInt(5, c.getKm());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0)
                addCoche = false;

        } catch (SQLException e) {
            System.out.println(" Error al añadir un coche nuevo: " + c);
            addCoche = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return addCoche;
    }
    
    //ELIMINAR COCHE DE LA TABLA 

    @Override
    public boolean deleteCoche(int id) {
        if (!abrirConexion()) {
            return false;
        }
        boolean deleteCoche = true;

        String query = "delete from coches where id = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0)
                deleteCoche = false;

        } catch (SQLException e) {
            deleteCoche = false;
            System.out.println(" Fallo al dar de baja el coche con id: " + id);
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return deleteCoche;
    }

    // MODIFICAR COCHE
    @Override
    public boolean updateCoche(Coche c) {
        if (!abrirConexion()) {
            return false;
        }

        boolean updateCoche = true;

        String query = "UPDATE coches SET MARCA=?, MODELO=?, YearFabricacion=?, KM=? WHERE ID=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getYearFabricacion());
            ps.setInt(4, c.getKm());
            ps.setInt(5, c.getId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0)
                updateCoche = false;

        } catch (SQLException e) {
            System.out.println("modificar -> fallo al modificar el coche " + c);
            updateCoche = false;
            e.printStackTrace();

        } finally {
            cerrarConexion();
        }
        return updateCoche;
    }
    
    //OBTENER COCHE

    @Override
    public Coche getCoche(int id) {
        if (!abrirConexion()) {
            return null;
        }

        Coche coche = null;

        String query = "select ID, MARCA, MODELO, YEARFABRICACION, KM from coches where id = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                coche = new Coche();
                coche.setId(rs.getInt(1));
                coche.setMarca(rs.getString(2));
                coche.setModelo(rs.getString(3));
                coche.setYearFabricacion(rs.getInt(4));
                coche.setKm(rs.getInt(5));

            }
        } catch (SQLException e) {
            System.out.println("Fallo al obtener el coche con id: " + id);
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return coche;
    }
    
    //OBTENER UNA LISTA DE LOS COCHES REGISTRADOS 

    @Override
    public List<Coche> listarCoche() {
        if (!abrirConexion()) {
            return null;
        }

        List<Coche> listaCoches = new ArrayList<>();

        String query = "SELECT ID, MARCA, MODELO, YEARFABRICACION, KM FROM coches";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coche coche = new Coche();
                coche.setId(rs.getInt(1));
                coche.setMarca(rs.getString(2));
                coche.setModelo(rs.getString(3));
                coche.setYearFabricacion(rs.getInt(4));
                coche.setKm(rs.getInt(5));

                listaCoches.add(coche);
            }
        } catch (SQLException e) {
            System.out.println("Fallo al obtener la lista de coches: ");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return listaCoches;
    }

    
    //AÑADIR PASAJERO AL COCHE
    // Para ello primero debemos de verificar que existe el pasajero 
    @Override
    public boolean addPasajeroCoche(int idPasajero, int idCoche) {
        if (!abrirConexion()) {
            System.out.println("Error: No se pudo abrir la conexión con la base de datos.");
            return false;
        }

        boolean addPasajeroCoche = false;
        
        try {
           
            Pasajero pasajero = new Pasajero();
            
            if (pasajero != null) {
               
                String query = "UPDATE coches SET idPasajero = ? WHERE ID = ?";
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setInt(1, idPasajero);
                ps.setInt(2, idCoche);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    addPasajeroCoche = true;
                    System.out.println("El pasajero con ID " + idPasajero + " ha sido añadido al coche con ID " + idCoche + " correctamente.");
                } else {
                    System.out.println("Error: No se pudo añadir el pasajero al coche. No se encontraron filas afectadas en la base de datos.");
                }
            } else {
                System.out.println("Error: No se pudo encontrar el pasajero con ID " + idPasajero + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error al añadir pasajero al coche: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return addPasajeroCoche;
    }


//ELIMINAR PASAJERO DEL COCHE
  
    @Override
    public boolean deletePasajeroCoche(int idPasajero) {
        if (!abrirConexion()) {
            return false;
        }

        boolean deletePasajeroCoche = true;
        String query = "UPDATE coches SET idPasajero = NULL WHERE idPasajero = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idPasajero);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                deletePasajeroCoche = false;
            }
        } catch (SQLException e) {
            deletePasajeroCoche = false;
            System.out.println("Fallo al eliminar el pasajero con id " + idPasajero + " de los coches.");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return deletePasajeroCoche;
    }

  // LISTAR PASAJERO QUE TIENE UN COCHE.
    
    @Override
    public List<Pasajero> listarPasajerosCoche(int idCoche) {
        List<Pasajero> pasajeros = new ArrayList<>();
        String query = "SELECT p.ID, p.NOMBRE, p.EDAD, p.PESO FROM PASAJEROS p " +
                "WHERE p.ID IN (SELECT idPasajero FROM COCHES WHERE ID = ?)";

        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idCoche);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt("ID"));
                pasajero.setNombre(rs.getString("NOMBRE"));
                pasajero.setEdad(rs.getInt("EDAD"));
                pasajero.setPeso(rs.getInt("PESO"));

                pasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de pasajeros del coche con ID " + idCoche);
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return pasajeros;
    }

// AL IMPLEMENTAR DAOPASAJERO NOS ESCRIBE LOS MÉTODOS

    @Override
    public boolean addPasajero(Pasajero p) {
      
        return false;
    }

    @Override
    public boolean deletePasajero(int id) {
     
        return false;
    }

    @Override
    public Pasajero getPasajero(int id) {
      
        return null;
    }

  

	@Override
	public List<Pasajero> listar() {
		
		return null;
	}
}
