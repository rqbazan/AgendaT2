package com.university.project.agendat2.desktop;

import com.university.project.agendat2.business.ContactBL;
import com.university.project.agendat2.business.UserBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;
import com.university.project.agendat2.model.SexType;
import com.university.project.agendat2.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 08/11/16
 */
public class RegisterForm {
    private JPanel pl_main;
    private JPanel pl_data_user;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JPasswordField txt_confirm_password;
    private JTextField txt_name;
    private JTextField txt_last_name;
    private JTextField txt_email;
    private JTextField txt_cellphonenumber;
    private JTextField txt_dni;
    private JButton btn_register;
    private JRadioButton rbtn_male;
    private JRadioButton rbtn_female;
    private User currentUser;
    private Frame windows;
    private HomeForm home;

    public RegisterForm(HomeForm home, Frame windows, User user) {
        this.home = home;
        this.windows = windows;
        this.currentUser = user;
        if (user != null){
            pl_data_user.setVisible(false);
        }
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user == null) registerUser();
                else registerContact();
            }
        });
    }

    private void registerUser(){
        try {
            String password = String.valueOf(txt_password.getPassword()).trim();
            String confirm = String.valueOf(txt_confirm_password.getPassword()).trim();
            if (!password.equals(confirm)){
                JOptionPane.showMessageDialog(null,
                        "Las contraseñas no coinciden",
                        "Alerta",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            User user = new User();
            user.setUsername(txt_username.getText());
            user.setPassword(password);
            Person person = new Person();
            person.setName(txt_name.getText());
            person.setLastName(txt_last_name.getText());
            person.setEmail(txt_email.getText());
            person.setCellphoneNumber(txt_cellphonenumber.getText());
            person.setDni(txt_dni.getText());
            if(rbtn_female.isSelected()){
                person.setSex(SexType.FEMALE.asChar());
            }else if(rbtn_male.isSelected()){
                person.setSex(SexType.MALE.asChar());
            }
            user.setPerson(person);
            UserBL.getInstance().insert(user);
            windows.dispose();
        }catch (ApplicationException error){
            JOptionPane.showMessageDialog(null,
                    error.getMessage(),
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            error.printStackTrace();
        }
    }

    private void registerContact(){
        try {
            Contact contact = new Contact();
            contact.setName(txt_name.getText());
            contact.setLastName(txt_last_name.getText());
            contact.setEmail(txt_email.getText());
            contact.setCellphoneNumber(txt_cellphonenumber.getText());
            contact.setDni(txt_dni.getText());
            if(rbtn_female.isSelected()){
                contact.setSex(SexType.FEMALE.asChar());
            }else if(rbtn_male.isSelected()){
                contact.setSex(SexType.MALE.asChar());
            }
            int newIdContact = ContactBL.getInstance().insert(currentUser.getId(), contact);
            windows.dispose();
            contact.setId(newIdContact);
            home.addRow(contact);
        }catch (ApplicationException error){
            JOptionPane.showMessageDialog(null,
                    error.getMessage(),
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            error.printStackTrace();
        }
    }

    public static void Run(HomeForm home, User user){
        try {
            JFrame windows = new JFrame();
            windows.setTitle((user == null)? "Registro de usuario" : "Registro de contacto");
            windows.setPreferredSize(new Dimension(400, (user != null)? 420 : 600));
            windows.setContentPane(new RegisterForm(home, windows, user).pl_main);
            windows.setLocation(500, 100);
            windows.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            windows.pack();
            windows.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
