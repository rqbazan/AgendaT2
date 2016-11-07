package com.university.project.agendat2.business;

import com.university.project.agendat2.business.util.*;
import com.university.project.agendat2.dao.UserDAO;
import com.university.project.agendat2.model.User;

import java.sql.SQLException;

/**
 * Created by Ricardo Alexis Quiroz Bazan  on 04/11/16.
 */
public class UserBL {
    // { Singleton
    private static UserBL instance;

    private UserBL(){}

    public static UserBL getInstance(){
        if (instance == null) {
            instance = new UserBL();
        }
        return instance;
    }
    // } Singleton

    public User authUser(String username, String password) throws ApplicationException{
        try {
            User user = UserDAO.getInstance().authUser(username, password);
            if (user == null) {
                throw new UserNotFoundException();
            } else if (!user.isActive()) {
                throw new UserDisableException();
            }
            return user;
        }
        catch (ApplicationException e){
            throw e;
        }catch (Exception e){
            throw new UnknowException();
        }
    }

    public int insert(User user) throws ApplicationException{
        try {
            return UserDAO.getInstance().insert(user);
        }catch (SQLException e){
            throw new InsertTransactionException();
        }catch (Exception e){
            throw new UnknowException();
        }
    }
}
