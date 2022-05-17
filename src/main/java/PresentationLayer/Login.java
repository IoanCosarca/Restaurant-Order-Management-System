package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private final JLabel Name;
    protected JTextField UserName;
    private final JLabel Pass;
    protected JTextField Password;
    protected JButton Login;
    protected JButton CAccount;

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
        CAccount        = new JButton("CREATE ACCOUNT");

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

        Login.setBounds(50, 250,150,50);
        getContentPane().add(Login);

        CAccount.setBounds(250,250,150,50);
        getContentPane().add(CAccount);

        this.setVisible(true);
    }

    public void Refresh()
    {
        this.setVisible(true);
        UserName.setText(null);
        Password.setText(null);
    }
}
