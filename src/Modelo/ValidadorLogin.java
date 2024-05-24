package Modelo;

import Utilidad.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.*;

public class ValidadorLogin {
    private String username;
    private String password;

    public ValidadorLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validar() throws SQLException {
        String query = "SELECT COUNT(*) FROM Usuarios WHERE Email = ? AND Contraseña = ?";
        int count = 0;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            String hashedPassword = hashPassword(password);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        }

        return count == 1;
    }

    public int obtenerIdUsuario() throws SQLException {
        String query = "SELECT ID_Usuario FROM Usuarios WHERE Email = ? AND Contraseña = ?";
        int idUsuario = -1;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            String hashedPassword = hashPassword(password);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idUsuario = resultSet.getInt("ID_Usuario");
                }
            }
        }

        return idUsuario;
    }

    public static boolean esAdmin(int id) throws SQLException {
        String rol = obtenerRol(id);
        return rol != null && rol.equals("Bibliotecario");
    }

    public static String obtenerRol(int id) throws SQLException {
        String query = "SELECT Rol FROM Usuarios WHERE ID_Usuario = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Rol");
                }
            }
        }
        return null;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Fallo la encriptacion", e);
        }
    }
}
