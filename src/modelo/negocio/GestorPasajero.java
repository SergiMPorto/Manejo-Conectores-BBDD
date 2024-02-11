package modelo.negocio;

import java.util.List;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {
    private DaoPasajero daoPasajero;

    public GestorPasajero(DaoPasajero daoPasajero) {
        this.daoPasajero = daoPasajero;
    }

    public boolean nuevoPasajero(Pasajero pasajero) {
        if (pasajero.getNombre().isEmpty() || pasajero.getEdad() <= 0 || pasajero.getPeso() <= 0) {
            System.out.println("Los datos del pasajero son incorrectos.");
            return false;
        }
        return daoPasajero.nuevoPasajero(pasajero);
    }

    public boolean borrarPasajero(int id) {
        if (id <= 0) {
            System.out.println("ID de pasajero incorrecto.");
            return false;
        }
        return daoPasajero.borrarPasajero(id);
    }

    public Pasajero consultarPasajero(int id) {
        if (id <= 0) {
            System.out.println("ID de pasajero incorrecto.");
            return null;
        }
        return daoPasajero.consultarPasajero(id);
    }

    public List<Pasajero> listarPasajeros() {
        return daoPasajero.listarPasajeros();
    }
}
