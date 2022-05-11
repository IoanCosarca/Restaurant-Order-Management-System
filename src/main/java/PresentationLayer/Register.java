package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {
    private JLabel Name;
    protected JTextField UserName;
    private JLabel Pass;
    protected JTextField Password;
    private JLabel Type;
    protected JComboBox<String> UserTypes;
    protected JButton Register;
    protected JButton GoBack;

    public Register()
    {
        this.setSize(500,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Register User");
        getContentPane().setBounds(0,0,450,450);
        getContentPane().setLayout(null);

        Font MyFont     = new Font("", Font.PLAIN,20);
        Name            = new JLabel("User Name");
        UserName        = new JTextField();
        Pass            = new JLabel("Password");
        Password        = new JTextField();
        Type            = new JLabel("Type");
        String[] types  = new String[3];
        types[0]        = "ADMINISTRATOR";
        types[1]        = "EMPLOYEE";
        types[2]        = "CLIENT";
        UserTypes       = new JComboBox<String>(types);
        Register        = new JButton("REGISTER");
        GoBack          = new JButton("GO BACK");

        JPanel RegisterCredentials = new JPanel();
        getContentPane().add(RegisterCredentials);
        RegisterCredentials.setLayout(new GridLayout(2,2));
        RegisterCredentials.setBounds(50,100,400,100);
        Name.setFont(MyFont);
        RegisterCredentials.add(Name);
        UserName.setFont(MyFont);
        RegisterCredentials.add(UserName);
        Pass.setFont(MyFont);
        RegisterCredentials.add(Pass);
        Password.setFont(MyFont);
        RegisterCredentials.add(Password);

        Type.setFont(MyFont);
        Type.setBounds(50,200,100,50);
        getContentPane().add(Type);
        UserTypes.setFont(MyFont);
        UserTypes.setBounds(250,200,200,50);
        getContentPane().add(UserTypes);

        Register.setFont(MyFont);
        Register.setBounds(50, 300,150,50);
        getContentPane().add(Register);

        GoBack.setFont(MyFont);
        GoBack.setBounds(250,300,150,50);
        getContentPane().add(GoBack);

        this.setVisible(false);
    }

    public void showPopUp(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
