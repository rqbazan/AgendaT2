package com.university.project.agendat2.desktop;

import com.university.project.agendat2.business.UserBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 05/11/16
 */
public class LoginForm {
    private JPanel pl_main;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JButton btn_login;
    private JPanel pl_image;
    private JLabel lbl_image;
    private JButton btn_signup;
    private Frame windows;

    public LoginForm(Frame windows) {
        this.windows = windows;
        try {
            ImageIcon image = new ImageIcon(getClass().getResource("/images/locked.png"));
            lbl_image.setIcon(image);
        }catch (Exception e){
            e.printStackTrace();
        }
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authUser();
            }
        });
        btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm.Run(null, null);
            }
        });
        txt_password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authUser();
                }
            }
        });
    }

    private void authUser(){
        try {
            String username = txt_username.getText();
            String password = String.valueOf(txt_password.getPassword());
            User user = UserBL.getInstance().authUser(username, password);
            windows.dispose();
            HomeForm.run(user);
        }catch (ApplicationException error) {
            JOptionPane.showMessageDialog(null,
                    error.getMessage(),
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            error.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            }catch (Exception e){
                e.printStackTrace();
            }
            JFrame windows = new JFrame();
            windows.setTitle("AgendaT2 - Log in");
            windows.setPreferredSize(new Dimension(300, 400));
            windows.setLocation(500, 200);
            windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windows.setContentPane(new LoginForm(windows).pl_main);
            windows.pack();
            windows.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
