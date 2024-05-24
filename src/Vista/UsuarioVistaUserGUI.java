package Vista;

import Modelo.Usuario;
import excepciones.CamposVacios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class UsuarioVistaUserGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nombreField, apellidoField, emailField, telefonoField;
    private JPasswordField contraseñaField;
    public JButton editarButton, mostrarButton, volverButton;

    public UsuarioVistaUserGUI() {
        setTitle("Gestión de Usuarios");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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

        add(formPanel, BorderLayout.NORTH);

        // Crear tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Email", "Teléfono","Fecha de Creacion"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        volverButton = new JButton("Volver");
        mostrarButton = new JButton("Mostrar");
        editarButton = new JButton("Editar");

        buttonPanel.add(volverButton);
        buttonPanel.add(mostrarButton);
        buttonPanel.add(editarButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Métodos para añadir ActionListener a los botones
    public void addVolverButtonListener(ActionListener listener) {
        volverButton.addActionListener(listener);
    }

    public void addEditarButtonListener(ActionListener listener) {
        editarButton.addActionListener(listener);
    }

    public void addMostrarButtonListener(ActionListener listener) {
        mostrarButton.addActionListener(listener);
    }

    public Usuario obtenerDatosUsuarioEditar() throws CamposVacios {
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
                "Usuario",
                new Date(),
                0   , // No se actualiza el número de multas
                contraseñaField.getText()
        );
    }

    public int obtenerIdUsuario() {
        return Integer.parseInt(idField.getText());
    }

    public void mostrarUsuario(Usuario usuario) {
        tableModel.setRowCount(0); // Limpiar tabla
            tableModel.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getFechaRegistro()
            });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    public int[] obtenerIDsParaBorrar() {
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
