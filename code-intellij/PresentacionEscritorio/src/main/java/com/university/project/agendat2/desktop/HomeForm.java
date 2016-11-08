package com.university.project.agendat2.desktop;

import com.university.project.agendat2.business.ContactBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 07/11/16
 */
public class HomeForm {
    private JTable tbl_contacts;
    private JPanel pl_main;
    private JButton btn_new_contact;
    private JScrollPane psl_wrapper;
    private DefaultTableModel model;

    private int counterRows;
    private User currentUser;

    public HomeForm(final User user) {
        counterRows = 0;
        currentUser = user;
        btn_new_contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm.Run(HomeForm.this, user);
            }
        });
    }

    private void createUIComponents() {
        model = new DefaultTableModel(0, 5);
        String[] header = {"N°", "Nombre completo", "Sexo", "Correo-e", "Celular"};
        model.setColumnIdentifiers(header);
        tbl_contacts = new JTable(model);
        setColumnWidth(0, 40);
        setColumnWidth(2, 40);
        setColumnWidth(4, 120);
        tbl_contacts.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        try {
            java.util.List<Contact> contacts = ContactBL.getInstance().getAllByIdUser(currentUser.getId());
            for (Contact contact : contacts) {
                addRow(contact);
            }
        }catch (ApplicationException error) {
            JOptionPane.showMessageDialog(null,
                    error.getMessage(),
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
            error.printStackTrace();
        }
    }

    private void setColumnWidth(int index, int width){
        tbl_contacts.getColumnModel().getColumn(index).setMinWidth(width);
        tbl_contacts.getColumnModel().getColumn(index).setMaxWidth(width);
    }

    public void addRow(Contact contact){
        try {
            counterRows++;
            model.addRow(new Object[]{
                    counterRows,
                    contact.getName() + " " + contact.getLastName(),
                    contact.getSex(),
                    contact.getEmail(),
                    contact.getCellphoneNumber()
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void run(User user) {
        try {
            JFrame windows = new JFrame();
            windows.setTitle("AgendaT2");
            windows.setPreferredSize(new Dimension(700, 400));
            windows.setContentPane(new HomeForm(user).pl_main);
            windows.setLocation(500, 200);
            windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windows.pack();
            windows.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
