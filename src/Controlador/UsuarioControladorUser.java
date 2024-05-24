package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioModelo;
import Modelo.ValidadorLogin;
import Vista.UsuarioVistaAdminGUI;
import Vista.UsuarioVistaUserGUI;
import excepciones.CamposVacios;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;


public class UsuarioControladorUser {
    private final UsuarioModelo modelo;
    private final UsuarioVistaUserGUI vista;

    public UsuarioControladorUser(UsuarioModelo modelo, UsuarioVistaUserGUI vista) {
        this.modelo = modelo;
        this.vista = vista;
        // Agregar listeners a los botones
        this.vista.addMostrarButtonListener(e-> mostrarUsuario());
        this.vista.addEditarButtonListener(e -> editarUsuario());
    }

    public UsuarioVistaUserGUI getVista() {
        return vista;
    }

    public void mostrarUsuario() {
        try {
            ValidadorLogin getid = new ValidadorLogin(Controlador.username, Controlador.password);
            Usuario usuario = modelo.obtenerUsuario(getid.obtenerIdUsuario());
            vista.mostrarUsuario(usuario);
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al obtener usuarios: " + e.getMessage());
        }
    }

    public void editarUsuario() {
        mostrarUsuario();
        try {
            Usuario usuarioActualizado = vista.obtenerDatosUsuarioEditar();
            modelo.editarUsuario(usuarioActualizado);
            vista.mostrarMensaje("Usuario editado correctamente");
            mostrarUsuario();
        } catch (SQLException  | NoSuchAlgorithmException | CamposVacios e) {
            vista.mostrarMensajeError("Error al editar el usuario: " + e.getMessage());
        }
    }

    public void mostrarVentana(){
        vista.setVisible(true);
    }

    public void ocultarVentana(){
        vista.setVisible(false);
    }
}
