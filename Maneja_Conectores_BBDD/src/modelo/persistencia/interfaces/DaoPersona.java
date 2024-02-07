package modelo.persistencia.interfaces;


import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public interface DaoPersona {
	
	
		boolean addPasajero(Pasajero p);
		boolean deletePasajero(int id);
	    Pasajero getCoche (int id);
		List<Pasajero> listar();
	

	  
	

}

