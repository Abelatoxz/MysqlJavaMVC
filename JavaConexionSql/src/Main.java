import Controlador.UsuarioControlador;
import Modelo.UsuarioModelo;
import Vista.UsuarioVista;
import Controlador.LibroControlador;
import Modelo.LibroModelo;
import Vista.LibroVista;
import Vista.PrestamoVista;
import Modelo.PrestamoModelo;
import Modelo.Prestamo;
import Controlador.PrestamoControlador;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import Vista.LibroVistaGUI;

public class Main {
    public static void main(String[] args) {
        // Crear instancias del modelo, la vista y el controlador del usuario
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        UsuarioVista usuarioVista = new UsuarioVista();
         UsuarioControlador usuarioControlador = new UsuarioControlador(usuarioModelo, usuarioVista);
        // Crear instancias del modelo, la vista y el controlador del libro

















        LibroModelo libroModelo = new LibroModelo();
        LibroVistaGUI libroVistaGui = new LibroVistaGUI();
        LibroControlador libroControlador = new LibroControlador(libroModelo,libroVistaGui);
        libroVistaGui.setVisible(true);
        
        if (libroControlador.agregarLibros()) {
            libroControlador.mostrarLibrosGui();
        }

        

















        //Crear instancias del modelo, la vista y el controlador de los prestamos
        PrestamoModelo prestamoModelo = new PrestamoModelo();
        PrestamoVista prestamoVista = new PrestamoVista();
        PrestamoControlador prestamoControlador = new PrestamoControlador(prestamoModelo,prestamoVista);
        // Mostrar usuarios
        //libroControlador.cambiarEstadoPrestado();
        //libroControlador.cambiarEstadoPrestado();

       // prestamoControlador.darDeBajaLibro();


    }
}
