/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;//首先得在服务端写出来如果收到了一连串的字符应该怎么办，如果在客户端选择不同的东西应该怎么处理的；
import common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserOperationHandler {

    public static int flag;
    public static String feedback1;
    public String feedback2;
    public static String user=null;
   public static String login(String loginname){
       String loginname1=getCTX(loginname,"%","$");
       String loginpassword=getCTX(loginname,"$","*");
            Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from User";
            PreparedStatement pstmt;
            System.out.println("sql");
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(loginname1)&&rs.getString(2).equals(loginpassword)){
                    feedback1="Log in successfully"; 
                    user=loginname1;
                    break;
                 
                }
                else{
                    feedback1="Username or password wrong";
                }
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserOperationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
          return feedback1;
      }
       
   public static String register(String registername){
   
        System.out.println(registername);
        String registername1=getCTX(registername,"%","$");
        String registerpassword1 = getCTX(registername,"$","*");
        
        
            Connection conn = MySqlConnector.Mysqlconnector();
            String sql = "select * from user";
            PreparedStatement pstmt;
            try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
                {
                    
                    if(rs.getString(1).equals(registername1))
                    {
                        feedback1 = "Wrong! User exists!" ;
                        flag = 1;
                        break;
                    }
                    
                }
            if (flag==0)
            {
                DatabaseOperationManager.dataInsert(new User(registername1, registerpassword1));
                feedback1 = "Create user Sucessfully! Welcome user: " + registername1;
                user=registername1;
                DatabaseOperationManager.dataBaseGetAll();
            }
            } catch (SQLException e) {
        e.printStackTrace();
    }

          return feedback1;
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

       
     public static String getCTX(String originalCTX,String firstSplit,String secondSplit){//怎样利用分隔符分割结构
        String resultCTX = originalCTX.substring(originalCTX.lastIndexOf(firstSplit), 
        originalCTX.lastIndexOf(secondSplit));
        resultCTX = resultCTX.substring(1,resultCTX.length());
        return resultCTX;
    }
    
}    

