/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class FileOperationHandler {
    public static String [] filenames=new String [40];
    public static String [] fileowner=new String [40];
     public static String [] filelevel=new String [40];
    public static String username;
    public static String operationSystem(String input){
        username= UserOperationHandler.user;
        String result = null;                
        int filenumber=MAX(filenames);        
       if(input.equals("QUIT")){
           result="log out successfully";
       }        
       else if(input.equals("EXIT")){
           
           DatabaseOperationManager.dataDelete(username);
           Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from file";
            PreparedStatement pstmt;
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getString(2).equals(username)){
                    DatabaseOperationManager.dataDelete(username);
                }             
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserOperationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
           result="unregister successfully";
       }
       else{
           String operationflag=getCTX(input,"<",":");
           String filename=getCTX(input,":",">");

           if(operationflag.equals("upload")){ 
               boolean breakFlag = true;
               String privatelevel=getCTX(input,">","~");
               System.out.println("wow: "+privatelevel);
                Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from file";
            PreparedStatement pstmt;
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if((rs.getString(1).equals(filename))){
                    result="File already exists";
                    breakFlag = false;
                    break;
                }
            }
            if (breakFlag){
                DatabaseOperationManager.dataInsert1(new File(filename, username, privatelevel));
                result="upload successfully";
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserOperationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

           }
           else if(operationflag.equals("download")){
               boolean breakFlag = true;
                Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from file";
            PreparedStatement pstmt;
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                
                if((rs.getString(1).equals(filename))&&!(rs.getString(3).equals("1"))){                  
                    result="download successfully";
                    breakFlag = false;
                    break;
                }
                else if((rs.getString(1).equals(filename))&&(!(rs.getString(2).equals(username)))&&(rs.getString(3).equals("1"))){
                    result="download not allowed";
                    breakFlag = false;
                    break;
                }
                
            }
            if (breakFlag){
                result="File not exist";
               
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserOperationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

           }
        else if(operationflag.equals("update")){
            Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from file";
            PreparedStatement pstmt;
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(((rs.getString(1).equals(filename)))&&!(rs.getString(3).equals("2"))){                  
                    DatabaseOperationManager.dataUpdate(new File(filename, username, null));
                    result="update successfully";
                    break;
                }
                else if((rs.getString(1).equals(filename))&&(!(rs.getString(2).equals(username)))&&(rs.getString(3).equals("2"))){
                    result="update not allowed";
                    break;
                }
                 if(((rs.getString(1).equals(filename)))&&(rs.getString(3).equals("2"))&&((rs.getString(2).equals(username)))){                  
                    DatabaseOperationManager.dataUpdate(new File(filename, username, null));
                    result="update successfully";
                    break;
                }
                else
                {
                    result="File not exist";
 
                }
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserOperationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

           
           } 
           else if(operationflag.equals("delete")){
            Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from file";
            PreparedStatement pstmt;
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(((rs.getString(1).equals(filename)))&&!(rs.getString(3).equals("2"))&&!(rs.getString(3).equals("1"))){                  
                    DatabaseOperationManager.fileDelete(filename);
                    result="delete successfully";
                    break;
                }
                else if((rs.getString(1).equals(filename))&&(!(rs.getString(2).equals(username)))&&(rs.getString(3).equals("2"))){
                    result="delete not allowed";
                    break;
                }
                 if(((rs.getString(1).equals(filename)))&&(rs.getString(3).equals("2"))&&((rs.getString(2).equals(username)))){                  
                    DatabaseOperationManager.fileDelete(filename);
                    result="delete successfully";
                    break;
                }
                 if(((rs.getString(1).equals(filename)))&&!(rs.getString(2).equals(username))&&(rs.getString(3).equals("1"))){                  
    
                    result="delete not allowed";
                    break;
                }
                 if(((rs.getString(1).equals(filename)))&&(rs.getString(2).equals(username))&&(rs.getString(3).equals("1"))){                  
                    DatabaseOperationManager.fileDelete(filename);
                    result="delete successfully";
                    break;
                }
                else
                {
                    result="File not exist";
 
                }
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserOperationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
           
           
       }
        return result;
        
    }
    public static String getCTX(String originalCTX,String firstSplit,String secondSplit){
        String resultCTX = originalCTX.substring(originalCTX.lastIndexOf(firstSplit), 
        originalCTX.lastIndexOf(secondSplit));
        resultCTX = resultCTX.substring(1,resultCTX.length());
        return resultCTX;
    }
     public static int MAX(String[] M){
     int n = 0;
        for (String M1 : M) {
            if (null != M1) {
                n++;
            }
        }
    return n;    
    }  
  
}
