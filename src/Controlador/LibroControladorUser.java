package Controlador;

import Modelo.Libro;
import Modelo.LibroModelo;
import Vista.LibroVistaAdminGUI;
import Vista.LibroVistaUserGUI;

import java.sql.SQLException;
import java.util.List;

public class LibroControladorUser {
    private final LibroModelo modelo;
    private final LibroVistaUserGUI vista;

    public LibroControladorUser(LibroModelo modelo, LibroVistaUserGUI vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Agregar listeners a los botones
        this.vista.addMostrarButtonListener(e -> mostrarLibros());
    }

    public LibroVistaUserGUI getVista() {
        return vista;
    }

    public void eliminarLibros() {
        try {
            int[] IDs = vista.obtenerIDsParaBorrar();
            modelo.borrarLibro(IDs);
            vista.mostrarMensaje("Libros eliminados correctamente.");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al eliminar libros: " + e.getMessage());
        }
    }

    public void mostrarLibros() {
        try {
            List<Libro> libros = modelo.obtenerLibros();
            vista.mostrarLibros(libros);
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al obtener libros: " + e.getMessage());
        }
    }

    public void agregarLibros() {
        Libro nuevoLibro = vista.obtenerDatosNuevoLibro();
        try {
            modelo.insertarLibro(nuevoLibro);
            vista.mostrarMensaje("Libro agregado correctamente");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al agregar el libro " + e.getMessage());
        }
    }

    public void editarLibros() {
        Libro editarLibro = vista.obtenerDatosEditarLibro();
        try {
            modelo.editarLibro(editarLibro);
            vista.mostrarMensaje("Libro editado correctamente");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al editar el libro " + e.getMessage());
        }
    }

    public void mostrarVentana(){
        vista.setVisible(true);
    }

    public void ocultarVentana(){
        vista.setVisible(false);
    }
}
