package MessageBusFiles;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * CLient will manage
 *  one thread for sending messages
 *  one thread for receiving messages
 *  a list of chatrooms
 */
public class Client implements Runnable {
    private ClientSend send;
    private ClientReceive receive;
    private ArrayList<ClientChatroom> chatrooms;
    private Socket serverConnection;

    @Override
    public void run() {
        try{
            setServerConnection("localhost", 8000);
            send = new ClientSend(serverConnection);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServerConnection(String hostname, int port) throws IOException {
        serverConnection = new Socket(hostname, port);
    }
}
