package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;


//@Author SergiMP


public interface DaoPasajero {
	
	/*
	 * Método para dar añadir un pasajero se genera un id de manera automática.  
	 * @param p el pasajero que queremos añadir
	 * @return true en caso de que el pasajero sea añadido correctamente false en caso de error
	 */
    boolean addPasajero(Pasajero p);
    /*
     * Método para eliminar pasajeros mediante id.
     * @param id del pasajero 
     * @return true si el pasajero se elimina de la base de datos. 
     */
    boolean deletePasajero(int id);
    
    /*
     * Método para listar todos los pasajeros de la BBSS
     * @param clase pasajero a list
     * @return true nos devuelve una lista de pasajeros 
     * 
     */
    List<Pasajero> listar();
    
    /*
     * Método para obtener un pasajero de la tabla
     * @param id del pasajero
     * @return true en caso del que pasajero existe, false en caso de error
     */
    Pasajero getPasajero(int id);
}
