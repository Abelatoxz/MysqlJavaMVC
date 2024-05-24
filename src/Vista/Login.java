package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField usuariField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        setTitle("Iniciar Sessió - Can Casacoberta");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JLabel usernameLabel = new JLabel("Usuari:");
        usuariField = new JTextField();

        JLabel passwordLabel = new JLabel("Contrasenya:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Iniciar Sessió");


        panel.add(usernameLabel);
        panel.add(usuariField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);

        setVisible(true);
    }

    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public JTextField getUsuariField() {
        return usuariField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
}