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


    public void start() {
        if (receivingCmds) {
            return;
        }
        receivingCmds = true;
        server = new ServerConnection();
        new Thread(this).start();
    }

    /**
     * Interprets and performs user commands.
     */
    @Override
    public void run() {
        while (receivingCmds) {
            try {
                CmdLine cmdLine = new CmdLine(readNextLine());
                switch (cmdLine.getCmd()) {
                    case QUIT:
                        receivingCmds = false;
                        server.disconnect();
                        break;
                    case CONNECT:
                        server.addCommunicationListener(new ConsoleOutput());
                        server.connect(cmdLine.getParameter(0),
                                Integer.parseInt(cmdLine.getParameter(1)));
                        break;
                    case USER:
                        server.sendUsername(cmdLine.getParameter(0));
                        break;
                    default:
                        server.sendChatEntry(cmdLine.getUserInput());
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
