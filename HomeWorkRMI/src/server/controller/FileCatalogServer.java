/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;
import common.FileCatalogInterface;
import server.model.FileOperationHandler;
import server.model.UserOperationHandler;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public  class FileCatalogServer extends UnicastRemoteObject implements FileCatalogInterface {
    public FileCatalogServer() throws RemoteException {
    }

    @Override
    public Vector<String> getFileList() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getFileLength(String fileName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getFile(String fileName, int start, int length) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String print() throws RemoteException {

        return "Welcome!!  1: Log in    2: Register";
    
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String login(String loginname) throws RemoteException {
       String feedback= UserOperationHandler.login(loginname); //To change body of generated methods, choose Tools | Templates.
       
       return feedback;
    }
    

    @Override
    public String register(String registername) throws RemoteException {
        String feedback= UserOperationHandler.register(registername);
        return feedback;
    }

    @Override
    public String operationSystem(String input) {
       String feedback1= FileOperationHandler.operationSystem(input);
       return feedback1;
    }
    
    
}
