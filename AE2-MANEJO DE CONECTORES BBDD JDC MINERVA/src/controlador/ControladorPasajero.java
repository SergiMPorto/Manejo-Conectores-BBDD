

package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.DaoPasajeroMySql;

public class ControladorPasajero {
	Scanner sc = new Scanner(System.in);
	DaoPasajeroMySql dao = new DaoPasajeroMySql();
	DaoCocheMySql daocoche = new DaoCocheMySql();
	
	public boolean altaPasajero(Pasajero p) {
		
		if(p.getNombre().isEmpty() ) {
			return false;
		}
		
		
		dao.altaPasajero(p);
		return true;
	}
	
	public boolean  borrarPasajero(int id) {

		Pasajero p = dao.consultarUnPasajero(id);
		if(p != null) {
			dao.borrarPasajero(id);
			return true;
		}else {
			return false;
		}
	}

	
	public Pasajero consultarUnPasajero(int id) {

		Pasajero p = dao.consultarUnPasajero(id);
		if(p != null) {
			return p;
		}
		return null;
	}
	
	
	public 	ArrayList<Pasajero> listarPasajeros() {
		return ((ControladorPasajero) dao).listarPasajeros();
	}
	
	
	public ArrayList<Pasajero> listarPasajerosDeUnCoche(int idcoche) {
		DaoCocheMySql daocoche = new DaoCocheMySql();
		Coche c = daocoche.consultarUnCoche(idcoche);
		if (c == null) {
			return null;
		}else{
			
			return dao.listarPasajerosDeUnCoche(idcoche);
		}
		
		

	}
	
	public boolean quitarCocheAPasajero(int id) {
		Pasajero p = dao.consultarUnPasajero(id);
		if(p != null) {
			dao.quitarCocheAPasajero(id);
			return true;
		}else {
			return false;
		}
	}

	public boolean anadirPasajeroACoche(int idcoche, int idpasajero) {
		Pasajero p = dao.consultarUnPasajero(idpasajero);
		Coche c = daocoche.consultarUnCoche(idcoche);
		if(p == null) {
			return false;
		}
		if(c == null) {
			return false;
		}
		dao.añadirPasajeroACoche(idcoche,idpasajero);
		return true;
	}

	public void cerrar() {
		dao.cerrar();
	}
}

