package Vista;

import Modelo.Prestamo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrestamoVistaGUI extends JFrame {
    private JTextArea areaTexto;
    
    public PrestamoVistaGUI() {
        super("Lista de Préstamos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void mostrarPrestamos(List<Prestamo> prestamos) {
        if (prestamos.isEmpty()) {
            areaTexto.setText("No hay préstamos para mostrar.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Lista de Préstamos:\n");
            for (Prestamo prestamo : prestamos) {
                sb.append(prestamo.toString()).append("\n");
            }
            areaTexto.setText(sb.toString());
        }
    }

    public int[] obtenerIDsUsuarioLibro() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtIdLibro = new JTextField();
        JTextField txtIdUsuario = new JTextField();

        panel.add(new JLabel("ID Libro:"));
        panel.add(txtIdLibro);
        panel.add(new JLabel("ID Usuario:"));
        panel.add(txtIdUsuario);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese IDs del Libro y Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int idLibro = Integer.parseInt(txtIdLibro.getText());
                int idUsuario = Integer.parseInt(txtIdUsuario.getText());
                return new int[]{idUsuario, idLibro};
            } catch (NumberFormatException e) {
                mostrarMensajeError("Por favor, ingrese números válidos para los IDs.");
            }
        }
        return null;
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
}