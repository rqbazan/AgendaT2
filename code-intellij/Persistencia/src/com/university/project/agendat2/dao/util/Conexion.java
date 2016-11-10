package com.university.project.agendat2.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Ricardo Alexis Quiroz Bazan  on 04/11/16.
 */
public class Conexion {
    // Utility class
    private Conexion(){}

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/dbAgendaT2";
    private static final String REMOTE_DB_URL = "jdbc:mysql://40.112.60.102:3306/dbagendat2";

    //  Database credentials
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    public static Connection getConnection() throws Exception{
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return conn;
        }catch (Exception e){
            throw e;
        }
    }
}
