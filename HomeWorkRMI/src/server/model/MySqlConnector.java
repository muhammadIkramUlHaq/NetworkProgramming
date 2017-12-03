/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlConnector {
   
    
    public static Connection Mysqlconnector(){
        Connection con =null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/file_catalog_rmi";
        String user = "root";
        String password = "";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
                 
         } catch (Exception e) {
            e.printStackTrace();
    }
            return con;
    
}
}
