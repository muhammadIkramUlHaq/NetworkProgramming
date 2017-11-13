/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Al-Haddad
 */
public class Server {

    public static void printWelcome (){
        System.out.println("Welcome from the server side");
    }
    public static void main (String args[]) throws InterruptedException, IOException{
        // this is incorrect because I need to create a connection and then send
        // that socket to the handler that will handle stuff. 
        //ClientHandler cl1= new ClientHandler();
        //cl1.start();
        
        int PORT = 9999;
        String IPADDRESS = "localhost";
        printWelcome();
        
        // create the server socket and start listening to connections
        // then create the socket that will accept connections from clients
        ServerSocket ss = new ServerSocket(PORT);
        while (true){
        Socket s = ss.accept();
        ClientHandler cl1 = new ClientHandler(s);
        cl1.start();
        }
        /* This part is hidden for now, but it's supposed to be more than one
        DataInputStream input = new DataInputStream(s.getInputStream());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        
        while (input.available()==0){
            Thread.sleep(1);
        }
        String message = input.readUTF();
        System.out.println("Your message from the client is " +message);
        output.writeUTF("hello from the server");   
        */ 
    }
}

class ClientHandler extends Thread{
    Socket socket;
    public ClientHandler(Socket s){
        this.socket = s;
    }
    
    @Override
    public void run(){
        String modifiedMessage = null;
        try {
            
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            while (true){
                while (input.available()==0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String message = input.readUTF();
                System.out.println("Client message is = " +message);
                modifiedMessage = modify(message);
                output.writeUTF(modifiedMessage);
                output.flush();
            }
            } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
                    
        }
    public String modify(String message){
        String m2m = message+" mirrors " + message;
        //System.out.println(m2m);
        return m2m;
    }
                    
        
    }




