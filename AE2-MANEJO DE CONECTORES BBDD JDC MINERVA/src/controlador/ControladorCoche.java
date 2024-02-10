package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;

public class ControladorCoche {

	Scanner sc = new Scanner(System.in);
	DaoCocheMySql dao = new DaoCocheMySql();
	
	public boolean crearCoche(Coche coche) {
		if(coche.getMarca().isEmpty() | coche.getModelo().isEmpty()) {
			return false;
		}
		dao.altaCoche(coche);
		return true;
	}
	
	public boolean borrarCoche(int id) {

		Coche coche = dao.consultarCoche(id);
		if(coche != null) {
			dao.borrarCoche(id);
			return true;
		}else {
			return false;
		}
	}
	
	
	public Coche consultarCoche(int id) {

		Coche coche = dao.consultarCoche(id);
		if(coche != null) {
			return coche;
		}
		return null;
	}
	
	public int modificarCoche(Coche c) {
		Coche coche = dao.consultarCoche(c.getId());
		if(coche != null) {
			if(coche.getMarca().isEmpty() | coche.getModelo().isEmpty()) {
				return 2;
			}
			dao.modificarCoche(c);
			return 0;
		}else {
			return 1;
		}
	}
	
	public 	ArrayList<Coche> listarCoches(){
		return Dao.listarCoches();
	}
	
	public void cerrar() {
		Dao.cerrar();
	}
}
