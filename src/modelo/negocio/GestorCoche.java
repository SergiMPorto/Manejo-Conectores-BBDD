package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.*;
import modelo.persistencia.interfaces.*;


public class GestorCoche {
	
	
		private DaoCoche daoCoche = new DaoCocheMySQL();
		
		
		public int alta(Coche coche) {
	        if (coche.getMarca().isEmpty() || coche.getModelo().isEmpty()) {
	            return 2; 
	        }
	        
	        boolean alta = daoCoche.add(coche);
	        if (alta) {
	            return 0; 
	        } else {
	            return 1; 
	        }
	    }
		
		public boolean baja(int id){
			boolean baja = daoCoche.borrar(id);
			return baja;
		}
		
		/**
		 * Método que da modifica una persona en base de datos. El nombre de la persona
		 * debe de tener al menos 3 caracteres. La modificarci�n sera a partir del 
		 * id de la persona
		 * @param c la persona a modificar. Dentro tiene que tener el id
		 * @return 0 en caso de que hayamos modificado la persona, 1 en caso
		 * de algun error de conexi�n con la bbdd y 2 en caso de que la persona
		 * tenga menos de 3 caracteres
		 */
		public int modificar(Coche c){
			//aplicamos la regla de negocio
			if(c.getMarca().length() >= 3) {
				boolean modificada = daoCoche.modificar(c);
				if(modificada) {
					return 0;
				}else {
					return 1;
				}
			}
			return 2;
		}
		
		public Coche obtener(int id){
			Coche coche = daoCoche.consultar(id);
			return coche;
		}
		
		public List<Coche> listar(){
			List<Coche> listaCoches = daoCoche.listar();
			return listaCoches;
		}
}
