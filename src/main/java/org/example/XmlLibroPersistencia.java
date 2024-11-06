package org.example;
import jakarta.xml.bind.*;
import java.io.File;

public class XmlLibroPersistencia {
    private File archivo; //Archivo donde se almacena los datos de los libros en XML

    //Constructor que inicializa la clase con el archivo
    public XmlLibroPersistencia(File archivo) {
        this.archivo = archivo;
    }

    //Metodo para cargar los libros desde el XML
    public Libros cargarLibros() {
        try {
            //Si archivo existe....
            if (archivo.exists()) {
                //Configura JAXBContext para la clase Libros
                JAXBContext context = JAXBContext.newInstance(Libros.class);
                //Crea el objeto Unmarshaller
                Unmarshaller unmarshaller = context.createUnmarshaller();
                //Convierte XML a objeto Libros
                return (Libros) unmarshaller.unmarshal(archivo);
            }

        } catch (JAXBException e) {
            System.out.println("Error al cargar libros: " + e.getMessage());
        }
        return new Libros(); //Retorna nueva instancia si no existe
    }

    //Metodo para guardar la lista de libros en el XML
    public void guardarLibros(Libros libros) {
        try {
            //configura JAXBContext para la clase Libros
            JAXBContext context = JAXBContext.newInstance(Libros.class);
            //Crea el objeto de la clase Marshaller
            Marshaller marshaller = context.createMarshaller();
            //Formatea el XML para que sea legible (forma arbol)
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Convierte el objeto Libros a XML y se guarda en archivo
            marshaller.marshal(libros, archivo);

        } catch (JAXBException e) {
            System.out.println("Error al guardar libros: " + e.getMessage());
        }
    }
}