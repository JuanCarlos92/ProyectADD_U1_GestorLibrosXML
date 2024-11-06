
# Gestor de Libros XML

Este proyecto es un **gestor de libros** en Java que utiliza **JAXB** para leer y escribir libros en un archivo **XML**. La aplicación permite realizar operaciones básicas como agregar, listar, modificar y eliminar libros, así como exportar la lista a un archivo **CSV**.

## Funcionalidades

- **Agregar libros**: Permite añadir nuevos libros al archivo XML.
- **Listar libros**: Muestra todos los libros almacenados en el archivo XML.
- **Modificar libros**: Cambia los detalles de un libro existente.
- **Eliminar libros**: Elimina un libro del archivo XML.
- **Exportar a CSV**: Exporta la lista de libros a un archivo CSV.

## Requisitos

- JDK 23 o superior
- Maven para gestión de dependencias

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/JuanCarlos92/ProyectADD_U1_GestorLibrosXML.git
   ```

2. Entra en el directorio del proyecto:
   ```bash
   cd ProyectADD_U1_GestorLibrosXML
   ```

3. Compila y ejecuta el proyecto:
   ```bash
   mvn clean install
   mvn exec:java
   ```

## Uso

El programa se ejecuta desde la terminal y permite interactuar con el sistema de gestión de libros mediante un menú.

## Estructura del Proyecto

- **Libro**: Representa un libro con propiedades como título, autor y año de publicación.
- **Libros**: Contenedor que maneja una lista de objetos `Libro`.
- **XmlLibroPersistencia**: Clase para persistir los libros en un archivo XML.
- **CsvExportarLibro**: Permite exportar los libros a un archivo CSV.
- **GestorLibros**: Clase principal que gestiona las operaciones de agregar, listar, modificar, eliminar y exportar.


## Licencia

Este proyecto está bajo la licencia MIT.
