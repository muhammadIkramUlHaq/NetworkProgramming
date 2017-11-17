
package model.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ikram
 */


/**
 * Here are a few things to consider for this game as understood:
 * 1. The controller.server does everything.
 * 2. The controller.server initiates the talking to the user by saying, hey, guess a word.
 * The client will be notified about the number of times he can guess given the 
 * number of characters. 
 * 3. The user/client replies back with a character or the whole word. 
 * 4. The user/client losses if he can't deliver based on the counter.
 * 
 * @author ikram
 */
public class HangMan {
    static List<String> database = new ArrayList<String>();

   public static void init () {
       try {
        BufferedReader reader = new BufferedReader( new FileReader("words.txt"));
        
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
    public static String randomWordGenerator (){
        // Choose a random one from the list
        Random r = new Random();
        String randomWord = database.get(r.nextInt(database.size()));

        
        return randomWord;
        
    }

    //public static void guess (String word, String a)
    public static void guess (String word, DataInputStream input, DataOutputStream output) throws IOException {
        boolean shouldRun= true;
        System.out.println("The word is " +word);
        String dashed = word.replaceAll(".", "-");
        String aTry;
        int score = 0;
        int counter = word.length();
        // these two are here because they allow us to play with strings.
        StringBuffer stringBuffer = new StringBuffer(word);
        StringBuffer wordDashed = new StringBuffer(dashed);
        output.writeUTF("Welcome.");
        while (shouldRun){
            output.writeUTF("You have "+counter+" tries. The word is "+dashed);

            aTry = input.readUTF();
            if (aTry.toLowerCase().equalsIgnoreCase("quit")){
                output.writeUTF("quit");
                break;
            }
            // test if the input is more than one character 
            if (aTry.length()== word.length() && aTry.equalsIgnoreCase(word)){
                    score++;
                output.writeUTF("cool! you got it right! Your score is : "+score);
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
                output.writeUTF("Incorrect guess bro!");
                counter--;
            }            
            if (counter==0 ) {
                output.writeUTF("Hanged!");
                break;
            } else if (dashed.equalsIgnoreCase(word)){
                score++;
                output.writeUTF("oh man you made it! Your Score is :" + score);
                break;
            }
        }   
    }
}
    
