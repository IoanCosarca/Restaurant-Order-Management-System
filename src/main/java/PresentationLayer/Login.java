package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JLabel Name;
    protected JTextField UserName;
    private JLabel Pass;
    protected JTextField Password;
    protected JButton Login;
    protected JButton Register;
    private JLabel ResetPassword;

    public Login()
    {
        this.setSize(450,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login User");
        getContentPane().setBounds(0,0,450,450);
        getContentPane().setLayout(null);

        Font MyFont     = new Font("", Font.PLAIN,20);
        Name            = new JLabel("User Name");
        UserName        = new JTextField();
        Pass            = new JLabel("Password");
        Password        = new JTextField();
        Login           = new JButton("LOGIN");
        Register        = new JButton("REGISTER");
        ResetPassword   = new JLabel("Reset Password");

        JPanel LoginCredentials = new JPanel();
        getContentPane().add(LoginCredentials);
        LoginCredentials.setLayout(new GridLayout(2,2));
        LoginCredentials.setBounds(50,100,350,100);
        Name.setFont(MyFont);
        LoginCredentials.add(Name);
        UserName.setFont(MyFont);
        LoginCredentials.add(UserName);
        Pass.setFont(MyFont);
        LoginCredentials.add(Pass);
        Password.setFont(MyFont);
        LoginCredentials.add(Password);

        ResetPassword.setBounds(250,200,200,50);
        getContentPane().add(ResetPassword);

        Login.setFont(MyFont);
        Login.setBounds(50, 250,150,50);
        getContentPane().add(Login);

        Register.setFont(MyFont);
        Register.setBounds(250,250,150,50);
        getContentPane().add(Register);

        this.setVisible(true);
    }

    public void showPopUp(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
