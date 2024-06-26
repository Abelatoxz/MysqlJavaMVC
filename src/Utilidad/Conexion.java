package Utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Mariadb Driver
    private static final String URL = "jdbc:mariadb://gitlab.abelflix.es:9000/Bibloteca";
    private static final String USUARIO = "bibloteca";
    private static final String CONTRASENA = "password";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
