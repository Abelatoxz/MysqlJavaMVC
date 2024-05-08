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

}
