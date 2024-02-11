package vista;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySQL;
import modelo.persistencia.DaoPasajeroSQL;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        DaoPasajeroSQL daoPasajero = new DaoPasajeroSQL();
        DaoCocheMySQL daoCoche = new DaoCocheMySQL(daoPasajero);

        boolean continuar = true;

        while (continuar) {
            System.out.println("Menú:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consultar coche por ID");
            System.out.println("4. Modificar coche por ID");
            System.out.println("5. Listado de coches");
            System.out.println("6. Gestión de pasajeros");
            System.out.println("7. Terminar el programa");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Coche nuevoCoche = nuevoCocheDesdeInput(scanner);
                    boolean alta = daoCoche.add(nuevoCoche);
                    if (alta) {
                        System.out.println("Coche añadido correctamente.");
                    } else {
                        System.out.println("Error al añadir el coche.");
                    }
                    break;
                case 2:
                    System.out.print("Introduzca el ID del coche a borrar: ");
                    int idBorrar = scanner.nextInt();
                    boolean borrado = daoCoche.borrar(idBorrar);
                    if (borrado) {
                        System.out.println("Coche borrado correctamente.");
                    } else {
                        System.out.println("Error al borrar el coche.");
                    }
                    break;
                case 3:
                    System.out.print("Introduzca el ID del coche a consultar: ");
                    int idConsultar = scanner.nextInt();
                    Coche cocheConsultado = daoCoche.consultar(idConsultar);
                    if (cocheConsultado != null) {
                        System.out.println("Coche consultado: " + cocheConsultado);
                    } else {
                        System.out.println("No se encontró ningún coche con ese ID.");
                    }
                    break;
                case 4:
                    System.out.print("Introduzca el ID del coche a modificar: ");
                    int idModificar = scanner.nextInt();
                    Coche cocheAModificar = daoCoche.consultar(idModificar);
                    if (cocheAModificar != null) {
                        Coche cocheModificado = nuevoCocheDesdeInput(scanner);
                        cocheModificado.setId(idModificar);
                        boolean modificado = daoCoche.modificar(cocheModificado);
                        if (modificado) {
                            System.out.println("Coche modificado correctamente.");
                        } else {
                            System.out.println("Error al modificar el coche.");
                        }
                    } else {
                        System.out.println("No se encontró ningún coche con ese ID.");
                    }
                    break;
                case 5:
                    System.out.println("Listado de coches:");
                    List<Coche> coches = daoCoche.listar();
                    if (coches != null) {
                        for (Coche coche : coches) {
                            System.out.println(coche);
                        }
                    } else {
                        System.out.println("Error al obtener el listado de coches.");
                    }
                    break;
                case 6:
                    // Submenú de gestión de pasajeros
                    subMenuPasajeros(scanner, daoPasajero,daoCoche);
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }

    public static Coche nuevoCocheDesdeInput(Scanner scanner) {
        System.out.print("Introduzca la marca del coche: ");
        String marca = scanner.next();
        System.out.print("Introduzca el modelo del coche: ");
        String modelo = scanner.next();
        System.out.print("Introduzca el año de fabricación del coche: ");
        int anhoFabricacion = scanner.nextInt();
        System.out.print("Introduzca los kilómetros del coche: ");
        double km = scanner.nextDouble();

        return new Coche(marca, modelo, anhoFabricacion, km);
    }
    
    
    public static void subMenuPasajeros(Scanner scanner, DaoPasajeroSQL daoPasajero, DaoCocheMySQL daoCoche) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menú Gestión de Pasajeros:");
            System.out.println("1. Crear nuevo pasajero");
            System.out.println("2. Borrar pasajero por ID");
            System.out.println("3. Consultar pasajero por ID");
            System.out.println("4. Listar todos los pasajeros");
            System.out.println("5. Añadir pasajero a coche");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar todos los pasajeros de un coche");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para crear nuevo pasajero
                    crearNuevoPasajero(scanner, daoPasajero);
                    break;
                case 2:
                    // Lógica para borrar pasajero por ID
                    borrarPasajero(scanner, daoPasajero);
                    break;
                case 3:
                    // Lógica para consultar pasajero por ID
                    consultarPasajero(scanner, daoPasajero);
                    break;
                case 4:
                    // Lógica para listar todos los pasajeros
                    listarTodosLosPasajeros(daoPasajero);
                    break;
                case 5:
                    // Lógica para añadir pasajero a coche
                    añadirPasajeroACoche(scanner, daoPasajero, daoCoche);
                    break;
                case 6:
                    // Lógica para eliminar pasajero de un coche
                    eliminarPasajeroDeCoche(scanner, daoCoche);
                    break;
                case 7:
                    // Lógica para listar todos los pasajeros de un coche
                    listarPasajerosDeCoche(scanner, daoCoche);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void crearNuevoPasajero(Scanner scanner, DaoPasajeroSQL daoPasajero) {
        System.out.println("Introduzca los datos del nuevo pasajero:");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        System.out.print("Peso: ");
        float peso = scanner.nextFloat();
        
        Pasajero nuevoPasajero = new Pasajero(nombre, edad, peso);
        boolean alta = daoPasajero.nuevoPasajero(nuevoPasajero);
        if (alta) {
            System.out.println("Pasajero añadido correctamente.");
        } else {
            System.out.println("Error al añadir el pasajero.");
        }
    }

    public static void borrarPasajero(Scanner scanner, DaoPasajeroSQL daoPasajero) {
        System.out.print("Introduzca el ID del pasajero a borrar: ");
        int id = scanner.nextInt();
        boolean borrado = daoPasajero.borrarPasajero(id);
        if (borrado) {
            System.out.println("Pasajero borrado correctamente.");
        } else {
            System.out.println("Error al borrar el pasajero.");
        }
    }

    public static void consultarPasajero(Scanner scanner, DaoPasajeroSQL daoPasajero) {
        System.out.print("Introduzca el ID del pasajero a consultar: ");
        int id = scanner.nextInt();
        Pasajero pasajero = daoPasajero.consultarPasajero(id);
        if (pasajero != null) {
            System.out.println("Pasajero consultado: " + pasajero);
        } else {
            System.out.println("No se encontró ningún pasajero con ese ID.");
        }
    }

    public static void listarTodosLosPasajeros(DaoPasajeroSQL daoPasajero) {
        List<Pasajero> pasajeros = daoPasajero.listarPasajeros();
        if (pasajeros != null) {
            System.out.println("Listado de pasajeros:");
            for (Pasajero pasajero : pasajeros) {
                System.out.println(pasajero);
            }
        } else {
            System.out.println("Error al obtener el listado de pasajeros.");
        }
    }

    public static void añadirPasajeroACoche(Scanner scanner, DaoPasajeroSQL daoPasajero, DaoCocheMySQL daoCoche) {
        System.out.print("Introduzca el ID del pasajero a añadir: ");
        int idPasajero = scanner.nextInt();
        System.out.print("Introduzca el ID del coche al que desea añadir el pasajero: ");
        int idCoche = scanner.nextInt();
        boolean añadido = daoCoche.addPasajero(idPasajero, idCoche);
        if (añadido) {
            System.out.println("Pasajero añadido al coche correctamente.");
        } else {
            System.out.println("Error al añadir el pasajero al coche.");
        }
    }

    public static void eliminarPasajeroDeCoche(Scanner scanner, DaoCocheMySQL daoCoche) {
        System.out.print("Introduzca el ID del coche del que desea eliminar el pasajero: ");
        int idCoche = scanner.nextInt();
        System.out.print("Introduzca el ID del pasajero a eliminar del coche: ");
        int idPasajero = scanner.nextInt();
        boolean eliminado = daoCoche.borrarPasajeroDeCoche(idPasajero, idCoche);
        if (eliminado) {
            System.out.println("Pasajero eliminado del coche correctamente.");
        } else {
            System.out.println("Error al eliminar el pasajero del coche.");
        }
    }

    public static void listarPasajerosDeCoche(Scanner scanner, DaoCocheMySQL daoCoche) {
        System.out.print("Introduzca el ID del coche para ver sus pasajeros: ");
        int idCoche = scanner.nextInt();
        List<Pasajero> pasajeros = daoCoche.listarPasajerosEnCoche(idCoche);
        if (pasajeros != null) {
            System.out.println("Pasajeros del coche:");
            for (Pasajero pasajero : pasajeros) {
                System.out.println(pasajero);
            }
        } else {
            System.out.println("Error al obtener el listado de pasajeros del coche.");
        }
    }
    
}
