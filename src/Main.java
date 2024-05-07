import Controlador.UsuarioControlador;
import Modelo.UsuarioModelo;
import Vista.UsuarioVista;

public class Main {
    public static void main(String[] args) {
        // Crear instancias del modelo, la vista y el controlador
        UsuarioModelo modelo = new UsuarioModelo();
        UsuarioVista vista = new UsuarioVista();
        UsuarioControlador controlador = new UsuarioControlador(modelo, vista);

        // Mostrar usuarios
        controlador.mostrarUsuarios();

        // Otros llamados a métodos del controlador según sea necesario
    }
}
