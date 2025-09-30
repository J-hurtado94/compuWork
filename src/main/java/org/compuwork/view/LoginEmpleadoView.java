package org.compuwork.view;

import org.compuwork.controllers.GestorDepartamentos;
import org.compuwork.controllers.GestorEmpleados;
import org.compuwork.exceptions.EmpleadoNoEncontradoException;
import org.compuwork.models.Empleado;
import org.compuwork.models.Metrica;
import org.compuwork.models.Rol;
import org.compuwork.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class LoginEmpleadoView extends JFrame {

    private GestorEmpleados gestorEmpleado;
    private GestorDepartamentos gestorDepartamentos;
    private JTextField txtId;
    private JTextField txtNombre;
    private JButton btnLogin;
    private JButton btnVolver;

    public LoginEmpleadoView(GestorEmpleados gestorEmpleado, GestorDepartamentos gestorDepartamentos) {
        this.gestorEmpleado = gestorEmpleado;
        this.gestorDepartamentos = gestorDepartamentos;

        setTitle("Login Empleado");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        btnLogin = new JButton("Ingresar");
        panel.add(new JLabel());
        panel.add(btnLogin);

        btnVolver = new JButton("Volver");
        panel.add(new JLabel());
        panel.add(btnVolver);

        add(panel);


        btnLogin.addActionListener(e -> login());

        btnVolver.addActionListener(e -> {
            dispose();
            new LoginMain(gestorEmpleado, gestorDepartamentos).setVisible(true);
        });
    }

    private void login() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();

        try {
            Usuario usuario = gestorEmpleado.buscarPorId(id);
            if (usuario.getRol() == Rol.EMPLEADO && usuario.getNombre().equalsIgnoreCase(nombre)) {
                ImageIcon icon = new ImageIcon("src/main/java/org/compuwork/assets/icons/icons8-usuario-30.png");

                JOptionPane.showMessageDialog(this, "Bienvenido Empleado","Aviso",JOptionPane.INFORMATION_MESSAGE,icon);

                new EmpleadoView((Empleado) usuario, gestorEmpleado, gestorDepartamentos).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Datos incorrectos o usuario no es empleado");
            }
        } catch (EmpleadoNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado con id: " + id);
        }
    }


}
