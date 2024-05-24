package Modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utilidad.Conexion;

public class UsuarioModelo {
    public List<Usuario> obtenerUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuarios";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("ID_Usuario"),
                        resultSet.getString("Nombre"),
                        resultSet.getString("Apellidos"),
                        resultSet.getString("Email"),
                        resultSet.getString("Telefono"),
                        resultSet.getString("Rol"),
                        resultSet.getDate("Fecha_Registro"),
                        resultSet.getInt("Multas")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario obtenerUsuario(int id) throws SQLException {
        String query = "SELECT * FROM Usuarios WHERE ID_Usuario = ?";
        Usuario usuario = null;
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    usuario = new Usuario(
                            resultSet.getInt("ID_Usuario"),
                            resultSet.getString("Nombre"),
                            resultSet.getString("Apellidos"),
                            resultSet.getString("Email"),
                            resultSet.getString("Telefono"),
                            resultSet.getString("Rol"),
                            resultSet.getDate("Fecha_Registro"),
                            resultSet.getInt("Multas")
                    );
                }
            }
        }
        return usuario;
    }

    public void insertarUsuario(Usuario usuario) throws SQLException, NoSuchAlgorithmException {
        // Encriptar la contraseña
        String contraseñaEncriptada = encriptarContraseña(usuario.getContrasenya());

        String query = "INSERT INTO Usuarios (Nombre, Apellidos, Email, Telefono, Rol, Contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());
            statement.setString(6, contraseñaEncriptada); // Guardar la contraseña encriptada
            statement.executeUpdate();
        }
    }

    private String encriptarContraseña(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(contraseña.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void editarUsuario(Usuario usuario) throws SQLException, NoSuchAlgorithmException {
        // Encriptar la nueva contraseña si se proporciona
        String contraseñaEncriptada = null;
        if (usuario.getContrasenya() != null && !usuario.getContrasenya().isEmpty()) {
            contraseñaEncriptada = encriptarContraseña(usuario.getContrasenya());
        }

        String query = "UPDATE Usuarios SET Nombre = ?, Apellidos = ?, Email = ?, Telefono = ?, Rol = ?";
        // Agregar la actualización de la contraseña solo si se proporciona una nueva contraseña
        if (contraseñaEncriptada != null) {
            query += ", Contraseña = ?";
        }
        query += " WHERE ID_Usuario = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());
            // Establecer la nueva contraseña solo si se proporciona
            int parametro = 6;
            if (contraseñaEncriptada != null) {
                statement.setString(parametro++, contraseñaEncriptada);
            }
            statement.setInt(parametro, usuario.getId());
            statement.executeUpdate();
        }
    }

    public void editarUsuarioUnico(Usuario usuario) throws SQLException, NoSuchAlgorithmException {
        // Encriptar la nueva contraseña si se proporciona
        String contraseñaEncriptada = null;
        if (usuario.getContrasenya() != null && !usuario.getContrasenya().isEmpty()) {
            contraseñaEncriptada = encriptarContraseña(usuario.getContrasenya());
        }

        String query = "UPDATE Usuarios SET Nombre = ?, Apellidos = ?, Email = ?, Telefono = ?, Rol = ? WHERE ID_Usuario = ?";
        // Agregar la actualización de la contraseña solo si se proporciona una nueva contraseña
        if (contraseñaEncriptada != null) {
            query += ", Contraseña = ?";
        }
        query += " WHERE ID_Usuario = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());
            // Establecer la nueva contraseña solo si se proporciona
            int parametro = 6;
            if (contraseñaEncriptada != null) {
                statement.setString(parametro++, contraseñaEncriptada);
            }
            statement.setInt(parametro, usuario.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarUsuario(int[] ID) throws SQLException {
        String query = "DELETE FROM Usuarios WHERE ID_Usuario IN (" + generarPlaceholders(ID.length) + ")";

        try (Connection conexion = Conexion.obtenerConexion();

             PreparedStatement statementReservas = conexion.prepareStatement(query)) {
            for (int i = 0; i < ID.length; i++) {
                statementReservas.setInt(i + 1, ID[i]);
            }
            statementReservas.executeUpdate();
        }
    }
    private String generarPlaceholders(int cantidad) {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            placeholders.append("?");
            if (i != cantidad - 1) {
                placeholders.append(",");
            }
        }
        return placeholders.toString();
    }
}
