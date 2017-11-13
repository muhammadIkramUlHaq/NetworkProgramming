
package hangman;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ahmed 
 */


/**
 * Here are a few things to consider for this game as understood from the hw:
 * 1. The server does everything. 
 * 2. The server initiates the talking to the user by saying, hey, guess a word.
 * The client will be notified about the number of times he can guess given the 
 * number of characters. 
 * 3. The user/client replies back with a character or the whole word. 
 * 4. The user/client losses if he can't deliver based on the counter.
 * 
 * @author Asmar
 */
public class HangMan {
    static List<String> database = new ArrayList<String>();

   

    /**
     *
     */
    private static void init () throws FileNotFoundException, IOException {
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
    public static String randomWordGenerator (){
        // Choose a random one from the list
        Random r = new Random();
        String randomWord = database.get(r.nextInt(database.size()));

        
        return randomWord;
        
    }
    public static void main(String args[]) throws IOException{
        // first things first, let's try to open a file with lots of words in it.
        Scanner playAgain = new Scanner(System.in);
        init();
        
        while (true ){
            guess();
            System.out.println("play again? Please enter y to continue");
            String reply = playAgain.nextLine();
            if (reply.toLowerCase().equals("y")){
                continue;
            } else {
                System.out.println("bye");
                break;
                
            }
        }
            
    }
    
    //public static void guess (String word, String a)
    public static void guess () throws IOException{
        boolean shouldRun= true;
        Scanner sc = new Scanner(System.in);
        String random = randomWordGenerator();
        String word = random;
        System.out.println("The word is " +random);
        String dashed = word.replaceAll(".", "-");
        String aTry;
        int counter = word.length();
        // these two are here because they allow us to play with strings.
        StringBuffer sb = new StringBuffer(word);
        StringBuffer wordDashed = new StringBuffer(dashed);
        // I need to build some kind of progress here hmm 
        while (shouldRun){
            
            System.out.print("You have "+counter+" tries.");
            System.out.println(" The word is "+dashed);
            aTry = sc.nextLine();
            // test if the input is more than one character 
            if (aTry.length()== word.length() && aTry.equals(word)){
                //if (aTry.equals(word)){
                System.out.println("cool! you got it right!");
                break;
                 
            } 
            else if (aTry.length()<2 && word.contains(aTry) && !aTry.equals("")){                

                char aTryChar = aTry.charAt(0);
                for(int i=0;i<word.length();i++){
                                        
                    if (sb.charAt(i)==(aTryChar)){
                        wordDashed.setCharAt(i, aTryChar);
                        dashed = wordDashed.toString();
                        System.out.println(dashed);       
                        }
                    }  
                }
            else{
                System.out.println ("Incorrect guess bro!");
                counter--;
            }            
            if (counter==0 ) {
                System.out.println("Hanged!");
                break;
            } else if (dashed.equals(word)){
                System.out.println("oh man you made it!");
                break;
            }
        }   
    }
}
    

