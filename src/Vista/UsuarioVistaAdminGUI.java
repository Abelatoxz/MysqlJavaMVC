package Vista;

import Modelo.Libro;
import Modelo.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import excepciones.*;

public class UsuarioVistaAdminGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nombreField, apellidoField, emailField, telefonoField ;
    private JComboBox<String> rolField;
    public JButton agregarButton, editarButton, eliminarButton, mostrarButton, volverButton;
    private JPasswordField contraseñaField;

    public UsuarioVistaAdminGUI() {
        setTitle("Gestión de Usuarios");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear tabla
        tableModel = new DefaultTableModel(new Object[]{"Numero de Usuario", "Nombre", "Apellido", "Email", "Teléfono", "Rol", "Fecha de Creacion", "Mula"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(7, 3));
        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        formPanel.add(telefonoField);

        formPanel.add(new JLabel("Contraseña:"));
        contraseñaField = new JPasswordField();
        formPanel.add(contraseñaField);

        formPanel.add(new JLabel("Rol:"));
        String[] opciones = {"Usuario", "Bibliotecario"};
        rolField = new JComboBox<>(opciones);
        formPanel.add(rolField);

        add(formPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");
        volverButton = new JButton("Volver");

        buttonPanel.add(volverButton);
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    // Métodos para añadir ActionListener a los botones
    public void addAgregarButtonListener(ActionListener listener) {
        agregarButton.addActionListener(listener);
    }

    public void addEditarButtonListener(ActionListener listener) {
        editarButton.addActionListener(listener);
    }

    public void addEliminarButtonListener(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }

    public void addVolverButtonListener(ActionListener listener) {
        volverButton.addActionListener(listener);
    }



    public Usuario obtenerDatosUsuario() throws CamposVacios, Exception {
        JTextField[] camposTexto = {nombreField, apellidoField, emailField, telefonoField, contraseñaField};

        // Verificar si algún campo de texto está vacío
        for (JTextField campo : camposTexto) {
            if (campo.getText().isEmpty()) {
                throw new CamposVacios("Todos los campos deben estar completos.");
            }
        }

        String telefonoText = telefonoField.getText();
        String rol = (String) rolField.getSelectedItem();
        String contrasenya = contraseñaField.getText();

        try {
            if (idField.getText().isEmpty()) {
                return new Usuario(
                        nombreField.getText(),
                        apellidoField.getText(),
                        emailField.getText(),
                        telefonoText,
                        rol,
                        new Date(), // Set the current date
                        0, // Default value for 'multa'
                        contrasenya
                );
            } else {
                return new Usuario(
                        Integer.parseInt(idField.getText()),
                        nombreField.getText(),
                        apellidoField.getText(),
                        emailField.getText(),
                        telefonoText,
                        rol,
                        new Date(), // Set the current date
                        0, // Default value for 'multa'
                        contrasenya
                );
            }
        } catch (NumberFormatException e) {
            throw new Exception("Error al convertir el ID. Asegúrate de que el ID sea un número entero.");
        }
    }
    public Usuario obtenerDatosUsuarioEditar() throws CamposVacios {
        String rol = (String) rolField.getSelectedItem();
        JTextField[] camposTexto = {nombreField, apellidoField, emailField, telefonoField};

        // Verificar si algún campo de texto está vacío
        for (JTextField campo : camposTexto) {
            if (campo.getText().isEmpty()) {
                throw new CamposVacios("Todos los campos deben estar menos la contraseña eso es optativo.");
            }
        }
        int id = obtenerIdUsuario(); // Obtener el ID del usuario desde el campo correspondiente

        return new Usuario(
                id,
                nombreField.getText(),
                apellidoField.getText(),
                emailField.getText(),
                telefonoField.getText(),
                rol,
                new Date(),
                0   , // No se actualiza el número de multas
                contraseñaField.getText()
        );
    }
    public Usuario obtenerUsuariosDesdeTabla() throws CamposVacios {
        // Asegurarse de que los cambios de edición se han aplicado
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada == -1) {
            // Si no se ha seleccionado ninguna fila, lanzar una excepción
            throw new CamposVacios("Seleccione un usuario.");
        }

        try {
            int id = (Integer) tableModel.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tableModel.getValueAt(filaSeleccionada, 1);
            String apellido = (String) tableModel.getValueAt(filaSeleccionada, 2);
            String email = (String) tableModel.getValueAt(filaSeleccionada, 3);
            String telefono = (String) tableModel.getValueAt(filaSeleccionada, 4);
            String rol = (String) tableModel.getValueAt(filaSeleccionada, 5);
            String fechaRegistroStr = (String) tableModel.getValueAt(filaSeleccionada, 6);
            Date fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").parse(fechaRegistroStr);
            int multa = (Integer) tableModel.getValueAt(filaSeleccionada, 7);
            System.out.println(nombre);
            return new Usuario(id, nombre, apellido, email, telefono, rol, fechaRegistro, multa);
        } catch (Exception e) {
            throw new CamposVacios("Error al obtener datos del usuario: " + e.getMessage());
        }
    }
    public int obtenerIdUsuario() {
        return Integer.parseInt(idField.getText());
    }

    public void mostrarUsuarios(List<Usuario> usuarios) {
        tableModel.setRowCount(0); // Limpiar tabla
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getRol(),
                    usuario.getFechaRegistro(),
                    usuario.getMulta()
            });
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    public int[] obtenerIDsParaBorrar() throws CamposVacios {
        if (table.getSelectedRow() == -1){
            throw new CamposVacios("Seleccione un campo");

        }
        int[] ids = new int[table.getSelectedRowCount()];

        for (int i = 0; i < table.getSelectedRowCount(); i++) {
            ids[i] = (int) tableModel.getValueAt(table.getSelectedRows()[i], 0);
        }

        return ids;


    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}