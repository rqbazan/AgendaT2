package com.university.project.agendat2.dao;

import com.university.project.agendat2.dao.util.Conexion;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 04/11/16.
 */
public class ContactDAO {
    // { Singleton
    private static ContactDAO instance;

    private ContactDAO(){}

    public static ContactDAO getInstance(){
        if (instance == null) {
            instance = new ContactDAO();
        }
        return instance;
    }
    // } Singleton

    public List<Contact> getAllByIdUser(int idUser) throws Exception{
        List<Contact> contacts = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            CallableStatement cst = conn.prepareCall("{call sp_ListContactsByIdUser(?)}");
            cst.setInt(1, idUser);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setLastName(rs.getString("lastName"));
                contact.setCellphoneNumber(rs.getString("cellphoneNumber"));
                contact.setEmail(rs.getString("email"));
                contact.setDni(rs.getString("dni"));
                contact.setSex(rs.getString("sex").charAt(0));
                contacts.add(contact);
            }
            return contacts;
        }catch (Exception e){
            throw e;
        }finally {
            if (conn != null)
                conn.close();
        }
    }

    public int insert(int idUser, Contact contact) throws Exception{
        Connection conn = null;
        int idContact;
        try {
            conn = Conexion.getConnection();
            CallableStatement cst = conn.prepareCall("{call sp_InsertContactByIdUser(?,?,?,?,?,?,?,?)}");
            cst.setInt(1, idUser);
            cst.setString(2, contact.getName());
            cst.setString(3, contact.getLastName());
            cst.setString(4, contact.getCellphoneNumber());
            cst.setString(5, contact.getEmail());
            cst.setString(6, String.valueOf(contact.getSex()));
            cst.setString(7, contact.getDni());
            cst.registerOutParameter(8, Types.INTEGER);
            cst.executeUpdate();
            idContact = cst.getInt(8);
            return idContact;
        }catch (Exception e){
            throw e;
        }finally {
            if (conn != null)
                conn.close();
        }
    }
}
