package Controlador;

import Modelo.Prestamo;
import Modelo.PrestamoModelo;
import Modelo.ValidadorLogin;
import Vista.PrestamoVistaAdminGUI;
import Vista.PrestamoVistaUserGUI;
import excepciones.CamposVacios;

import java.sql.SQLException;
import java.util.List;

public class PrestamoControladorUser {
    PrestamoModelo modelo;
    PrestamoVistaUserGUI vista;
    public PrestamoControladorUser(PrestamoVistaUserGUI vista, PrestamoModelo modelo){
        this.modelo = modelo;
        this.vista = vista;

        this.vista.addPrestamoButtonListener(e->agregarPrestamo());
        this.vista.addMostrarButtonListener(e->mostrarPrestamo());
    }

    public PrestamoVistaUserGUI getVista() {
        return vista;
    }

    public void agregarPrestamo(){
        try{
            ValidadorLogin valida = new ValidadorLogin(Controlador.username, Controlador.password);
            int[] ids = vista.obtenerIDsParaPrestamo();
            ids[1] = valida.obtenerIdUsuario();
            modelo.insertarPrestamo(ids);
            mostrarPrestamo();
            vista.mostrarMensaje("Prestamo agregado exitosamente");
        }catch (Exception e){
            vista.mostrarMensaje("Error al agregar el prestamo: " + e.getMessage());
        }
    }

    public void darDeAlta(){
        try {
            Prestamo pr = vista.obtenerPrestamoDesdeTabla();
            modelo.insertarPrestamoAlta(pr);
            mostrarPrestamo();
            vista.mostrarMensaje("Prestamo agregado exitosamente");
        }catch (SQLException | CamposVacios e){
            vista.mostrarMensajeError("Prestamo mal: " + e.getMessage());
        }
    }

    public void mostrarPrestamo(){
        try {
            ValidadorLogin valida = new ValidadorLogin(Controlador.username, Controlador.password);
            List<Prestamo> prestamo = modelo.obtenerPrestamoUsuario(valida.obtenerIdUsuario());
            vista.mostrarPrestamos(prestamo);
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al obtener los prestamos: " + e.getMessage());
        } catch (Exception e){
            vista.mostrarMensajeError("Error al obtener los prestamos: " + e.getMessage());

        }
    }

    public void mostrarVentana(){
        vista.setVisible(true);
    }

    public void ocultarVentana(){
        vista.setVisible(false);
    }
}