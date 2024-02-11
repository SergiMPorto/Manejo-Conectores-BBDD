package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public interface DaoCoche {
	public boolean add(Coche c);
	public boolean borrar(int id);
	public boolean modificar(Coche c);
	public Coche consultar(int id);
	public List<Coche> listar();
	public boolean addPasajero(int idPasajero, int idCoche);
	public boolean borrarPasajeroDeCoche(int idPasajero, int idCoche);
	public List<Pasajero> listarPasajerosEnCoche(int idCoche);
}
