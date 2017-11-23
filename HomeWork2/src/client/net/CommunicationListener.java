package client.net;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 * Date: 11/23/17
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommunicationListener {

    public void recvdMsg(String msg);

    public void connected(InetSocketAddress serverAddress);

    public void disconnected();
}
