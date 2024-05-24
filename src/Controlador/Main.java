package Controlador;

import Modelo.LibroModelo;
import Modelo.PrestamoModelo;
import Modelo.UsuarioModelo;

import Vista.*;


public class Main {
    public static void main(String[] args) {
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        UsuarioVistaAdminGUI usuarioVistaAdmin = new UsuarioVistaAdminGUI();
        UsuarioVistaUserGUI usuarioVistaUser = new UsuarioVistaUserGUI();
        UsuarioControladorAdmin usuarioControladorAdmin = new UsuarioControladorAdmin(usuarioModelo, usuarioVistaAdmin);
        UsuarioControladorUser usuarioControladorUser = new UsuarioControladorUser(usuarioModelo, usuarioVistaUser);

        LibroModelo libroModelo = new LibroModelo();
        LibroVistaAdminGUI libroVistaAdmin = new LibroVistaAdminGUI();
        LibroVistaUserGUI libroVistaUser = new LibroVistaUserGUI();
        LibroControladorAdmin libroControladorAdmin = new LibroControladorAdmin(libroModelo, libroVistaAdmin);
        LibroControladorUser libroControladorUser = new LibroControladorUser(libroModelo, libroVistaUser);

        PrestamoModelo prestamoModelo = new PrestamoModelo();
        PrestamoVistaAdminGUI prestamoVistaAdmin = new PrestamoVistaAdminGUI();
        PrestamoVistaUserGUI prestamoVistaUser = new PrestamoVistaUserGUI();
        PrestamoControladorAdmin prestamoControladorAdmin = new PrestamoControladorAdmin(prestamoVistaAdmin, prestamoModelo);
        PrestamoControladorUser prestamoControladorUser = new PrestamoControladorUser(prestamoVistaUser, prestamoModelo);

        Menu menu = new Menu();
        Login login = new Login();

        Controlador controlador = new Controlador(login, menu, libroControladorAdmin, usuarioControladorAdmin, libroControladorUser, usuarioControladorUser, prestamoControladorAdmin, prestamoControladorUser);
    }
}