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
    private String estado;

    public Prestamo(int idPrestamo, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaPrestamoPrevista,String fechaPrestamoReal, String estado){
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamo);
        this.fechaPrestamoPrevista = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamoPrevista);
        this.fechaPrestamoReal = fechaPrestamoReal;
        this.estado = estado;
    }
    //sobre carga ya que la fecha de prestamo prevista tiene nulls, y si intento contruir con eso, cagada.
    Prestamo(int idPrestamo, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaPrestamoPrevista, String estado){
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamo);
        this.fechaPrestamoPrevista = new SimpleDateFormat("yyyy-MM-dd").format(fechaPrestamoPrevista);
        this.fechaPrestamoReal = null;

        this.estado = estado;
    }
    //getters and setters
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaPrestamoReal(String fechaPrestamoReal) {
        this.fechaPrestamoReal = fechaPrestamoReal;
    }
    @Override
    public String toString(){
        return  "Prestamo   {" +
                "idPrestamo="+ idPrestamo +
                ", idLibro="+ idLibro +
                ", idUsuario= " + idUsuario +
                ", fechaPrestamo= " + fechaPrestamo +
                ", fechaRetornoPrevista=" + fechaPrestamoPrevista +
                ", fechaRetornoReal=" + fechaPrestamoReal +
                ", estado=" + estado +" }";
    }
}