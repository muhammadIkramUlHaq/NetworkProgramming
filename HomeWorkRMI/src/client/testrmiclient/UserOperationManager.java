/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.testrmiclient;
import java.util.*;

import common.FileCatalogInterface;

import java.rmi.*;

/**
 *
 * @author user
 */
public class UserOperationManager {
    public static int flag=0; 
    public static void registerlogin() throws Exception{
       
        Scanner sc = new Scanner(System.in);
        FileCatalogInterface userOperation=(FileCatalogInterface)Naming.lookup(StartClient.URL);
        System.out.println("Welcome！Choose to start: 1-login 2-register 3-quit ");
        String Firstchoice= sc.nextLine();
        if(Firstchoice.equals("1")){
            System.out.println("User name:");
            String loginUsername=sc.nextLine();
            System.out.println("Password:");
            String loginpassword=sc.nextLine();
            String login="%"+loginUsername+"$"+loginpassword+"*";
            String feedback=userOperation.login(login);
            if(feedback.equals("Log in successfully")){ flag=1;}
            System.out.println(feedback);
           
            
        }
        else if(Firstchoice.equals("2")){
            System.out.println("User name:");
            String registerUsername=sc.nextLine();
            System.out.println("Password:");
            String registerpassword=sc.nextLine();
            String register="%"+registerUsername+"$"+registerpassword+"*";
            String feedback=userOperation.register(register);
            if(feedback.equals("Successfully register")){ flag=1;}
            System.out.println(feedback);
            
        }
        else if(Firstchoice.equals("3")){
            System.exit(0);
        }
        else{
            System.out.println("Wrong input");
        }
    }
    
}
