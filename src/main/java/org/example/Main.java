package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File archivo = new File("libros.xml");
        GestorLibros gestorlibros = new GestorLibros(archivo);
        gestorlibros.iniciar(); //Inicia el metodo inciar() de gestorlibros
    }
}




