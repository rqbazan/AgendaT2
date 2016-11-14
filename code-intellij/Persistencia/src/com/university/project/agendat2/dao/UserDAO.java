package com.university.project.agendat2.dao;

import com.university.project.agendat2.dao.util.Conexion;
import com.university.project.agendat2.model.Person;
import com.university.project.agendat2.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Created by Ricardo Alexis Quiroz Bazan  on 04/11/16.
 */
public class UserDAO {
    // { Singleton
    private static UserDAO instance;

    private UserDAO(){}

    public static UserDAO getInstance(){
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
    // } Singleton

    public User authUser(String username, String password) throws Exception{
        User user = null;
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            CallableStatement cst = conn.prepareCall("{call sp_authUser(?,?)}");
            cst.setString(1, username);
            cst.setString(2, password);
            ResultSet rs = cst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(password);
                user.setActive(rs.getBoolean("active"));
                Person person = new Person();
                person.setId(rs.getInt("idPerson"));
                person.setName(rs.getString("name"));
                person.setLastName(rs.getString("lastName"));
                person.setCellphoneNumber(rs.getString("cellphoneNumber"));
                person.setEmail(rs.getString("email"));
                person.setDni(rs.getString("dni"));
                person.setSex(rs.getString("sex").charAt(0));
                user.setPerson(person);
            }
            return user;
        }catch (Exception e){
            throw e;
        }finally {
            if (conn != null){
                conn.close();
            }
        }
    }


    public int insert(User user) throws Exception{
        Connection conn = null;
        int idUser;
        try {
            conn = Conexion.getConnection();
            CallableStatement cst = conn.prepareCall("{call sp_InsertUser(?,?,?,?,?,?,?,?,?)}");
            cst.setString(1, user.getUsername());
            cst.setString(2, user.getPassword());
            cst.setString(3, user.getPerson().getName());
            cst.setString(4, user.getPerson().getLastName());
            cst.setString(5, user.getPerson().getCellphoneNumber());
            cst.setString(6, user.getPerson().getEmail());
            cst.setString(7, String.valueOf(user.getPerson().getSex()));
            cst.setString(8, user.getPerson().getDni());
            cst.registerOutParameter(9, Types.INTEGER);
            cst.executeUpdate();
            idUser = cst.getInt(9);
            return idUser;
        }catch (Exception e){
            throw e;
        }finally {
            if (conn != null)
                conn.close();
        }
    }

    public boolean getByDni(String dni) throws Exception{
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            CallableStatement cst = conn.prepareCall("{call sp_GetUserByDni(?)}");
            cst.setString(1, dni);
            ResultSet rs = cst.executeQuery();
            return rs.next();
        }catch (Exception e){
            throw e;
        }finally {
            if (conn != null)
                conn.close();
        }
    }

}
