package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utilidad.Conexion;

public class PrestamoUsuario {
    public List<Prestamo> obtenerPrestamo() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM Usuarios";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Prestamo prestamo= new Prestamo(
                        resultSet.getInt("ID_Prestamo"),
                        resultSet.getInt("ID_Libro"),
                        resultSet.getInt("ID_Usuario"),
                        resultSet.getDate("Fecha_Prestamo"),
                        resultSet.getDate("Fecha_Retorno_Prevista"),
                        resultSet.getDate("Fecha_Retorno_Real"),
                        resultSet.getString("Estado")
                );
                prestamos.add(prestamo);
            }
        }

        return prestamos;
    }
}
