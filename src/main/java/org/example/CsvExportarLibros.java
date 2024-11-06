package org.example;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvExportarLibros {

    //Metodo para exportar la lista de libros a CSV
    public void exportar(List<Libro> libros, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            for (Libro libro : libros) {//Bucle foreach que recorre cada libro en la lista

                //Escribe cada libros con los getters de la clase
                writer.write("Titulo: " + libro.getTitulo() + ", Autor: " + libro.getAutor() +
                        ", Año de publicación: " + libro.getPublicacion());

                //Inserta nueva linea
                writer.newLine();
            }

            System.out.println();
            System.out.println("--> Libros exportados a " + nombreArchivo + "!");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}
