package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RmosLogin implements ActionListener {
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    JFrame frame = new JFrame();

    public void rmosLogin(){

        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        // Password Label
        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();
        // Submit
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(this);
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Please Login Here !");
        frame.setSize(450,250);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        if (userName.trim().equals("admin") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName + "");
            frame.dispose();
            RmosGui.getInstance().createRMOS();
        } else {
            message.setText(" Invalid user.. ");
        }
    }
}
