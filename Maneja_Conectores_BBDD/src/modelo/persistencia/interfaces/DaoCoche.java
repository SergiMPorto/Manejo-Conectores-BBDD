package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;




import java.util.List;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

//@Author SergiMP

public interface DaoCoche {
	/*
	 * Método que añade un coche. Se genera el ID de manera automática.
	 * @param c el coche a añadir 
	 * return true en caso de que se haya añadido correctamente, false en caso de eroor con la BBDD
	 * 
	 */
    boolean addCoche(Coche c);
    
    /*
     * Método para dar de baja
     * @param c el coche a dar de baja
     * return true si la baja se efectúa false si hay algún error
     */
    boolean deleteCoche(int id);
    
    /*
     * Método para modificar el coche 
     * @param le pasamos el parametro c del coche que queremos modificar.
     * return true en caso de que se haya modificado. False en caso de error. 
     */
    boolean updateCoche(Coche c);
    
    /*
     * Método para obtener un coche.
     * @param le pasamos el id del coche que queremos obtener
     * return true caso de obtenerlo, false error. 
     */
    Coche getCoche(int id);
    
    /*
     * Método para listar los coches que estánen la tabla
     * @paran la clase coche
     * return true caso de obtener el listado, false error
     */
    List<Coche> listarCoche();
    
    
    /*
     * Método para añadir un pasajero al coche. 
     * @param le pasamos el id del pasajero y el id del coche al que queremos añadirlo
     * return true en caso de que el pasajero haya sido añadido al coche exitosamente.false en caso de error.
     * Se establece una restricción un pasajero solo puede tener un coche- 
     */
    boolean addPasajeroCoche(int idPasajero, int idCoche);
    
    /*
     * Método para eliminar pasajero al coche 
     * @param el id del pasajero del coche
     * return true en caso de que se elimine el pasajero del coche, false en caso de error
     * 
     */
    boolean deletePasajeroCoche(int idPasajero);
    
    /*
     * Método para listar los pasajeros que tienen un coche
     * @param el id del coche 
     * return lista de pasajeros del coche. 
     */
    List<Pasajero> listarPasajerosCoche(int idCoche);
}
