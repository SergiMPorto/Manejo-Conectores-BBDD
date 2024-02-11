package modelo.negocio;

import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMysql;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {
// Implementamos el DaoPasajero 
    private DaoPasajero dPasajero = new DaoPasajeroMysql();

    
    /*
     * Método para añadir pasajero
     * @param p el pasajero a añadir
     * @return true en el caso de que el pasajero se haya añadido, false en caso de error.
     * 
     */
    public boolean addPasajero(Pasajero p) {
      
            return dPasajero.addPasajero(p);
    }
    /*
     * Método para eliminar un pasajero de la tabla
     * @para id del pasajero a borrar
     * @return true en caso del que el pasajero se haya eliminado correctamente. 
     * 
     */

    public boolean deletePasajero(int id) {
        return dPasajero.deletePasajero(id);
    }

  /*
   * Método para obtener un pasajero de la tabla
   * @param id del pasajero a borrar
   * return tur en caso de que el pasajero sea obtinido, false en caso de error de conexión o 
   * no exista pasajero
   */

    public Pasajero getPasajero(int id) {
        return dPasajero.getPasajero(id);
    }
    /*
     * Método para listar todos los pasajeros de la tabla
     * @param Pasajero
     * return devuelve una lista de los pasajeros con su columnas de nombre en caso de true. 
     */

    public List<Pasajero> listar() {
        return dPasajero.listar();
    }
}
