package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private String fechaPrestamo;
    private String fechaPrestamoPrevista;
    private String fechaPrestamoReal;
    Prestamo(int idPrestamo, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaPrestamoPrevista,Date fechaPrestamoReal){
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamo);
        this.fechaPrestamoPrevista = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamoPrevista);
        this.fechaPrestamoReal = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamoReal);
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaPrestamoPrevista() {
        return fechaPrestamoPrevista;
    }

    public void setFechaPrestamoPrevista(String fechaPrestamoPrevista) {
        this.fechaPrestamoPrevista = fechaPrestamoPrevista;
    }

    public String getFechaPrestamoReal() {
        return fechaPrestamoReal;
    }

    public void setFechaPrestamoReal(String fechaPrestamoReal) {
        this.fechaPrestamoReal = fechaPrestamoReal;
    }
}
