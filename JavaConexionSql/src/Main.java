import Controlador.UsuarioControlador;
import Modelo.UsuarioModelo;
import Vista.UsuarioVista;
import Controlador.LibroControlador;
import Modelo.LibroModelo;
import Vista.LibroVista;

public class Main {
    public static void main(String[] args) {
        // Crear instancias del modelo, la vista y el controlador del usuario
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        UsuarioVista usuarioVista = new UsuarioVista();
        // UsuarioControlador usuarioControlador = new UsuarioControlador(usuarioModelo, usuarioVista);
        // Crear instancias del modelo, la vista y el controlador del libro

        LibroModelo libroModelo = new LibroModelo();
        LibroVista libroVista = new LibroVista();
        LibroControlador libroControlador = new LibroControlador(libroModelo,libroVista);
        // Mostrar usuarios
       // usuarioControlador.mostrarUsuarios();
       // usuarioControlador.agregarUsuario();
        libroControlador.mostrarLibros();
        //libroControlador.editarLibros();
        libroControlador.eliminarLibros();
    }
}
