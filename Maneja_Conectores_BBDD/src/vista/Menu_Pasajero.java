package vista;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;
//@Author SergiMP

public class Menu_Pasajero {

    public static void main(String[] args) {
        System.out.println("Bienvenidos a tu Gestor de Pasajero");
        Scanner sc = new Scanner(System.in);
        boolean fin = false;
        GestorPasajero gPasajero = new GestorPasajero();
        GestorCoche gCoche = new GestorCoche();
        int opcion;

        do {
            System.out.println("Menú");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Borrar pasajero");
            System.out.println("3. Consultar pasajero con ID");
            System.out.println("4. Listar todos los pasajeros");
            System.out.println("5. Añadir pasajero a coche");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar todos los pasajeros de un coche");
            System.out.println("8. Fin del programa");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    addPasajero(gPasajero);
                    break;
                case 2:
                    deletePasajero(gPasajero);
                    break;
                case 3:
                    getPasajero(gPasajero);
                    break;
                case 4:
                    listarPasajeros(gPasajero);
                    break;
                case 5:
                    addPasajeroACoche(gCoche);
                    break;
                case 6:
                    deletePasajeroDeCoche(gCoche);
                    break;
                case 7:
                    listarPasajerosDeCoche(gCoche);
                    break;
                case 8:
                	fin=true;
                default:
                    System.out.println("Opción no válida");
            }
        } while (!fin);

        System.out.println("Gracias por utilizar el gestor de pasajeros. Sayorana, baby");
    }
//AÑADIR PASAJERO
    private static void addPasajero(GestorPasajero gPasajero) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del pasajero");
        String nombre = sc.next();
        System.out.println("Introduzca la edad del pasajero");
        int edad = sc.nextInt();
        System.out.println("Introduzca el peso del pasajero");
        int peso = sc.nextInt();

        Pasajero p = new Pasajero();
        p.setNombre(nombre);
        p.setEdad(edad);
        p.setPeso(peso);

        boolean resultado = gPasajero.addPasajero(p);
        if (resultado) {
            System.out.println("Pasajero registrado");
        } else {
            System.out.println("Error al registrar el pasajero");
        }
    }

    
    // ELIMINAR PASAJERO
    private static void deletePasajero(GestorPasajero gPasajero) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del pasajero a borrar:");
        int id = sc.nextInt();

        boolean resultado = gPasajero.deletePasajero(id);
        if (resultado) {
            System.out.println("Pasajero borrado correctamente");
        } else {
            System.out.println("Error al borrar el pasajero");
        }
    }
    
    // OBTENER PASAJERO

    private static void getPasajero(GestorPasajero gPasajero) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del pasajero:");
        int id = sc.nextInt();

        Pasajero p = gPasajero.getPasajero(id);
        if (p != null) {
            System.out.println("Información del pasajero:");
            System.out.println(p);
        } else {
            System.out.println("No se encontró ningún pasajero con ese ID");
        }
    }

    // LISTAR PASAJEROS 
    private static void listarPasajeros(GestorPasajero gPasajero) {
        List<Pasajero> lista = gPasajero.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay pasajeros registrados");
        } else {
            System.out.println("Listado de pasajeros:");
            for (Pasajero p : lista) {
                System.out.println(p);
            }
        }
    }

    
    // AÑADIR PASAJERO A COCHE
    private static void addPasajeroACoche(GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del pasajero:");
        int idPasajero = sc.nextInt();
        System.out.println("Ingrese el ID del coche:");
        int idCoche = sc.nextInt();

        boolean resultado = gCoche.addPasajeroCoche(idPasajero, idCoche);
        if (resultado) {
            System.out.println("Pasajero añadido al coche correctamente");
        } else {
            System.out.println("Error al añadir pasajero al coche");
        }
    }

    
    //ELIMINAR PASAJERO AL COCHE
    private static void deletePasajeroDeCoche(GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del pasajero:");
        int idPasajero = sc.nextInt();

        boolean resultado = gCoche.deletePasajeroCoche(idPasajero);
        if (resultado) {
            System.out.println("Pasajero eliminado del coche correctamente");
        } else {
            System.out.println("Error al eliminar pasajero del coche");
        }
    }
    
    //LISTAR PASAJEROS DEL COCHE.

    private static void listarPasajerosDeCoche(GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del coche:");
        int idCoche = sc.nextInt();

        List<Pasajero> lista = gCoche.listarPasajerosCoche(idCoche);
        if (lista.isEmpty()) {
            System.out.println("No hay pasajeros asociados a este coche");
        } else {
            System.out.println("Listado de pasajeros del coche:");
            for (Pasajero p : lista) {
                System.out.println(p);
            }
        }
    }
}
