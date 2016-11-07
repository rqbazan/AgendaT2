package com.university.project.agendat2.business;

import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.business.util.InsertTransactionException;
import com.university.project.agendat2.business.util.UnknowException;
import com.university.project.agendat2.dao.ContactDAO;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 04/11/16
 */
public class ContactBL {
    // { Singleton
    private static ContactBL instance;

    private ContactBL(){}

    public static ContactBL getInstance(){
        if (instance == null) {
            instance = new ContactBL();
        }
        return instance;
    }
    // } Singleton

    public List<Contact> getAllByIdUser(int idUser) throws ApplicationException {
        try {
            return ContactDAO.getInstance().getAllByIdUser(idUser);
        }catch (Exception e){
            throw new UnknowException();
        }
    }

    public int insert(int idUser, Contact contact) throws ApplicationException{
        try {
            return ContactDAO.getInstance().insert(idUser, contact);
        }catch (SQLException e){
            throw new InsertTransactionException();
        } catch (Exception e){
            throw new UnknowException();
        }
    }
}
