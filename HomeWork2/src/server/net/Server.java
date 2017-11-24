package server.net;

import server.controller.Controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * User: Ikram
 */

public class Server {

    private final Controller contr = new Controller();
    private ByteBuffer messageToSend = ByteBuffer.allocateDirect(1024);

    private int portNo = 1111;
    private Selector selector;
    private ServerSocketChannel listeningSocketChannel;


    void sendBack(String msg) {
        contr.addMessage(msg);
        messageToSend = ByteBuffer.wrap(msg.getBytes());
        selector.wakeup();
    }

    private void initSelector() throws IOException {
        selector = Selector.open();
    }

    private void initListeningSocketChannel() throws IOException {
        listeningSocketChannel = ServerSocketChannel.open();
        listeningSocketChannel.configureBlocking(false);
        listeningSocketChannel.bind(new InetSocketAddress(portNo));
        listeningSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    private void processAcceptEvent(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverSocketChannel.accept();
        clientChannel.configureBlocking(false);
        ClientHandler handler = new ClientHandler(this, clientChannel);
        clientChannel.register(selector, SelectionKey.OP_WRITE, new Client(handler, contr.getMessage()));
    }

    private void recvFromClient(SelectionKey key) throws IOException {
        Client client = (Client) key.attachment();
        try {
            client.handler.recvMsg();
        } catch (IOException clientHasClosedConnection) {
            removeClient(key);
        }
    }

    private void sendToClient(SelectionKey key) throws IOException {
        Client client = (Client) key.attachment();
        try {
            client.sendAll();
            key.interestOps(SelectionKey.OP_READ);
        }
        catch (IOException clientHasClosedConnection) {
            removeClient(key);
        }
    }

    private void removeClient(SelectionKey clientKey) throws IOException {
        Client client = (Client) clientKey.attachment();
        client.handler.disconnectClient();
        clientKey.cancel();
    }

    private void serve() {
        try {
            initSelector();
            initListeningSocketChannel();
            while (true) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        processAcceptEvent(key);
                    } else if (key.isReadable()) {
                        recvFromClient(key);
                    } else if (key.isWritable()) {
                        sendToClient(key);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Server failure.");
        }
    }

    private class Client {
        private final ClientHandler handler;
        private ByteBuffer messageToSend = ByteBuffer.allocate(1024);

        private Client(ClientHandler handler, String message) {
            this.handler = handler;
            this.messageToSend = ByteBuffer.wrap(message.getBytes());
        }

        private void sendAll() throws IOException {
                while ( messageToSend != null) {
                    handler.sendMsg(messageToSend);
                }
        }
    }

    public static void main(String args[]){
            System.out.println("Server Is Ready ! Waiting for Clients ...");
            Server server = new Server();
            server.serve();
    }

}
