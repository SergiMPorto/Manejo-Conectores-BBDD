package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {
	public interface DaoPersona {
		/**
		 * Metodo que da de alta una persona en la BBDD. Se generar� el ID de manera
		 * autom�tica.
		 * @param p la persona a dar de alta
		 * @return true en caso de que se haya dado de alta. false en caso de error
		 * con la BBDD.
		 */
		boolean addCoche(Coche c);
		boolean deleteCoche(int id);
		/**
		 * Metodo que modifica una persona en la BBDD. La modificaci�n ser� a partir
		 * del ID que contenga la persona.
		 * @param p la persona a modificar
		 * @return true en caso de que se haya modificado. False en caso de error
		 * con la BBDD.
		 */
		boolean updateCoche(Coche c);
		Coche getCoche (int id);
		List<Coche> listar();
	

	
	
}
}
