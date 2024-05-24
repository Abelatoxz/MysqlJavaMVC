package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utilidad.Conexion;



public class LibroModelo {
    public List<Libro> obtenerLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM Libros";
        try (Connection conexion = Conexion.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                Libro libro = new Libro(
                        resultSet.getInt("ID_Libro"),
                        resultSet.getString("Titulo"),
                        resultSet.getString("Autor"),
                        resultSet.getString("ISBN"),
                        resultSet.getString("Editorial"),
                        resultSet.getDate("Anio_Publicacion"),
                        resultSet.getString("Categoria"),
                        resultSet.getString("Estado")
                );
                libros.add((libro));
            }

        }
        return libros;
    }
    public void insertarLibro(Libro libro) throws SQLException{
        //Query para insertar libro
        String query = "INSERT INTO Libros (Titulo, Autor, ISBN, Editorial, Anio_Publicacion, Categoria, Estado) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conexion = Conexion.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(query)){
            //Establecer los valores del libro en la consulta preparada
            statement.setString(1, libro.getTitol());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getISBN());
            statement.setString(4, libro.getEditorial());
            statement.setString(5, libro.getFechaPublicacion());
            statement.setString(6, libro.getCategoria());
            statement.setString(7, libro.getEstado());

            // Ejecutar la consulta al libro
            statement.executeUpdate();
        }

    }
    public void editarLibro(Libro libro) throws SQLException{


        //Query para Editar libro
        String query = "UPDATE Libros SET Titulo = ?, Autor = ?, ISBN = ?, Editorial = ?, Anio_Publicacion = ?, Categoria = ?, Estado = ? WHERE  ID_Libro = ?; ";

        try(Connection conexion = Conexion.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(query)){
            //Establecer los valores del libro en la consulta preparada
            statement.setString(1, libro.getTitol());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getISBN());
            statement.setString(4, libro.getEditorial());
            statement.setString(5, libro.getFechaPublicacion());
            statement.setString(6, libro.getCategoria());
            statement.setString(7, libro.getEstado());
            statement.setInt(8,libro.getId());

            // Ejecutar la consulta al libro
            statement.executeUpdate();
        }
    }
    public void cambiarEstadoDisponible(int id) throws SQLException {
        String query = "UPDATE Libros SET Estado = 'Disponible' where ID_Libro = ?";
        try(Connection conexion = Conexion.obtenerConexion();
        PreparedStatement statement = conexion.prepareStatement(query)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
    }

    //Borrar libros, funcion acabada por parte del modelo queda la vista y el contrlador y probarlo
    public void borrarLibro(int[] ID) throws SQLException {
        try (Connection conexion = Conexion.obtenerConexion()) {

            // Eliminar las entradas relacionadas en Prestamos
            String queryEliminarPrestamos = "DELETE FROM Prestamos WHERE ID_Libro IN (" + generarPlaceholders(ID.length) + ")";
            try (PreparedStatement statementPrestamos = conexion.prepareStatement(queryEliminarPrestamos)) {
                for (int i = 0; i < ID.length; i++) {
                    statementPrestamos.setInt(i + 1, ID[i]);
                }
                statementPrestamos.executeUpdate();
            }

            // Eliminar los libros de la tabla Libros
            String queryEliminarLibros = "DELETE FROM Libros WHERE ID_Libro IN (" + generarPlaceholders(ID.length) + ")";
            try (PreparedStatement statementLibros = conexion.prepareStatement(queryEliminarLibros)) {
                for (int i = 0; i < ID.length; i++) {
                    statementLibros.setInt(i + 1, ID[i]);
                }
                statementLibros.executeUpdate();
            }
        }
    }

    //Generar ? para peticiones sql
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
