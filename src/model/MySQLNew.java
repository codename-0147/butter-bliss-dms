/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author barth
 */
public class MySQLNew implements Serializable {
    public String host;
    public String port;
    public String pw;
    public String un;
    public String dbname;
    public String dump;
    
    public static Connection connection;
    
    public static void createConnection() throws Exception {
        if (connection == null) {
            String appPath = new File("").getAbsolutePath();
            File dbInfoFile = new File(appPath + File.separator + "dbinfo.ser");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbInfoFile));
            MySQLNew db = (MySQLNew) ois.readObject();
            ois.close();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+db.host+":"+db.port+"/"+db.dbname, db.un, db.pw);
        }
    }
    
    public static ResultSet executeSearch(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeQuery(query);
    }
    
    public static Integer executeIUD(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }
}
