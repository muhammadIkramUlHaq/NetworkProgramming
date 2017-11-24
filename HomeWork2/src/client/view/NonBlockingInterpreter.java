package client.view;

import client.net.CommunicationListener;
import client.net.ServerConnection;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */
public class NonBlockingInterpreter implements Runnable{

    private static final String PROMPT = "> ";
    private final Scanner console = new Scanner(System.in);
    private final ThreadSafeStdOut outMgr = new ThreadSafeStdOut();
    private boolean receivingCmds = false;
    private ServerConnection server;
    private final int portNumber = 1111 ;
    private final String serverAddress = "localhost" ;


    public void start() {
        if (receivingCmds) {
            return;
        }
        receivingCmds = true;
        server = new ServerConnection();
        initClientConnection();
        new Thread(this).start();

    }


    private  void initClientConnection(){
        server.addCommunicationListener(new ConsoleOutput());
        server.connect(serverAddress,portNumber);
        System.out.println("Client Connected !!! ");
    }
    /**
     * Interprets and performs user commands.
     */
    @Override
    public void run() {
        while (receivingCmds) {
            try {
                CmdLine cmdLine = new CmdLine(readNextLine());
                    if (cmdLine.getUserInput().equalsIgnoreCase("quit"))  {
                        receivingCmds = false;
                        server.disconnect();
                    }
                    else {
                        server.sendEnteredMessage(cmdLine.getUserInput());
                    }
            } catch (Exception e) {
                outMgr.println("Operation failed");
            }
        }
    }

    private String readNextLine() {
        outMgr.print(PROMPT);
        return console.nextLine();
    }

    private class ConsoleOutput implements CommunicationListener {
        @Override
        public void recvdMsg(String msg) {
            printToConsole(msg);
        }

        @Override
        public void connected(InetSocketAddress serverAddress) {
            printToConsole("Connected to " + serverAddress.getHostName() + ":"
                    + serverAddress.getPort());
        }

        @Override
        public void disconnected() {
            printToConsole("Disconnected from server.");
        }

        private void printToConsole(String output) {
            outMgr.println(output);
            outMgr.print(PROMPT);
        }
    }


}
