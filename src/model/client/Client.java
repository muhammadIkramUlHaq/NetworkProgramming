package model.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 *
 */

public class Client {

    public static void printWelcome (){
        System.out.println("Welcome from client");
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        int PORT = 9999;
        String ipAddress = "localhost";
        printWelcome();

        Socket s = new Socket(ipAddress,PORT);
        DataInputStream input = new DataInputStream(s.getInputStream());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        Sender sender = new Sender(output);
        sender.start();
        String message;
        while (true){
            try{
                message = input.readUTF();
                if (message.toLowerCase().equals("quit")) break;

                System.out.println(message);
            } catch (IOException e){
                System.out.println(e);
            }
        }
        try {
            s.close();
            input.close();
            output.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}