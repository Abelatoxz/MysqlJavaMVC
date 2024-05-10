package Controlador;

import Modelo.Prestamo;
import Modelo.PrestamoModelo;
import Vista.PrestamoVista;


import java.sql.SQLException;
import java.util.List;
public class PrestamoControlador {
    private final PrestamoModelo modelo;
    private final PrestamoVista vista;

    public PrestamoControlador(PrestamoModelo modelo, PrestamoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    public void mostrarPrestamos(){
        try {
            List<Prestamo> prestamos = modelo.obtenerPrestamo();
            vista.mostrarPrestamos(prestamos);
        }catch (SQLException e){
            vista.mostrarMensajeError("Error al mostrar los prestamos: " + e.getMessage());
        }
    }
    public void darDeBajaLibro(){
        try {
            int[] IDs = vista.obtenerIDsUsuarioLibro();
            modelo.insertarPrestamoAlta(IDs);
            vista.mostrarMensaje("Dar de baja completado");
        }catch (SQLException e){
            vista.mostrarMensajeError("Error: " + e.getMessage());
        }
    }
}
