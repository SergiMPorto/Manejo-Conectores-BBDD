package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;


public interface DaoCoche {
	public interface DaoPersona {
		
		boolean addCoche(Coche c);
		boolean deleteCoche(int id);
		
		boolean updateCoche(Coche c);
		Coche getCoche (int id);
		List<Coche> listar();
		
		boolean addPasajeroCoche( int idCoche, int idPasajero);
		boolean deletePasajeroCoche(int idCoche, int idPasajero);
		
		List<Pasajero>listarPC(int idCoche);
	

	
	
}
}
