package com.kth.homework5.common;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ikram
 */


/**
 * Here are a few things to consider for this game as understood:
 * 1. The server does everything.
 * 2. The server initiates the talking to the user by saying, hey, guess a word.
 * The client will be notified about the number of times he can guess given the
 * number of characters.
 * 3. The user/client replies back with a character or the whole word.
 * 4. The user/client losses if he can't deliver based on the counter.
 *
 * @author ikram
 */
public class HangMan {
    private static List<String> database = new ArrayList<String>();
    private  int score ;
    private Socket clientSocket;

    public HangMan(Socket clientSocket){
           this.clientSocket = clientSocket;
    }
    public void init (DataOutputStream output) {
        try {
            BufferedReader reader = new BufferedReader( new FileReader("words.txt"));
            output.writeUTF("Welcome! The HangMan game has started, You can click 'quit' button at any time if you want to leave! ");
            output.writeUTF("Enjoy playing the game and improve your vocabulary :)");
            int counter = 0;
            String word = reader.readLine();
            while( word != null ) {
                database.add(word);
                word = reader.readLine();
                counter++;
            }
            System.out.println("database created. no. of words = "+counter);
        }
        catch (IOException e){
            System.out.println("File Not Found!");
        }

    }
    public String randomWordGenerator (){
        // Choose a random one from the list
        Random r = new Random();
        String randomWord = database.get(r.nextInt(database.size()));
        return randomWord;

    }

    public void guess (String word, DataInputStream input, DataOutputStream output) throws IOException {
        boolean shouldRun= true;
        System.out.println("The word is " +word);
        String dashed = word.replaceAll(".", "-");
        String aTry;
        int counter = word.length();
        // these two are here because they allow us to play with strings.
        StringBuffer stringBuffer = new StringBuffer(word);
        StringBuffer wordDashed = new StringBuffer(dashed);

        while (shouldRun){
            output.writeUTF("You have "+counter+" tries. The word is "+dashed);

            aTry = input.readUTF();
            if (aTry.toLowerCase().equalsIgnoreCase("quit")){
                clientSocket.close();
                input.close();
                output.close();
                break;
            }
            // test if the input is more than one character
            if (aTry.length()== word.length() && aTry.equalsIgnoreCase(word)){
                this.score++;
                output.writeUTF("cool! you got it right! Your score is : "+this.score);
                break;

            }
            else if (aTry.length()<2 && word.toLowerCase().contains(aTry.toLowerCase()) && !aTry.equals("")){

                char aTryChar = Character.toLowerCase(aTry.charAt(0));
                for(int i=0;i<word.length();i++){
                    if ( Character.toLowerCase(stringBuffer.charAt(i)) == aTryChar ){
                        wordDashed.setCharAt(i, aTryChar);
                        dashed = wordDashed.toString();

                    }
                }
            }
            else{
                output.writeUTF("Incorrect guess!");
                counter--;
            }
            if (counter==0 ) {
                this.score--;
                output.writeUTF("Hanged! Your Score is : "+ this.score);

                break;
            } else if (dashed.equalsIgnoreCase(word)){
                this.score++;
                output.writeUTF("oh man you made it! Your Score is : " + this.score);
                break;
            }
        }
    }
}


