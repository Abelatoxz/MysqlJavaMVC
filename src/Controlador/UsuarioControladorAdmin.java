package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioModelo;
import Vista.Menu;
import Vista.UsuarioVistaAdminGUI;
import excepciones.CamposVacios;

import java.sql.SQLException;
import java.util.List;
import java.security.NoSuchAlgorithmException;


public class UsuarioControladorAdmin {
    private final UsuarioModelo modelo;
    private final UsuarioVistaAdminGUI vista;

    public UsuarioControladorAdmin(UsuarioModelo modelo, UsuarioVistaAdminGUI vista) {
        this.modelo = modelo;
        this.vista = vista;
        mostrarUsuarios();

        // Agregar listeners a los botones
        this.vista.addAgregarButtonListener(e -> agregarUsuario());
        this.vista.addEditarButtonListener(e -> editarUsuario());
        this.vista.addEliminarButtonListener(e -> eliminarUsuario());
    }



    public void mostrarUsuarios() {
        try {
            List<Usuario> usuarios = modelo.obtenerUsuarios();
            vista.mostrarUsuarios(usuarios);
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al obtener usuarios: " + e.getMessage());
        }
    }

    public void agregarUsuario() {
        try {
            Usuario nuevoUsuario = vista.obtenerDatosUsuario();
            modelo.insertarUsuario(nuevoUsuario);
            mostrarUsuarios();

            vista.mostrarMensaje("Usuario agregado correctamente");
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al agregar el usuario: " + e.getMessage());
        }catch (NoSuchAlgorithmException e){
            vista.mostrarMensajeError("Error al agregar el usuario: " + e.getMessage());
        }catch (CamposVacios e){
            vista.mostrarMensajeError("Error al agregar el usuario: " + e.getMessage());
        }catch (Exception e){
            vista.mostrarMensajeError("Error al agregar el usuario: " + e.getMessage());
        }

    }

    public void editarUsuario() {
        mostrarUsuarios();

        try {
            Usuario usuario = vista.obtenerUsuariosDesdeTabla();
            modelo.editarUsuario(usuario);
            vista.mostrarMensaje("Usuario editado correctamente");
            mostrarUsuarios(); // Actualizar la tabla en la interfaz de usuario
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al editar el usuario: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            vista.mostrarMensajeError("Error al editar el usuario: " + e.getMessage());
        } catch (CamposVacios e){
            vista.mostrarMensajeError("Error al editar el usuario: " + e.getMessage());
        }
    }


    public void eliminarUsuario() {

        try {
            int[] IDs = vista.obtenerIDsParaBorrar();
            modelo.eliminarUsuario(IDs);
            mostrarUsuarios(); // Refrescar la lista de usuarios
            vista.mostrarMensaje("Usuario eliminado correctamente");

        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al eliminar el usuario: " + e.getMessage());
        }catch(CamposVacios e){
            vista.mostrarMensajeError("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    public UsuarioVistaAdminGUI getVista() {
        return vista;
    }

    public void mostrarVentana(){
        vista.setVisible(true);
    }

    public void ocultarVentana(){
        vista.setVisible(false);
    }
}
