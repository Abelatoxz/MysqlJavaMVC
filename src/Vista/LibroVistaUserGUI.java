package Vista;

import Modelo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LibroVistaUserGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, tituloField, autorField, isbnField, editorialField, fechaField, categoriaField, estadoField;
    private JButton agregarButton, editarButton, eliminarButton, mostrarButton, volverButton;

    public LibroVistaUserGUI() {
        setTitle("Gestión de Libros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Título", "Autor", "ISBN", "Editorial", "Fecha Publicación", "Categoría", "Estado"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        volverButton = new JButton("Volver");
        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");
        mostrarButton = new JButton("Refrescar");

        buttonPanel.add(volverButton);
        buttonPanel.add(mostrarButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Métodos para añadir ActionListener a los botones
    public void addVolverButtonListener(ActionListener listener) {
        volverButton.addActionListener(listener);
    }

    public void addAgregarButtonListener(ActionListener listener) {
        agregarButton.addActionListener(listener);
    }

    public void addEditarButtonListener(ActionListener listener) {
        editarButton.addActionListener(listener);
    }

    public void addEliminarButtonListener(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }

    public void addMostrarButtonListener(ActionListener listener) {
        mostrarButton.addActionListener(listener);
    }

    public Libro obtenerDatosNuevoLibro() {
        String yearText = fechaField.getText();
        // Verificar si el campo de fecha no está vacío
        if (!yearText.isEmpty()) {
            try {
                // Obtener la fecha del año ingresado
                Date fecha = new SimpleDateFormat("yyyy").parse(yearText);
                if (idField.getText().isEmpty()) {
                            return new Libro(
                            tituloField.getText(),
                            autorField.getText(),
                            isbnField.getText(),
                            editorialField.getText(),
                            fecha, // Asignar la fecha obtenida
                            categoriaField.getText(),
                            estadoField.getText()
                    );
                }
                return new Libro(
                        Integer.parseInt(idField.getText()),
                        tituloField.getText(),
                        autorField.getText(),
                        isbnField.getText(),
                        editorialField.getText(),
                        fecha, // Asignar la fecha obtenida
                        categoriaField.getText(),
                        estadoField.getText()
                );
            } catch (ParseException | NumberFormatException e) {
                // Handle parsing error
                e.printStackTrace();
                return null; // Or handle it according to your application's logic
            }
        } else {
            // Handle empty date field
            return null; // Or handle it according to your application's logic
        }
    }

    public Libro obtenerDatosEditarLibro() {
        return obtenerDatosNuevoLibro();
    }

    public int[] obtenerIDsParaBorrar() {
        int[] ids = new int[table.getSelectedRowCount()];
        for (int i = 0; i < table.getSelectedRowCount(); i++) {
            ids[i] = (int) tableModel.getValueAt(table.getSelectedRows()[i], 0);
        }
        return ids;
    }

    public int[] obtenerIdEstadoPrestado() {
        int[] ids = new int[2];
        ids[0] = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese ID del libro:"));
        ids[1] = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese ID del usuario:"));
        return ids;
    }

    public void mostrarLibros(List<Libro> libros) {
        tableModel.setRowCount(0); // Limpiar tabla
        for (Libro libro : libros) {
            tableModel.addRow(new Object[]{
                    libro.getId(),
                    libro.getTitol(),
                    libro.getAutor(),
                    libro.getISBN(),
                    libro.getEditorial(),
                    libro.getFechaPublicacion(),
                    libro.getCategoria(),
                    libro.getEstado()
            });
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
