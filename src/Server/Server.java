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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangman.*;

/**
 *
 * @author Ahmed Al-Haddad
 */
public class Server {

    public static void printWelcome (){
        System.out.println("Welcome from the server side");
    }
    public static void main (String args[]) throws InterruptedException, IOException{

        int PORT = 9999;
        String ipAddress = "localhost";
        printWelcome();
        
        // create the server socket and start listening to connections
        // then create the socket that will accept connections from clients
        ServerSocket ss = new ServerSocket(PORT);
        while (true){
            Socket s = ss.accept();
            ClientHandler cl1 = new ClientHandler(s);
            cl1.start();
        }

    }
}

class ClientHandler extends Thread{
    Socket socket;
    public ClientHandler(Socket s){
        this.socket = s;
    }
    
    @Override
    public void run(){
        String actualWord = null;

        Scanner playAgain = new Scanner(System.in);


        try {

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            HangMan.init();

            while (true ){
                actualWord = HangMan.randomWordGenerator();
                HangMan.guess(actualWord,input,output);
                output.writeUTF("play again? Please enter y to continue");
                String reply = null;
                try {
                   reply = input.readUTF();
                }
                catch (Exception ex){
                    socket.close();
                    input.close();
                    output.close();
                    break;
                }
                if (reply.toLowerCase().equals("y")){
                    continue;
                } else {
                    output.writeUTF("quit");
                    break;

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
    }
        
    }




