package Modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Utilidad.Conexion;
import java.time.LocalDate;

public class PrestamoModelo {
    public LocalDate getFechaActual(){
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }
    public List<Prestamo> obtenerPrestamo() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();

        String query = "SELECT * FROM Prestamos";
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
                        resultSet.getString("Estado")
                );
                prestamos.add(prestamo);
            }
        }

        return prestamos;
    }
    public LocalDate obtenerPrestamoFechaActual(int IDUsuario, int IDLibro) throws SQLException {
        String queryFechaRetorno = "SELECT Fecha_Retorno_Prevista FROM Prestamos WHERE ID_Usuario = ? AND ID_Libro = ? AND Fecha_Retorno_Real IS NULL;";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(queryFechaRetorno)) {

            // Configurar los parámetros del PreparedStatement
            statement.setInt(1, IDUsuario);
            statement.setInt(2, IDLibro);

            // Ejecutar la consulta y procesar el ResultSet dentro del mismo bloque try-with-resources
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Crear un objeto LocalDate a partir del valor de la columna Fecha_Retorno_Prevista
                    LocalDate fechaPrestamoPrevista = resultSet.getDate("Fecha_Retorno_Prevista").toLocalDate();
                    return fechaPrestamoPrevista;
                } else {
                    throw new SQLException("No se encontró un préstamo para las IDs dadas.");
                }
            }
        }
    }

    public void insertarPrestamoAlta(int[] IDs) throws SQLException {
        LocalDate fechaActual = LocalDate.now();
        //Primer 0 = IdUsuario , 1 = IdLibro
        LocalDate fechaEntrega = obtenerPrestamoFechaActual(IDs[0],IDs[1]);
        //After == Entregar tarde
        if (fechaEntrega.isAfter(fechaActual)){
            String queryUpdateEstadoLibro = "UPDATE Libros SET Estado = 'Disponible' where ID_Libro = ? ";

            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(queryUpdateEstadoLibro)){
                statement.setInt(1,IDs[1]);
                statement.executeUpdate();
            }
            String updatePrestamo = "UPDATE Prestamos set Fecha_Retorno_Real = ? where ID_Libro = ? and ID_Usuario = ?";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(updatePrestamo)){
                statement.setDate(1, Date.valueOf(fechaActual));
                statement.setInt(2,IDs[1]);
                statement.setInt(3,IDs[0]);
                statement.executeUpdate();
                conexion.close();
            }

            String updateUsuario = "update Usuarios set Multas = Multas + 1 where ID_Usuario = ?";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(updateUsuario)){
                statement.setInt(1, IDs[0]);
                statement.executeUpdate();
                conexion.close();
            }

        }else {
            String queryUpdateEstadoLibro = "UPDATE Libros SET Estado = 'Disponible' where ID_Libro = ? ";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(queryUpdateEstadoLibro)){
                statement.setInt(1,IDs[1]);
                statement.executeUpdate();
                conexion.close();
            }
            String updatePrestamo = "UPDATE Prestamos set Fecha_Retorno_Real = ? , Estado = 'Completado'     where ID_Libro = ? and ID_Usuario = ?";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(updatePrestamo)){
                statement.setDate(1, Date.valueOf(fechaActual));
                statement.setInt(2,IDs[1]);
                statement.setInt(3,IDs[0]);
                statement.executeUpdate();
                conexion.close();
            }

        }
    }

}
