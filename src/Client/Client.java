/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Al-Haddad 
 */
public class Client {

    public static void printWelcome (){
        System.out.println("Welcome from client");
    }
    
    public static void main(String[] args) throws IOException, InterruptedException{
        int PORT = 9999;
        String IPADDRESS = "localhost";
        int counter = 1;
        printWelcome();
        // create the socket with which we can start the communciation
        Socket s = new Socket(IPADDRESS,PORT);
        // initialize and declare the messages that will be sent to the server
        DataInputStream input = new DataInputStream(s.getInputStream());
        // initialize and declare the output from the server 
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);
        String userMessage;
        while (true){
            //output.writeUTF("Hello from the client" + counter);
            userMessage = sc.nextLine();
            if (userMessage.toLowerCase().equals("quit")) break;
            output.writeUTF(userMessage);
            output.flush();
            while (input.available()==0){
                Thread.sleep(1);

            }
            counter++;
            String message = input.readUTF();
            System.out.println("Server's message = "+message);
            //Thread.sleep(3000);
            if (userMessage.toLowerCase().equals("quit")) break;
        
        }
        s.close();
        input.close();
        output.close();
    }


}
