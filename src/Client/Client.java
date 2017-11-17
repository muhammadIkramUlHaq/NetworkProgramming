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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


}
class UIHandler extends Thread{
    DataInputStream input;
    public UIHandler(DataInputStream in){
        this.input = in;
    }
    public  void run() {
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
            input.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

/*class UIHandler extends Thread {

    public String serverMessage;
    public UIHandler(String message ){
        this.serverMessage = message;
    }
    @Override
    public void run (){
          if(serverMessage != null) {
             System.out.println(serverMessage);
          }
    }
}    */
class Sender extends Thread{

    private DataOutputStream output;

    public Sender(DataOutputStream out)  {
        this.output = out;


    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        while(true){
            try{
                userInput = sc.nextLine();


                output.writeUTF(userInput);
                output.flush();
                if (userInput.toLowerCase().equals("quit")) break;
            }  catch (IOException e) {
                System.out.println(e);
            }
        }
        //s.close();
        //input.close();
        /*try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } */
    }

}
