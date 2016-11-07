package com.university.project.agendat2.desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 05/11/16
 */
public class LoginForm {
    private JPanel pl_main;
    private JTextField txt_username;
    private JTextField txt_password;
    private JButton btn_login;

    public LoginForm() {
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginForm().pl_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
