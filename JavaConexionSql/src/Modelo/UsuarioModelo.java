package Modelo;

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
    public void insertarUsuario(Usuario usuario) throws SQLException {
        // Query para insertar un nuevo usuario
        String query = "INSERT INTO Usuarios (Nombre, Apellidos, Email, Telefono, Rol) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            // Establecer los valores del usuario en la consulta preparada
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());

            // Ejecutar la consulta para insertar el usuario
            statement.executeUpdate();
        }
    }


}