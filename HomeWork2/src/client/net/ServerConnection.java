package client.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 */
public class ServerConnection implements Runnable {

    private static final String FATAL_COMMUNICATION_MSG = "Lost connection.";
    private static final String FATAL_DISCONNECT_MSG = "Could not disconnect, will leave ungracefully.";

    private ByteBuffer msgFromServer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer messageToSend = ByteBuffer.allocateDirect(1024);
    private final List<CommunicationListener> listeners = new ArrayList();
    private InetSocketAddress serverAddress;
    private SocketChannel socketChannel;
    private Selector selector;
    private boolean connected;
    private volatile boolean timeToSend = false;

    @Override
    public void run() {
        try {
            initConnection();
            initSelector();

            while (connected || messageToSend != null) {
                if (timeToSend) {
                    socketChannel.keyFor(selector).interestOps(SelectionKey.OP_WRITE);
                    timeToSend = false;
                }

                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isConnectable()) {
                        completeConnection(key);
                    } else if (key.isReadable()) {
                        recvFromServer(key);
                    } else if (key.isWritable()) {
                        sendToServer(key);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(FATAL_COMMUNICATION_MSG);
        }
        try {
            doDisconnect();
        } catch (IOException ex) {
            System.err.println(FATAL_DISCONNECT_MSG);
        }
    }

    public void addCommunicationListener(CommunicationListener listener) {
        listeners.add(listener);
    }


    public void connect(String host, int port) {
        serverAddress = new InetSocketAddress(host, port);
        new Thread(this).start();
    }

    private void initSelector() throws IOException {
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    private void initConnection() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(serverAddress);
        connected = true;
    }

    private void completeConnection(SelectionKey key) throws IOException {
        socketChannel.finishConnect();
        key.interestOps(SelectionKey.OP_READ);
        try {
            InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
            notifyConnectionDone(remoteAddress);
        } catch (IOException couldNotGetRemAddrUsingDefaultInstead) {
            notifyConnectionDone(serverAddress);
        }
    }

    public void disconnect() throws IOException {
        connected = false;
        String message = "DISCONNECTED";
        sendMsg(message);
    }

    private void doDisconnect() throws IOException {
        socketChannel.close();
        socketChannel.keyFor(selector).cancel();
        notifyDisconnectionDone();
    }

    public void sendEnteredMessage(String msg) {
        sendMsg(msg);
    }

    private void sendMsg(String message) {
        messageToSend = ByteBuffer.wrap(message.getBytes());
        timeToSend = true;
        selector.wakeup();
    }

    private void sendToServer(SelectionKey key) throws IOException {
        ByteBuffer msg;
        msg = messageToSend;
            while (msg != null) {
                socketChannel.write(msg);
                if (msg.hasRemaining()) {
                    return;
                }
            }
            key.interestOps(SelectionKey.OP_READ);
    }

    private void recvFromServer(SelectionKey key) throws IOException {
        msgFromServer.clear();
        int numOfReadBytes = socketChannel.read(msgFromServer);
        if (numOfReadBytes == -1) {
            throw new IOException(FATAL_COMMUNICATION_MSG);
        }
        String recvdString = extractMessageFromBuffer();
        System.out.println(recvdString);
        notifyMsgReceived(recvdString);
    }

    private String extractMessageFromBuffer() {
        msgFromServer.flip();
        byte[] bytes = new byte[msgFromServer.remaining()];
        msgFromServer.get(bytes);
        return new String(bytes);
    }

    private void notifyConnectionDone(final InetSocketAddress connectedAddress) {
        Executor pool = ForkJoinPool.commonPool();
        for (final CommunicationListener listener : listeners) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    listener.connected(connectedAddress);
                }
            });
        }
    }

    private void notifyDisconnectionDone() {
        Executor pool = ForkJoinPool.commonPool();
        for (final CommunicationListener listener : listeners) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    listener.disconnected();
                }
            });
        }
    }

    private void notifyMsgReceived(final String msg) {
        Executor pool = ForkJoinPool.commonPool();
        for (final CommunicationListener listener : listeners) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    listener.recvdMsg(msg);
                }
            });
        }
    }


}
