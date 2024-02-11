package modelo.negocio;

import java.util.List;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMysql;
import modelo.persistencia.interfaces.DaoCoche;
//@Author SergiMP
public class GestorCoche {
	
    private DaoCoche dCoche = new DaoCocheMysql();
    
    
    
    /*
     * Método para agregar un coche a la base de datos. Para ello establecemos varias reglas
     * según lo propuesto en el requerimiento 3. EL coche debe tener una marca como mínimo una longitud de 3 y un modelo obligatoriamente.
     * @param c el coche a añadir. 
     * @return 0 en caso de que hayamos añadido correctamente, 1 error de conexión, 2 no se cumple los requisitos 
     */

    public int addCoche(Coche c) {
        if (c.getMarca().length() >= 3 && c.getModelo().length() > 0) {
            boolean addCoche = dCoche.addCoche(c);
            if (addCoche) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }
/*
 * Método para eliminar el coche para ello le pasamos el id. 
 * @param c coche a añadir
 * @return se elimina el coche
 */
    
    public boolean deleteCoche(int id) {
        return dCoche.deleteCoche(id);
    }

    /*
     * Metodo para actualizar el coche. Teniendo en cuenta que el coche debe tener una marca y modelo
     * @param c el coche a dar de alta.
     * @return 0 en caso de modificar correctamente, 1 algún error en la conexión, 2 no se cumple en los requesitos de marca y modelo
     */
    public int updateCoche(Coche c) {
        if (c.getMarca().length() >= 3 && c.getModelo().length() > 0) {
            boolean updateCoche = dCoche.updateCoche(c);
            if (updateCoche) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }

    /*
     * Metodo para buscar coche 
     * @param id del coche para buscar
     * return el coche buscado
     */
    public Coche getCoche(int id) {
        return dCoche.getCoche(id);
    }
    /*
     * Método para obtner la lista de coches 
     * @param coche
     * @return una lista de coches en el caso de que sea true.
     */

    public List<Coche> listar() {
        return dCoche.listarCoche();
        
        /*
         * Método para añadir pasajeros al coche
         * @param id del pasajero y id del coche
         * @return nos devuelve la atabla de coches con el id del pasajero si es true.
         * 
         */
    }

    public boolean addPasajeroCoche(int idPasajero, int idCoche) {
		return dCoche.addPasajeroCoche(idPasajero, idCoche);
      
    }
    
    /*
     * Método para eliminar un pasajero del coche
     * @param idPasajero de la tabla coche
     * return true si es eliminado correctamente,false en caso de error.
     */

    public boolean deletePasajeroCoche(int idPasajero) {
        return dCoche.deletePasajeroCoche(idPasajero);
        
        /*
         * Método para listar los pasajeros de un coche
         * @param id del coche
         * return una list con los pasajeros del coche
         */
        
    }
    public List<Pasajero> listarPasajerosCoche(int idCoche) {
		return dCoche.listarPasajerosCoche(idCoche);
	}

}
