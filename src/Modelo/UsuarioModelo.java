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
        String query = "SELECT * FROM Usuaris";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("ID_Usuari"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Cognoms"),
                        resultSet.getString("Email"),
                        resultSet.getString("Telefon"),
                        resultSet.getString("Rol"),
                        resultSet.getDate("Data_Registre")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }


    // Otros métodos para operaciones CRUD, por ejemplo: insertarUsuario(), actualizarUsuario(), eliminarUsuario(), etc.
}