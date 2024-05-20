package Vista;

import Modelo.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class UsuarioVistaGUI extends JFrame {
    private JTextArea areaTexto;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtRol;
    private JTextField txtFechaRegistro;

    public UsuarioVistaGUI() {
        super("Lista de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            areaTexto.setText("No hay usuarios para mostrar.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Lista de Usuarios:\n");
            for (Usuario usuario : usuarios) {
                sb.append(usuario.toString()).append("\n");
            }
            areaTexto.setText(sb.toString());
        }
    }

    public Usuario obtenerDatosNuevoUsuario() {
        JFrame frame = new JFrame("Agregar Usuario");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        frame.add(panel);

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtRol = new JTextField();
        JTextField txtFechaRegistro = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Telefono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Rol:"));
        panel.add(txtRol);
        panel.add(new JLabel("Fecha de registro (yyyy-MM-dd):"));
        panel.add(txtFechaRegistro);

        JButton btnAgregar = new JButton("Agregar Usuario");
        Usuario[] nuevoUsuario = new Usuario[1];

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String email = txtEmail.getText();
            String telefono = txtTelefono.getText();
            String rol = txtRol.getText();
            String fechaRegistroStr = txtFechaRegistro.getText();
            Date fechaRegistro = null;

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                fechaRegistro = dateFormat.parse(fechaRegistroStr);
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Formato de fecha incorrecto. Usando la fecha actual.", "Error", JOptionPane.ERROR_MESSAGE);
                fechaRegistro = new Date();
            }

            nuevoUsuario[0] = new Usuario(0, nombre, apellido, email, telefono, rol, fechaRegistro, 0);
            JOptionPane.showMessageDialog(frame, "Usuario agregado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        });
        panel.add(btnAgregar);

        frame.setVisible(true);

        return nuevoUsuario[0];
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}