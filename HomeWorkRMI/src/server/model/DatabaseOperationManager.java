/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import common.File;
import common.User;
import server.model.MySqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperationManager {
    public static int dataInsert(User user) {
    Connection conn = MySqlConnector.Mysqlconnector();
    int i = 0;
    String sql = "insert into user (userName,userPassword) values(?,?)";
    PreparedStatement pstmt;
    try {
        
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getUserPassword());
        i = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
    public static int dataInsert1(File File) {
    Connection conn = MySqlConnector.Mysqlconnector();
    int i = 0;
    String sql = "insert into file (fileName,fileOwner,filePrivacy) values(?,?,?)";
    PreparedStatement pstmt;
    try {
        
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, File.getFileName());
        pstmt.setString(2, File.getFileOwner());
        pstmt.setString(3, File.getFilePrivacy());
        i = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
    
   
    
    public static Integer dataBaseGetAll() {
    Connection conn = MySqlConnector.Mysqlconnector();
    String sql = "select * from user";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement)conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        int col = rs.getMetaData().getColumnCount();
        System.out.println("============================");
        while (rs.next()) {
            for (int i = 1; i <= col; i++) {
                System.out.print(rs.getString(i) + "\t");
                if ((i == 2) && (rs.getString(i).length() < 8)) {
                    System.out.print("\t");
                }
             }
            System.out.println("");
        }
            System.out.println("============================");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public static int dataDelete(String userName) {
    Connection conn = MySqlConnector.Mysqlconnector();
    int i = 0;
    String sql = "delete from user where userName='" + userName + "'";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        i = pstmt.executeUpdate();
 //       System.out.println("resutl: " + i);
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
    
    
    public static int dataUpdate(File fileInfo) {
    Connection conn = MySqlConnector.Mysqlconnector();
    int i = 0;
    String sql = "update file set fileOwner='" + fileInfo.getFileOwner() + "' where fileName='" + fileInfo.getFileName() + "'";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        i = pstmt.executeUpdate();
     //   System.out.println("resutl: " + i);
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
 
    
     public static int fileDelete(String fileName) {
    Connection conn = MySqlConnector.Mysqlconnector();
    int i = 0;
    String sql = "delete from file where fileName='" + fileName + "'";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        i = pstmt.executeUpdate();
 //       System.out.println("resutl: " + i);
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
    
}
