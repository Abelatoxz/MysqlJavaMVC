package Controlador;
//importamos los otros modelos

import Modelo.Libro;
import Modelo.LibroModelo;
import Modelo.UsuarioModelo;
import Vista.LibroVista;
import Vista.UsuarioVista;

//importamos librerias utiles
import java.sql.SQLException;
import java.util.List;

public class LibroControlador {
    private final LibroModelo modelo;

    private final  LibroVista vista;

    public LibroControlador(LibroModelo modelo, LibroVista vista){
        this.modelo = modelo;
        this.vista = vista;
    }
    public void mostrarLibros() {
        try{
            List<Libro> libros = modelo.obtenerLibros();
            vista.mostrarLibros(libros);
        } catch (SQLException e){
            vista.mostrarMensajeError("Error al obtener libros: " + e.getMessage());
        }
    }
    public void agregarLibros() {
        Libro nuevoLibro = vista.obtenerDatosNuevoLibro();
        try{
            modelo.insertarLibro(nuevoLibro);
            vista.mostrarMensaje("Libro agregado correctamente");
        }catch (SQLException e){
            vista.mostrarMensajeError("Error al agregar el libro " + e.getMessage());
        }
    }

}
