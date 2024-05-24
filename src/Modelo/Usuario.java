package Modelo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;
    private String fechaRegistro;
    private int multa;
    private String contrasenya;

    // Constructor para nuevo usuario sin ID

    //        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Email", "Teléfono", "Rol","Fecha de Creacion"}, 0);
    public Usuario(int id, String nombre, String apellido, String email, String telefono, String rol, Date fechaRegistro, int multa) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").format(fechaRegistro);
        this.multa = multa;
    }
    // Constructor completo
    public Usuario( String nombre, String apellido, String email, String telefono, String rol, Date fechaRegistro, int multa,String contrasenya) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").format(fechaRegistro);
        this.multa = multa;
        this.contrasenya = contrasenya;
    }
    public Usuario(int id, String nombre, String apellido, String email, String telefono, String rol, Date fechaRegistro, int multa,String contrasenya) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").format(fechaRegistro);
        this.multa = multa;
        this.contrasenya = contrasenya;
    }


    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Método toString para representación de texto del objeto
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", multa=" + multa +
                '}';
    }
}
