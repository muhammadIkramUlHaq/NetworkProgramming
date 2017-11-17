package model.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */

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
    }

}