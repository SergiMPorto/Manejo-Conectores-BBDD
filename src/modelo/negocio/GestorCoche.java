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
		
		
		public int modificar(Coche c){
			
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
