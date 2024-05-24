package Vista;

import Modelo.Libro;
import Modelo.Prestamo;
import excepciones.CamposVacios;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class LibroVistaAdminGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField  tituloField, autorField, isbnField, editorialField, fechaField, categoriaField;
    private JButton agregarButton, editarButton, eliminarButton, volverButton;
    private  JComboBox<String>  estadoField;
    public LibroVistaAdminGUI() {
        setTitle("Gestión de Libros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear tabla
        tableModel = new DefaultTableModel(new Object[]{"Numero Libro", "Título", "Autor", "ISBN", "Editorial", "Fecha Publicación", "Categoría", "Estado"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(7, 2));


        formPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        formPanel.add(tituloField);

        formPanel.add(new JLabel("Autor:"));
        autorField = new JTextField();
        formPanel.add(autorField);

        formPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        formPanel.add(isbnField);

        formPanel.add(new JLabel("Editorial:"));
        editorialField = new JTextField();
        formPanel.add(editorialField);

        formPanel.add(new JLabel("Fecha Publicación:"));
        fechaField = new JTextField();
        formPanel.add(fechaField);

        formPanel.add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        formPanel.add(categoriaField);
        String[] opciones = {"Disponible", "Prestado"};

        formPanel.add(new JLabel("Estado:"));
        estadoField = new JComboBox<>(opciones);

        formPanel.add(estadoField);

        add(formPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        volverButton = new JButton("Volver");
        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");

        buttonPanel.add(volverButton);
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);

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


    public Libro obtenerDatosNuevoLibro() throws CamposVacios, Exception {
        // Crear una lista de todos los campos de texto excepto idField (ya que su vacío tiene un significado especial)
        JTextField[] camposTexto = {tituloField, autorField, isbnField, editorialField, fechaField, categoriaField};

        // Verificar si algún campo de texto está vacío
        for (JTextField campo : camposTexto) {
            if (campo.getText().isEmpty()) {
                throw new CamposVacios("Todos los campos deben estar completos.");
            }
        }

        String yearText = fechaField.getText();
        String estat = (String) estadoField.getSelectedItem();

        try {
            // Obtener la fecha del año ingresado
            Date fecha = new SimpleDateFormat("yyyy").parse(yearText);

            // Verificar si el campo ID está vacío
            if (tituloField.getText().isEmpty()) {
                return new Libro(
                        tituloField.getText(),
                        autorField.getText(),
                        isbnField.getText(),
                        editorialField.getText(),
                        fecha, // Asignar la fecha obtenida
                        categoriaField.getText(),
                        estat
                );
            } else {
                return new Libro(
                        Integer.parseInt(tituloField.getText()),
                        tituloField.getText(),
                        autorField.getText(),
                        isbnField.getText(),
                        editorialField.getText(),
                        fecha, // Asignar la fecha obtenida
                        categoriaField.getText(),
                        estat
                );
            }
        } catch (ParseException e) {
            throw new Exception("Error al parsear la fecha. Asegúrate de que esté en el formato 'yyyy'.");
        } catch (NumberFormatException e) {
            throw new Exception("Error al convertir el ID. Asegúrate de que el ID sea un número entero.");
        }
    }

    public Libro obtenerLibroDesdeTabla() throws CamposVacios {
        // Asegurarse de que los cambios de edición se han aplicado
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada == -1) {
            // Si no se ha seleccionado ninguna fila, lanzar una excepción
            throw new CamposVacios("Seleccione un libro.");
        }

        try {
            // Obtiene el ID directamente como entero
            int id = (Integer) tableModel.getValueAt(filaSeleccionada, 0);
            String titol = (String) tableModel.getValueAt(filaSeleccionada, 1);
            String autor = (String) tableModel.getValueAt(filaSeleccionada, 2);
            String ISBN = (String) tableModel.getValueAt(filaSeleccionada, 3);
            String editorial = (String) tableModel.getValueAt(filaSeleccionada, 4);
            String fechaPublicacionStr = (String) tableModel.getValueAt(filaSeleccionada, 5);
            String categoria = (String) tableModel.getValueAt(filaSeleccionada, 6);
            String estado = (String) tableModel.getValueAt(filaSeleccionada, 7);

            // Convertir la cadena de fecha en un objeto Date
            Date fechaPublicacion = new SimpleDateFormat("yyyy").parse(fechaPublicacionStr);

            return new Libro(id, titol, autor, ISBN, editorial, fechaPublicacion, categoria, estado);
        } catch (ParseException e) {
            throw new CamposVacios("Error de la fecha. Asegúrate de que esté en el formato 'yyyy'.");
        } catch (NumberFormatException e) {
            throw new CamposVacios("Error del ID. Asegúrate de que el ID sea un número entero.");
        } catch (ClassCastException e) {
            throw new CamposVacios("Error en el tipo de datos. Revisa los valores en la tabla.");
        }
    }



    public int[] obtenerIDsParaBorrar() throws CamposVacios {
        int[] ids = new int[table.getSelectedRowCount()];

        for (int i = 0; i < table.getSelectedRowCount(); i++) {
            ids[i] = (int) tableModel.getValueAt(table.getSelectedRows()[i], 0);

        }
        if (ids.length == 0){
            throw new CamposVacios("Para borrar un libro, tienes que seleccionarlo");
        }
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