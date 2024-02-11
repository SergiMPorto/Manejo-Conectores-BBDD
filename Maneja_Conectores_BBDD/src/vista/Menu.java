package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;


public class Menu {
//@Author SergiMP
    public static void main(String[] args) {
        System.out.println("Bienvenidos a tu Gestor de Coche");
        Scanner sc = new Scanner(System.in);
        boolean fin = false;
        GestorCoche gCoche = new GestorCoche();
        int opcion;

        List<Coche> listaCoches = new ArrayList<>();

        do {
            System.out.println("Menú");
            System.out.println("1. Registrar coche");
            System.out.println("2. Borrar coche");
            System.out.println("3. Modificar coche");
            System.out.println("4. Consultar coche con ID");
            System.out.println("5. Listado Coches");
            System.out.println("6. Fin del programa");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    addCoche(listaCoches, gCoche);
                    break;
                case 2:
                    deleteCoche(listaCoches, gCoche);
                    break;
                case 3:
                    updateCoche(listaCoches, gCoche);
                    break;
                case 4:
                    getCoche(listaCoches, gCoche);
                    break;
                case 5:
                    listarCoche(gCoche);
                    break;
                case 6:
                    fin = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (!fin);

        System.out.println("Gracias por utilizar el gestor de coche. Te esperamos de nuevo belleza");
    }

    
    // AÑADIR COCHE
    private static void addCoche(List<Coche> listaCoches, GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la marca de coche");
        String marca = sc.next();
        System.out.println("Introduzca el modelo de Coche");
        String modelo = sc.next();
        System.out.println("Introduzca el año de fabricación");
        int yearFabricacion = sc.nextInt();
        System.out.println("Introduzca los Km del coche");
        int km = sc.nextInt();

        Coche c = new Coche();
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setYearFabricacion(yearFabricacion);
        c.setKm(km);

        int resultado = gCoche.addCoche(c);
        if (resultado == 0) {
            System.out.println("Coche registrado");
        } else if (resultado == 1) {
            System.out.println("Error de conexión con la BBDD");
        } else if (resultado == 2) {
            System.out.println("Fallo al introducir el modelo o la marca");
        }
    }
    
    //ELIMINAR COCHE

    private static void deleteCoche(List<Coche> listaCoches, GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del coche a borrar:");
        int id = sc.nextInt();

        boolean deleteCoche = gCoche.deleteCoche(id);
        if (deleteCoche) {
            System.out.println("Coche borrado con éxito");
        } else {
            System.out.println("Error al borrar el coche");
        }
    }
// MODIFICAR COCHE
    private static void updateCoche(List<Coche> listaCoches, GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el id del coche que quiere modificar");
        int id = sc.nextInt();
        System.out.println("Introduzca la marca de coche");
        String marca = sc.next();
        System.out.println("Introduzca el modelo de Coche");
        String modelo = sc.next();
        System.out.println("Introduzca el año de fabricación");
        int yearFabricacion = sc.nextInt();
        System.out.println("Introduzca los Km del coche");
        int km = sc.nextInt();

        Coche c = new Coche();
        c.setId(id);
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setYearFabricacion(yearFabricacion);
        c.setKm(km);

        int resultado = gCoche.updateCoche(c);
        if (resultado == 0) {
            System.out.println("El coche se ha modificado correctamente");
        } else if (resultado == 1) {
            System.out.println("Error al modificar el coche");
        } else if (resultado == 2) {
            System.out.println("Fallo al introducir el modelo o la marca");
        }
    }
    
    //OBTENER COCHE

    private static void getCoche(List<Coche> listaCoches, GestorCoche gCoche) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el id del coche a consultar ");
        int id = sc.nextInt();

        Coche coche = gCoche.getCoche(id);
        if (coche != null) {
            System.out.println("Coche encontrado:");
            System.out.println(coche);
        } else {
            System.out.println("No se encontró ningún coche con ese ID.");
        }
    }
    //LISTAR COCHES

    private static void listarCoche(GestorCoche gCoche) {
        List<Coche> listaCoches = gCoche.listar();
        if (listaCoches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            System.out.println("Listado de coches:");
            for (Coche coche : listaCoches) {
                System.out.println(coche);
            }
        }
    }
}
