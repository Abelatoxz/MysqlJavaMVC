package Vista;

import Modelo.Prestamo;
import excepciones.CamposVacios;

import java.util.Date;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PrestamoVistaAdminGUI extends JFrame {

    private JTable prestamosTable;
    private DefaultTableModel tableModel;
    private JTextField idLibroField, idUsuarioField;
    private JButton prestamoButton, mostrarButton, actualizarButton, volverButton;

    public PrestamoVistaAdminGUI() {
        setTitle("Gestión de Préstamos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear tabla de préstamos
        tableModel = new DefaultTableModel(new Object[]{"ID Préstamo", "ID Libro", "ID Usuario", "Fecha Préstamo", "Fecha Retorno Prevista", "Fecha_Retorno_Real","Estado"}, 0);
        prestamosTable = new JTable(tableModel);
        add(new JScrollPane(prestamosTable), BorderLayout.CENTER);

        // Panel de formulario para realizar un préstamo
        JPanel prestamoPanel = new JPanel(new GridLayout(3, 2));

        prestamoPanel.add(new JLabel("ID Libro:"));
        idLibroField = new JTextField();
        prestamoPanel.add(idLibroField);

        prestamoPanel.add(new JLabel("ID Usuario:"));
        idUsuarioField = new JTextField();
        prestamoPanel.add(idUsuarioField);

        // Botón para realizar un préstamo
        prestamoButton = new JButton("Realizar Préstamo");
        prestamoPanel.add(prestamoButton);

        add(prestamoPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        mostrarButton = new JButton("Mostrar Préstamos");
        volverButton = new JButton("Volver");
        buttonPanel.add(volverButton);
        buttonPanel.add(mostrarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        actualizarButton = new JButton("Dar de alta");
        buttonPanel.add(actualizarButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Método para añadir ActionListener al botón de préstamo
    public void addVolverButtonListener(ActionListener listener) {
        volverButton.addActionListener(listener);
    }
    public void addPrestamoButtonListener(ActionListener listener) {
        prestamoButton.addActionListener(listener);
    }
    public void addactualizarButtonListener(ActionListener listener) {
        actualizarButton.addActionListener(listener);
    }

    // Método para añadir ActionListener al botón de mostrar préstamos
    public void addMostrarButtonListener(ActionListener listener) {
        mostrarButton.addActionListener(listener);
    }

    // Método para obtener los IDs de libro y usuario para realizar el préstamo
    public int[] obtenerIDsParaPrestamo() throws CamposVacios, Exception {
        int[] ids = new int[2];

        try {
            String idLibroText = idLibroField.getText();
            String idUsuarioText = idUsuarioField.getText();

            if (idLibroText.isEmpty() || idUsuarioText.isEmpty()) {
                throw new CamposVacios("Los campos de ID no pueden estar vacíos.");
            }

            ids[0] = Integer.parseInt(idLibroText);
            ids[1] = Integer.parseInt(idUsuarioText);

            if (ids[1] == 0) {
                System.out.println(ids[1]); // Considera eliminar este `println` en producción
            }

            System.out.println(ids[0]); // Considera eliminar este `println` en producción
            System.out.println(ids[1]); // Considera eliminar este `println` en producción

        } catch (NumberFormatException e) {
            throw new Exception("Los IDs deben ser números enteros.", e);
        }

        return ids;
    }
    public Prestamo obtenerPrestamoDesdeTabla() {
        int filaSeleccionada = prestamosTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            // Si no se ha seleccionado ninguna fila, retornar null o lanzar una excepción según tu lógica de manejo de errores
            return null;
        }
        int idPrestamo = (int) tableModel.getValueAt(filaSeleccionada, 0);
        int idLibro = (int) tableModel.getValueAt(filaSeleccionada, 1);
        int idUsuario = (int) tableModel.getValueAt(filaSeleccionada, 2);
        String fechaPrestamoStr = (String) tableModel.getValueAt(filaSeleccionada, 3);
        String fechaPrestamoPrevistaStr = (String) tableModel.getValueAt(filaSeleccionada, 4);
        String fechaPrestamoReal = (String) tableModel.getValueAt(filaSeleccionada, 5);
        String estado = (String) tableModel.getValueAt(filaSeleccionada, 6);

        // Convertir las cadenas String en objetos Date
        Date fechaPrestamo = null;
        Date fechaPrestamoPrevista = null;
        try {
            fechaPrestamo = new SimpleDateFormat("yyyy-MM-dd").parse(fechaPrestamoStr);
            fechaPrestamoPrevista = new SimpleDateFormat("yyyy-MM-dd").parse(fechaPrestamoPrevistaStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Crear objeto Prestamo
        return new Prestamo(idPrestamo, idLibro, idUsuario, fechaPrestamo, fechaPrestamoPrevista, fechaPrestamoReal, estado);
    }

    // Método para mostrar los préstamos en la tabla
    public void mostrarPrestamos(List<Prestamo> prestamos) {
        tableModel.setRowCount(0); // Limpiar tabla
        for (Prestamo prestamo : prestamos) {
            tableModel.addRow(new Object[]{
                    prestamo.getIdPrestamo(),
                    prestamo.getIdLibro(),
                    prestamo.getIdUsuario(),
                    prestamo.getFechaPrestamo(),
                    prestamo.getFechaPrestamoPrevista(),
                    prestamo.getFechaPrestamoReal(),
                    prestamo.getEstado()
            });
        }
    }

    //Método para mostrar mensajes de éxito
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Método para mostrar mensajes de error
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}