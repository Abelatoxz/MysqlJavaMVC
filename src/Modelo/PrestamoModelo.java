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
                        resultSet.getString("Fecha_Retorno_Real"),
                        resultSet.getString("Estado")
                );
                prestamos.add(prestamo);
            }
        }

        return prestamos;
    }

    public List<Prestamo> obtenerPrestamoUsuario(int idUsuario) throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM Prestamos WHERE ID_Usuario = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setInt(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = new Prestamo(
                            resultSet.getInt("ID_Prestamo"),
                            resultSet.getInt("ID_Libro"),
                            resultSet.getInt("ID_Usuario"),
                            resultSet.getDate("Fecha_Prestamo"),
                            resultSet.getDate("Fecha_Retorno_Prevista"),
                            resultSet.getString("Fecha_Retorno_Real"),
                            resultSet.getString("Estado")
                    );
                    prestamos.add(prestamo);
                }
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
    public void insertarPrestamo(int[] IDs) throws SQLException {
        String queryLibro = "SELECT Estado FROM Libros WHERE ID_Libro = ?";
        String queryUsuario = "SELECT COUNT(*) FROM Usuarios WHERE ID_Usuario = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statementLibro = conexion.prepareStatement(queryLibro);
             PreparedStatement statementUsuario = conexion.prepareStatement(queryUsuario)) {

            // Verificar la existencia del usuario
            statementUsuario.setInt(1, IDs[1]);
            try (ResultSet resultSetUsuario = statementUsuario.executeQuery()) {
                if (resultSetUsuario.next()) {
                    int count = resultSetUsuario.getInt(1);
                    if (count == 0) {
                        throw new SQLException("El usuario no existe en la base de datos.");
                    }
                }
            }

            // Establecer el parámetro ID_Libro en la consulta
            statementLibro.setInt(1, IDs[0]);

            try (ResultSet resultSet = statementLibro.executeQuery()) {
                if (resultSet.next()) {
                    String estadoLibro = resultSet.getString("Estado");

                    // Verificar el estado del libro antes de insertar el préstamo
                    if ("Disponible".equals(estadoLibro)) {
                        String queryInsertar = "INSERT INTO Prestamos (ID_Libro, ID_Usuario, Estado) VALUES (?, ?, 'Activo')";
                        try (PreparedStatement statementInsertar = conexion.prepareStatement(queryInsertar)) {
                            statementInsertar.setInt(1, IDs[0]);
                            statementInsertar.setInt(2, IDs[1]);
                            statementInsertar.executeUpdate();

                            // Actualizar el estado del libro a 'Prestado'
                            String queryActualizar = "UPDATE Libros SET Estado = 'Prestado' WHERE ID_Libro = ?";
                            try (PreparedStatement statementActualizar = conexion.prepareStatement(queryActualizar)) {
                                statementActualizar.setInt(1, IDs[0]);
                                statementActualizar.executeUpdate();
                            }
                        }
                    } else {
                        // El libro no está disponible para préstamo, lanzar una excepción SQLException
                        throw new SQLException("El libro no está disponible para préstamo.");
                    }
                } else {
                    // No se encontró el libro con el ID especificado, lanzar una excepción SQLException
                    throw new SQLException("El libro no existe en la base de datos.");
                }
            }
        }
    }


    public void insertarPrestamoAlta(Prestamo prestamo) throws SQLException {
        LocalDate fechaActual = LocalDate.now();
        //Primer 0 = IdUsuario , 1 = IdLibro
        System.out.println(fechaActual);
        LocalDate fechaEntrega = obtenerPrestamoFechaActual(prestamo.getIdUsuario(), prestamo.getIdLibro());
        //After == Entregar tarde
        System.out.println(fechaEntrega);
        if (fechaActual.isAfter(fechaEntrega)){
            String queryUpdateEstadoLibro = "UPDATE Libros SET Estado = 'Disponible' where ID_Libro = ? ";

            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(queryUpdateEstadoLibro)){
                statement.setInt(1,prestamo.getIdLibro());
                statement.executeUpdate();
            }
            String updatePrestamo = "UPDATE Prestamos set Fecha_Retorno_Real = ?, Estado = 'Retrasado'  where ID_Prestamo = ? ";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(updatePrestamo)){
                statement.setDate(1, Date.valueOf(fechaActual));
                statement.setInt(2,prestamo.getIdPrestamo());

                statement.executeUpdate();
                conexion.close();
            }

            String updateUsuario = "update Usuarios set Multas = Multas + 1 where ID_Usuario = ?";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(updateUsuario)){
                statement.setInt(1, prestamo.getIdUsuario());
                statement.executeUpdate();
                conexion.close();
            }

        }else {
            String queryUpdateEstadoLibro = "UPDATE Libros SET Estado = 'Disponible' where ID_Libro = ? ";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(queryUpdateEstadoLibro)){
                statement.setInt(1,prestamo.getIdLibro());
                statement.executeUpdate();
                conexion.close();
            }
            String updatePrestamo = "UPDATE Prestamos set Fecha_Retorno_Real = ? , Estado = 'Completado'    where ID_Prestamo = ?";
            try(Connection conexion = Conexion.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(updatePrestamo)){
                statement.setDate(1, Date.valueOf(fechaActual));
                statement.setInt(2,prestamo.getIdPrestamo());

                statement.executeUpdate();
                conexion.close();
            }

        }
    }

}