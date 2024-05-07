package Vista;

import Modelo.Usuario;

import java.util.List;

public class UsuarioVista {
    public void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para mostrar.");
        } else {
            System.out.println("Lista de Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    public void mostrarMensajeError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    // Otros métodos de la vista para manejar otras interacciones con el usuario, como agregar, actualizar, eliminar, etc.
}
