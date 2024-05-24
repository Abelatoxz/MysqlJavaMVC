package Controlador;

import Modelo.ValidadorLogin;
import Vista.Login;
import Vista.Menu;
import java.sql.*;
import javax.swing.*;

public class Controlador {
    private Login login;
    private Menu menu;
    private LibroControladorAdmin llibreAdmin;
    private UsuarioControladorAdmin usuariAdmin;
    private PrestamoControladorAdmin prestamoAdmin;
    private LibroControladorUser llibreUser;
    private UsuarioControladorUser usuariUser;
    private PrestamoControladorUser prestamoUser;
    public static String username;
    public static String password;

    public Controlador(Login login, Menu menu, LibroControladorAdmin llibreAdmin, UsuarioControladorAdmin usuariAdmin, LibroControladorUser llibreUser, UsuarioControladorUser usuariUser, PrestamoControladorAdmin prestamoAdmin, PrestamoControladorUser prestamoUser) {
        this.login = login;
        this.menu = menu;
        this.llibreAdmin = llibreAdmin;
        this.usuariAdmin = usuariAdmin;
        this.llibreUser = llibreUser;
        this.usuariUser = usuariUser;
        this.prestamoAdmin = prestamoAdmin;
        this.prestamoUser = prestamoUser;

        this.usuariAdmin.getVista().addVolverButtonListener(e -> ocultarUsuariAdmin());
        this.usuariUser.getVista().addVolverButtonListener(e -> ocultarUsuariUser());
        this.llibreUser.getVista().addVolverButtonListener(e -> ocultarLlibreUser());
        this.llibreAdmin.getVista().addVolverButtonListener(e -> ocultarLlibreAdmin());
        this.prestamoUser.getVista().addVolverButtonListener(e -> ocultarPrestamoUser());
        this.prestamoAdmin.getVista().addVolverButtonListener(e -> ocultarPrestamoAdmin());
        this.login.addLoginButtonListener(e -> mostrarMenu());
    }

    public void mostrarMenu() {
        username = login.getUsuariField().getText();
        password = new String(login.getPasswordField().getPassword());

        ValidadorLogin valida = new ValidadorLogin(username, password);

        try {
            if (valida.validar()) {
                int userId = valida.obtenerIdUsuario();
                boolean isAdmin = valida.esAdmin(userId);

                if (isAdmin) {
                    menu.addLibroButtonListener(e -> mostrarLlibreAdmin());
                    menu.addUserButtonListener(e -> mostrarUsuariAdmin());
                    menu.addPrestecButtonListener(e -> mostrarPrestamoAdmin());
                } else {
                    menu.addLibroButtonListener(e -> mostrarLlibreUser());
                    menu.addUserButtonListener(e -> mostrarUsuariUser());
                    menu.addPrestecButtonListener(e -> mostrarPrestamoUser());
                }

                login.setVisible(false);
                menu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(login, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(login, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(login, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarLlibreAdmin() {
        llibreAdmin.mostrarVentana();
        menu.setVisible(false);
    }

    public void mostrarUsuariAdmin() {
        usuariAdmin.mostrarVentana();

        menu.setVisible(false);
    }

    public void mostrarPrestamoAdmin() {
        prestamoAdmin.mostrarVentana();
        menu.setVisible(false);
    }

    public void mostrarLlibreUser() {
        llibreUser.mostrarVentana();
        menu.setVisible(false);
    }

    public void mostrarUsuariUser() {
        usuariUser.mostrarVentana();
        menu.setVisible(false);
    }

    public void mostrarPrestamoUser() {
        prestamoUser.mostrarVentana();
        menu.setVisible(false);
    }

    public void ocultarLlibreAdmin() {
        llibreAdmin.ocultarVentana();;
        menu.setVisible(true);
    }

    public void ocultarUsuariAdmin() {
        usuariAdmin.ocultarVentana();
        menu.setVisible(true);
    }

    public void ocultarPrestamoAdmin() {
        prestamoAdmin.ocultarVentana();
        menu.setVisible(true);
    }

    public void ocultarLlibreUser() {
        llibreUser.ocultarVentana();
        menu.setVisible(true);
    }

    public void ocultarUsuariUser() {
        usuariUser.ocultarVentana();
        menu.setVisible(true);
    }

    public void ocultarPrestamoUser() {
        prestamoUser.ocultarVentana();
        menu.setVisible(true);
    }
}