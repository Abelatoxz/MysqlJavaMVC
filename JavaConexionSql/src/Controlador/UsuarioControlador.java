package Controlador;
//importamos los otros modelos
import Modelo.Usuario;
import Modelo.UsuarioModelo;
import Vista.UsuarioVista;

//importamos librerias utiles
import java.sql.SQLException;
import java.util.List;

public class UsuarioControlador {
    private final UsuarioModelo modelo;
    private final UsuarioVista vista;

    public UsuarioControlador(UsuarioModelo modelo, UsuarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
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
        Usuario nuevoUsuario = vista.obtenerDatosNuevoUsuario();
        try{
            modelo.insertarUsuario(nuevoUsuario);
            vista.mostrarMensaje("Usuario agregado correctamente");
        }catch (SQLException e){
            vista.mostrarMensajeError("Error al agregar el usuaro " + e.getMessage());
        }
    }

}
