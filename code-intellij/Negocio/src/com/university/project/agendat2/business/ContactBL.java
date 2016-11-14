package com.university.project.agendat2.business;

import com.university.project.agendat2.business.util.*;
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
            if (this.getByDni(idUser, contact.getDni())){
                throw new ApplicationException("Este usuario ya tiene un contacto con el mismo DNI");
            }
            if (this.isNotValid(contact)){
                throw new EntityNotValidException();
            }
            return ContactDAO.getInstance().insert(idUser, contact);
        }catch (SQLException e) {
            throw new InsertTransactionException();
        }catch (ApplicationException e){
            throw e;
        } catch (Exception e){
            throw new UnknowException();
        }
    }

    public boolean getByDni(int idUser, String dni) throws ApplicationException{
        try{
            return ContactDAO.getInstance().getByDni(idUser, dni);
        }catch (SQLException e){
            throw new SelectTransactionException();
        }catch (Exception e){
            throw new UnknowException();
        }
    }

    private boolean isNotValid(Contact contact) throws ApplicationException{
        try {
            return  (contact.getName().trim().isEmpty()
                    || contact.getLastName().trim().isEmpty()
                    || contact.getCellphoneNumber().trim().isEmpty()
                    || contact.getEmail().trim().isEmpty()
                    || contact.getDni().trim().isEmpty()
                    || contact.getSex().toString().trim().isEmpty());
        }catch (Exception e){
            throw new UnknowException();
        }
    }
}
