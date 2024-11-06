package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

//Define la clase como la raíz del XML con el nombre "libros"
@XmlRootElement(name = "libros")
public class Libros {
    //Lista para almacenar los objetos libro
    private List<Libro> listaLibros;

    //Metodo para obtener la lista de libros, con anotación para cada "libro" en XML
    @XmlElement(name = "libro")
    public List<Libro> getListaLibros() {
        //Si es null inicializa la lsita creando una vacía
        if(listaLibros ==null){
            listaLibros = new ArrayList<>();
        }
        return listaLibros;
    }

    //Metodo para actualizar la listaLibros (recoge la lista por parámetros y actualiza la anterior
    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
}