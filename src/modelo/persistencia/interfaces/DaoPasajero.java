package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;


public interface DaoPasajero {
	
	public boolean nuevoPasajero(Pasajero p);
	public boolean borrarPasajero(int id);
	public Pasajero consultarPasajero (int id);
	public List<Pasajero> listarPasajeros();
	
}
