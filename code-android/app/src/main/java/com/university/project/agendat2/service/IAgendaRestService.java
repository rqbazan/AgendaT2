package com.university.project.agendat2.service;

import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;
import com.university.project.agendat2.model.User;

import java.util.ArrayList;

import rx.SingleSubscriber;
import rx.functions.Action1;

/**
 * Created by santiago on 06/11/16.
 */

public interface IAgendaRestService {
    void authUser(String username, String password);
    void listContacts(int idUser);
    void insertContact(int idUser, Contact contact);
    void insertUser(User user);
}
