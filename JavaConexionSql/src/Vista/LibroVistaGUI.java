package Vista;

import Modelo.Libro;
import Modelo.LibroModelo;
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


public class LibroVistaGUI extends JFrame {
    private JTextArea areaTexto;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtISBN;
    private JTextField txtEditorial;
    private JTextField txtCategoria;
    private JTextField txtEstado;
    private JTextField txtFechaPublicacion;
    public LibroVistaGUI() {
        super("Lista de Libros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            areaTexto.setText("No hay libros para mostrar.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Lista de Libros:\n");
            for (Libro libro : libros) {
                sb.append(libro.toString()).append("\n");
            }
            areaTexto.setText(sb.toString());
        }
    }
    public Libro LibroAgregarGUI() {
        JFrame frame = new JFrame("Agregar Libro");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panel = new JPanel(new GridLayout(7, 2));
        frame.add(panel);
    
        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtISBN = new JTextField();
        JTextField txtEditorial = new JTextField();
        JTextField txtCategoria = new JTextField();
        JTextField txtEstado = new JTextField();
        JTextField txtFechaPublicacion = new JTextField();
    
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Autor:"));
        panel.add(txtAutor);
        panel.add(new JLabel("ISBN:"));
        panel.add(txtISBN);
        panel.add(new JLabel("Editorial:"));
        panel.add(txtEditorial);
        panel.add(new JLabel("Categoría:"));
        panel.add(txtCategoria);
        panel.add(new JLabel("Estado:"));
        panel.add(txtEstado);
        panel.add(new JLabel("Fecha de publicación (yyyy):"));
        panel.add(txtFechaPublicacion);
    
        JButton btnAgregar = new JButton("Agregar Libro");
        Libro[] nuevoLibro = new Libro[1]; // Variable para almacenar el libro creado
        btnAgregar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String ISBN = txtISBN.getText();
            String editorial = txtEditorial.getText();
            String categoria = txtCategoria.getText();
            String estado = txtEstado.getText();
            String fechaPublicacionStr = txtFechaPublicacion.getText();
            Date fechaPublicacion = null;
    
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                fechaPublicacion = dateFormat.parse(fechaPublicacionStr);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
    
            nuevoLibro[0] = new Libro(0, titulo, autor, ISBN, editorial, fechaPublicacion, categoria, estado);
            // Ahora, podrías usar este libro para cualquier propósito que necesites
        });
        panel.add(btnAgregar);
    
        frame.setVisible(true);
    
        // Devolver el libro creado
        return nuevoLibro[0];
    }

}

