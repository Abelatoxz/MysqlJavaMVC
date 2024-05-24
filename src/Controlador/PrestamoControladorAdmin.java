package Controlador;

import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.PrestamoModelo;
import Vista.PrestamoVistaAdminGUI;
import excepciones.CamposVacios;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PrestamoControladorAdmin {
    PrestamoModelo modelo;
    PrestamoVistaAdminGUI vista;
    public PrestamoControladorAdmin(PrestamoVistaAdminGUI vista, PrestamoModelo modelo){
        this.modelo = modelo;
        this.vista = vista;
        mostrarPrestamo();
        this.vista.addPrestamoButtonListener(e->agregarPrestamo());
        this.vista.addMostrarButtonListener(e->mostrarPrestamo());
        this.vista.addactualizarButtonListener(e->darDeAlta());
    }

    public PrestamoVistaAdminGUI getVista() {
        return vista;
    }

    public void darDeAlta(){
        try {
            Prestamo prestamo = vista.obtenerPrestamoDesdeTabla();
            modelo.insertarPrestamoAlta(prestamo);
            mostrarPrestamo();
            vista.mostrarMensaje("Se ha dado de alta");
        }catch (SQLException e){
            vista.mostrarMensaje("Error " + e.getMessage());
        }
        catch (Exception e){
            vista.mostrarMensaje("Seleccione un campo");
        }
    }
    public void agregarPrestamo(){
        try {
            int[] IDs = vista.obtenerIDsParaPrestamo();
            modelo.insertarPrestamo(IDs);
            mostrarPrestamo();
            vista.mostrarMensaje("Prestamo agregado");
        }catch (SQLException e){
            vista.mostrarMensajeError("Error " + e.getMessage());
        }
        catch (CamposVacios e){
            vista.mostrarMensajeError(e.getMessage());
        }
        catch (Exception e){
            vista.mostrarMensajeError(e.getMessage());
        }
    }

    public void mostrarPrestamo(){
        try {
            List<Prestamo> prestamo = modelo.obtenerPrestamo();
            vista.mostrarPrestamos(prestamo);
        } catch (SQLException e) {
            vista.mostrarMensajeError("Error al obtener los prestamos: " + e.getMessage());
        }
        catch (Exception e){
            vista.mostrarMensaje("Error:" + e.getMessage());
        }
    }


    public void mostrarVentana(){
        vista.setVisible(true);
    }

    public void ocultarVentana(){
        vista.setVisible(false);
    }
}

