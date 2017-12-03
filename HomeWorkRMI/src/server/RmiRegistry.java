/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author user
 */
public class RmiRegistry {
    public static void interinitial() throws Exception{
   
        int port=9999;
        String address="localhost";
        String url= "rmi://"+address+":9999/rmi";
        LocateRegistry.createRegistry(port); 
        Naming.rebind(url, new FileCatalogServer());
        System.out.println("Server Started!");
    }
  
    
}
