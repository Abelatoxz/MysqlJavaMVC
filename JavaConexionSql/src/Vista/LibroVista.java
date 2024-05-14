package Vista;

import Modelo.Libro;
import Modelo.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFrame;



public class LibroVista {
    private final Scanner scanner;
    public LibroVista() { scanner = new Scanner(System.in); }

    public void mostrarLibros(List<Libro> libros){
        if (libros.isEmpty()){
            System.out.println("No hay libros para mostrar.");
        }else {
            System.out.println("Lista de Libros: ");
            for (Libro libro : libros){
                System.out.println(libro);
            }
        }
    }
    public Libro obtenerDatosNuevoLibro(){
        System.out.println("Mete los datos del libro");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Editorial: ");
        String editorial = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Fecha de publicacion (yyyy): ");
        String fechaPublicacionStr = scanner.nextLine();
        Date fechaPublicacion = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            fechaPublicacion = dateFormat.parse(fechaPublicacionStr);
        }catch (ParseException e){
            System.out.println("Error " + e.getMessage());
            fechaPublicacion = new Date();
        }
        return new Libro(0,titulo,autor,ISBN,editorial,fechaPublicacion,categoria,estado);
    }




    public Libro obtenerDatosEditarLibro(){
        System.out.println("Mete los datos del libro");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Editorial: ");
        String editorial = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Fecha de publicacion (yyyy): ");
        String fechaPublicacionStr = scanner.nextLine();
        Date fechaPublicacion = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            fechaPublicacion = dateFormat.parse(fechaPublicacionStr);
        }catch (ParseException e){
            System.out.println("Error " + e.getMessage());
            fechaPublicacion = new Date();
        }
        return new Libro(id,titulo,autor,ISBN,editorial,fechaPublicacion,categoria,estado);
    }
    public int[] obtenerIDsParaBorrar() {
        System.out.println("Ingrese los IDs de los libros que desea borrar (separados por espacios):");
        String input = scanner.nextLine();
        String[] idStrings = input.split("\\s+");
        List<Integer> ids = new ArrayList<>();

        for (String idString : idStrings) {
            try {
                int id = Integer.parseInt(idString);
                ids.add(id);
            } catch (NumberFormatException e) {
                System.out.println("El valor '" + idString + "' no es un ID válido. Se ignorará.");
            }
        }

        // Convertir la lista de Integer a un array de int
        int[] idsArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idsArray[i] = ids.get(i);
        }

        return idsArray;
    }


    public int[] obtenerIdEstadoPrestado() {
        System.out.print("Ingrese la ID del libro que buscas cambiar a prestado: ");
        int idLibro = scanner.nextInt();
        System.out.print("Ingrese la ID del Usuario: ");
        int idUsuario = scanner.nextInt();
        return new int[]{idLibro,idUsuario};
    }

/*
  public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, "Error: " + mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
**/

}