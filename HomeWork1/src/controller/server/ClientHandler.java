package controller.server;

import model.hangman.HangMan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */

class ClientHandler extends Thread{
    private Socket socket;

    public ClientHandler(Socket s){
        this.socket = s;
    }

    @Override
    public void run(){
        String mysteryWord = null;

        Scanner playAgain = new Scanner(System.in);
        HangMan hangMan = new HangMan();
        try {

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            hangMan.init(output);

            while (true ){
                mysteryWord = hangMan.randomWordGenerator();
                hangMan.guess(mysteryWord,input,output);
                output.writeUTF("play again? Please enter y to continue !");
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