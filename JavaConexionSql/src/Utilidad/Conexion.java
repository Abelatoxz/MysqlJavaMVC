package Utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://192.168.1.136:3306/Bibloteca";
    private static final String USUARIO = "bibloteca";
    private static final String CONTRASEÑA = "password";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }
}


