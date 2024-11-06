package org.example;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class GestorLibros {
    //Instancias y objeto para manejar la persistencia y exportación de libros.
    private XmlLibroPersistencia persistencia;
    private CsvExportarLibros exportador;
    private Libros libros;

    //Constructor
    public GestorLibros(File archivo){
        //Inicializar la instancia y cargaLibros al objeto libros
        this.persistencia = new XmlLibroPersistencia(archivo);
        this.exportador = new CsvExportarLibros();
        this.libros = persistencia.cargarLibros();
    }

    //Metodo para iniciar la app
    public void iniciar() {
        try(Scanner sc = new Scanner(System.in)){
            boolean salir = false;

            while (!salir) {
                mostrarMenu();

                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        agregarLibro(sc);
                        break;
                    case 2:
                        listarLibros();
                        break;
                    case 3:
                        modificarLibro(sc);
                        break;
                    case 4:
                        eliminarLibro(sc);
                        break;
                    case 5:
                        exportarCSV();
                        break;
                    case 6:
                        salir = true;
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");

                }
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    //Menu
    private void mostrarMenu() {
        System.out.println("--------- M E N Ú ----------");
        System.out.println("1. Agregar un nuevo libro  |");
        System.out.println("2. Listar todos los libros |");
        System.out.println("3. Modificar un libro      |");
        System.out.println("4. Eliminar un libro       |");
        System.out.println("5. Exportar a CSV          |");
        System.out.println("6. Salir                   |");
        System.out.println("----------------------------");
        System.out.print("Elige una opción: ");
    }

    //agregar libro al XML
    public void agregarLibro(Scanner sc) {
        System.out.print("Título del libro: ");
        String scTitulo = sc.nextLine();
        System.out.print("Autor del libro: ");
        String scAutor = sc.nextLine();
        System.out.print("Año de publicación: ");
        String scPublicacion = sc.nextLine();

        //Crear objeto Libro con scTitulo, scAutor, scPublicacion
        Libro nuevoLibro = new Libro(scTitulo, scAutor, scPublicacion);

        //Obtiene la lista de libros y agrega nuevolibro a la lista
        List<Libro> lista = libros.getListaLibros();
        lista.add(nuevoLibro);
        //actualiza la lista de libros
        libros.setListaLibros(lista);

        //Guardar cambios en el XML
        persistencia.guardarLibros(libros);

        System.out.println();
        System.out.println("--> Libro agregado!: " + scTitulo);
        System.out.println();
    }

    //listar libros del XML
    public void listarLibros() {
        System.out.println();

        //Obtiene la lista de libros
        List<Libro> lista = libros.getListaLibros();

        //Si no hay libros..
        if(lista.isEmpty()){
            System.out.println("No hay libros en el XML. ");
        }else{//si no... muestra la lista
            System.out.println("----- LISTA DE LIBROS ----");
            System.out.println("--------------------------");
            for (Libro libro : lista) {//bucle foreach que imprime cada libro
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Año de Publicación: " + libro.getPublicacion());
        }
            System.out.println("--------------------------");
        }

    }

    //modificar libro del XML
    public void modificarLibro(Scanner sc) {
        listarLibros();//Muestra la lista de libro con el metodo listarLibros()

        //Si no hay libros evita modificar
        if (libros.getListaLibros().isEmpty()) return;

        System.out.print("Selecciona el número del libro que deseas modificar: ");
        //Lee la posicion del libro que introduce el usuario
        int seleccion = sc.nextInt() - 1;
        sc.nextLine();

        //Obtiene la lista de libros
        List<Libro> lista = libros.getListaLibros();

        //Si seleccion es válida...
        if (seleccion >= 0 && seleccion < lista.size()) {
            //Obtiene el libro seleccionado
            Libro libroSeleccionado = lista.get(seleccion);

            //título
            System.out.print("Nuevo título (actual: " + libroSeleccionado.getTitulo() + "): ");
            String nuevoTitulo = sc.nextLine();
            libroSeleccionado.setTitulo(nuevoTitulo);

            //Autor
            System.out.print("Nuevo autor (actual: " + libroSeleccionado.getAutor() + "): ");
            String nuevoAutor = sc.nextLine();
            libroSeleccionado.setAutor(nuevoAutor);

            //Año publicación
            System.out.print("Nuevo año de publicación (actual: " + libroSeleccionado.getPublicacion() + "): ");
            String nuevoPublicacion = sc.nextLine();
            libroSeleccionado.setPublicacion(nuevoPublicacion);

            //Guardar cambios en el XML
            persistencia.guardarLibros(libros);

            System.out.println();
            System.out.println("--> Libro Modificado!");
            System.out.println();
        } else {
            System.out.println("La elección del libro no es válida.");
        }
    }

    //eliminar libro del XML
    public void eliminarLibro(Scanner sc) {
        listarLibros();//Muestra la lista de libro con el metodo listarLibros()

        //Si no hay libros evita eliminar
        if (libros.getListaLibros().isEmpty()) return;

        System.out.print("Selecciona el número del libro que deseas eliminar: ");
        //Lee la posicion del libro que introduce el usuario
        int seleccion = sc.nextInt() - 1;
        sc.nextLine();

        //Obtiene la lista de libros
        List<Libro> lista = libros.getListaLibros();

        //Si seleccion es válida...
        if (seleccion >= 0 && seleccion < lista.size()) {

            lista.remove(seleccion);//Elimina el libro seleccionado
            libros.setListaLibros(lista); //Actu lista

            //Guardar cambios en el XML
            persistencia.guardarLibros(libros);

            System.out.println();
            System.out.println("--> Libro eliminado!");
            System.out.println();

        } else {
            System.out.println("La elección del libro no es válida.");
        }
    }

    //exportar a CSV
    public void exportarCSV() {
        //Llama al exportador para crear el CSV
        exportador.exportar(libros.getListaLibros(), "libros.csv");

    }
}



