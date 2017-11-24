package server.net;

import common.HangMan;
import common.HangManGame;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ForkJoinPool;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */
public class ClientHandler implements Runnable {

    private final Server server;
    private final SocketChannel clientChannel;
    private final ByteBuffer msgFromClient = ByteBuffer.allocateDirect(1024);
    private  ByteBuffer msgToClient = ByteBuffer.allocateDirect(1024);
    ClientHandler(Server server, SocketChannel clientChannel) {
        this.server = server;
        this.clientChannel = clientChannel;
    }

    @Override
    public void run() {
        HangManGame hangManGame = new HangManGame();
        hangManGame.initGame();
        String message = "Welcome! The HangMan game has started, You can input 'quit' at any time if you want to leave!" ;
        msgToClient =  ByteBuffer.wrap(message.getBytes());
        try {
            sendMsg(msgToClient);
            while (true) {
                try{
                    server.sendBack(msgFromClient.toString());
                }
                catch(Exception ex) {
                   System.out.println(ex);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    void sendMsg(ByteBuffer msg) throws IOException {
        clientChannel.write(msg);
        if (msg.hasRemaining()) {
            throw new IOException("Could not send message");
        }
    }

    void recvMsg() throws IOException {
        msgFromClient.clear();
        int numOfReadBytes;
        numOfReadBytes = clientChannel.read(msgFromClient);
        if (numOfReadBytes == -1) {
            throw new IOException("Client has closed connection.");
        }
        String recvdString = extractMessageFromBuffer();
        ForkJoinPool.commonPool().execute(this);
    }

    void disconnectClient() throws IOException {
        clientChannel.close();
    }

    private String extractMessageFromBuffer() {
        msgFromClient.flip();
        byte[] bytes = new byte[msgFromClient.remaining()];
        msgFromClient.get(bytes);
        return new String(bytes);
    }

}
