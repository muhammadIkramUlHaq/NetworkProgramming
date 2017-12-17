package homework5.kth.com.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private Socket socket;
    private static final int SERVERPORT = 9999;
    private static final String SERVER_IP = "130.229.175.69";
    Handler updateConversationHandler;
    private EditText responseString;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseString = (EditText) findViewById(R.id.responseText);
        // To update the GUI on client Side
        updateConversationHandler = new Handler();
        // To Connect to Server
        Thread clientThread= new Thread(new ClientThread());
        clientThread.start();

    }



    public void onClick(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.EditText01);
            String str = et.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitSession(View view) {
        try {
            Button et = (Button) findViewById(R.id.quitButton);
            String quitString = "quit";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(quitString);
            socket.close();
            out.close();
            System.exit(0);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ListenerThread implements Runnable {

        private Socket socket;

        private BufferedReader input;

        public ListenerThread(Socket socket){
            this.socket = socket;

            try {

                this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

                while (!Thread.currentThread().isInterrupted()) {

                    try {

                        String read = input.readLine();

                        updateConversationHandler.post(new updateUIThread(read));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }

    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket(serverAddr, SERVERPORT);

                Thread listenerThread = new Thread(new ListenerThread(socket));
                listenerThread.start();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    class updateUIThread implements Runnable {
        private String msg;

        public updateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            responseString.setText(responseString.getText().toString()+"Server Says: "+ msg + "\n");
        }

    }
}


