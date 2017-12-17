package com.kth.homework5.server;

import com.kth.homework5.common.HangMan;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    private ServerSocket serverSocket;

    Thread serverThread = null;

    public static final int SERVERPORT = 9999;

public  void printWelcome (){
        System.out.println("Welcome from Server! waiting for client ...");
        this.serverThread = new Thread(new Server.ServerThread());
        this.serverThread.start();
}

public static void main (String args[]) throws InterruptedException, IOException {
     Server server = new Server();
     server.printWelcome();
}

    class ServerThread implements Runnable {

        public void run() {
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(SERVERPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!Thread.currentThread().isInterrupted()) {

                try {

                    socket = serverSocket.accept();

                    CommunicationThread commThread = new CommunicationThread(socket);
                    new Thread(commThread).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CommunicationThread implements Runnable {

        private Socket clientSocket;

        public CommunicationThread(Socket clientSocket) {

            this.clientSocket = clientSocket;
        }

        @Override
        public void run(){
            String mysteryWord = null;

            Scanner playAgain = new Scanner(System.in);
            HangMan hangMan = new HangMan(clientSocket);
            try {

                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                hangMan.init(output);

                while (!Thread.currentThread().isInterrupted() ) {
                    mysteryWord = hangMan.randomWordGenerator();
                    hangMan.guess(mysteryWord, input, output);
                    if (clientSocket.isClosed()) {
                        break;
                    } else {
                        output.writeUTF("play again? Please enter y to continue !");
                        String reply = null;
                        try {
                            reply = input.readUTF();
                        } catch (Exception ex) {
                            clientSocket.close();
                            input.close();
                            output.close();
                            break;
                        }
                        if (reply.toLowerCase().equals("y")) {
                            continue;
                        } else {
                            clientSocket.close();
                            input.close();
                            output.close();
                            break;
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    

}