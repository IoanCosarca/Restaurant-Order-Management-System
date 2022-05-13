package PresentationLayer;

import javax.swing.*;

public class MessagePopUp extends JFrame {
    public void showPopUp(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
