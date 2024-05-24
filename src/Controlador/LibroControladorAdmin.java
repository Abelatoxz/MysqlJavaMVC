package Controlador;

import Modelo.Libro;
import Modelo.LibroModelo;
import Vista.LibroVistaAdminGUI;
import excepciones.CamposVacios;

import java.sql.SQLException;
import java.util.List;

public class LibroControladorAdmin {
    private final LibroModelo modelo;
    private final LibroVistaAdminGUI vista;

    public LibroControladorAdmin(LibroModelo modelo, LibroVistaAdminGUI vista) {
        this.modelo = modelo;
        this.vista = vista;
        mostrarLibros();
        // Agregar listeners a los botones
        this.vista.addAgregarButtonListener(e -> agregarLibros());
        this.vista.addEditarButtonListener(e -> editarLibros());
        this.vista.addEliminarButtonListener(e -> eliminarLibros());
    }

    public LibroVistaAdminGUI getVista() {
        return vista;
    }

    public void eliminarLibros() {
        try {
            int[] IDs = vista.obtenerIDsParaBorrar();

            modelo.borrarLibro(IDs);
            mostrarLibros();
            vista.mostrarMensaje("Libros eliminados correctamente.");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al eliminar libros: " + e.getMessage());
        }catch (CamposVacios e){
            vista.mostrarMensajeError(e.getMessage());

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
        try {
            Libro nuevoLibro = vista.obtenerDatosNuevoLibro();

            modelo.insertarLibro(nuevoLibro);
            mostrarLibros();
            vista.mostrarMensaje("Libro agregado correctamente");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al agregar el libro " + e.getMessage());
        }
        catch (CamposVacios e){
            vista.mostrarMensajeError(e.getMessage());
        } catch (Exception e) {
            vista.mostrarMensajeError(e.getMessage());

        }
    }

    public void editarLibros() {


        try {
            Libro editarLibro = vista.obtenerLibroDesdeTabla();

            modelo.editarLibro(editarLibro);
            mostrarLibros();
            vista.mostrarMensaje("Libro editado correctamente");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al editar el libro " + e.getMessage());
        }catch (CamposVacios e){
            vista.mostrarMensajeError(e.getMessage());
        }
    }

    public void mostrarVentana(){
        vista.setVisible(true);
    }

    public void ocultarVentana(){
        vista.setVisible(false);
    }
}
