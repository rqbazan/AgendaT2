package com.university.project.agendat2.model;

import java.io.Serializable;

/**
 * Created by Ricardo Alexis Quiroz Bazan  on 03/11/16.
 */

public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private boolean active;
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
