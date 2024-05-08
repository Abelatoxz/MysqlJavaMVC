package Modelo;

import java.util.Date;
import java.text.SimpleDateFormat;
public class Libro {
    private int id;
    private String titol;
    private String autor;
    private String ISBN;
    private String editorial;
    private String fechaPublicacion;
    private String categoria;
    private String estado;
    //Constructor
    public Libro(int id,String titol, String autor, String ISBN, String editorial, Date fechaPublicacion, String categoria, String estado ){
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.fechaPublicacion = new SimpleDateFormat("yyyy").format(fechaPublicacion);
        this.categoria = categoria;
        this.estado = estado;
    }
    //Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitol(){
        return titol;
    }
    public void setTitol(String titol){
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public String toString(){
        return  "Libro    {" +
                "ISBN="+ ISBN +
                ", titulo= " + titol +
                ", autor= " + autor +
                ", editorial=" + editorial +
                ", fecha de publicacion=" + fechaPublicacion +
                ", categoria=" + categoria +
                ", estado="+ estado;
    }
}
