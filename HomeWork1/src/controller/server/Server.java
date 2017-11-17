package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */

public class Server {

    public static void printWelcome (){
        System.out.println("Welcome from Server! waiting for client ...");
    }
    public static void main (String args[]) throws InterruptedException, IOException {

        int PORT = 9999;
        String ipAddress = "localhost";
        printWelcome();

        // create the controller.controller socket and start listening to connections
        // then create the socket that will accept connections from clients
        ServerSocket ss = new ServerSocket(PORT);
        while (true){
            Socket s = ss.accept();
            ClientHandler cl1 = new ClientHandler(s);
            cl1.start();
        }

    }
}

