package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JTable table;
    private JButton userButton, libroButton, prestecButton;
    private JLabel venbingudaLabel;

    public Menu(){
        setTitle("Menu - Can Casacoberta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();

        // Crear el JLabel de bienvenida
        venbingudaLabel = new JLabel("Benvingut a Can Casacoberta", JLabel.CENTER);
        venbingudaLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        venbingudaLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir un margen

        // Añadir el JLabel al norte del BorderLayout
        add(venbingudaLabel, BorderLayout.NORTH);

        userButton = new JButton("Usuaris");
        libroButton = new JButton("Llibres");
        prestecButton = new JButton("Prestecs");

        buttonPanel.add(userButton);
        buttonPanel.add(libroButton);
        buttonPanel.add(prestecButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addUserButtonListener(ActionListener listener) {
        userButton.addActionListener(listener);
    }

    public void addLibroButtonListener(ActionListener listener) {
        libroButton.addActionListener(listener);
    }

    public void addPrestecButtonListener(ActionListener listener) {
        prestecButton.addActionListener(listener);
    }
}