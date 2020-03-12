package MessageBusFiles;


import MessageTypes.ChatMessage;
import MessageTypes.ClientInfo;
import MessageTypes.StringMessage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import MessageFiles.*;

public class ClientSend implements Runnable{
    private Socket connection;
    private static Scanner input = new Scanner(System.in);
    private ClientInfo clientInfo;

    public ClientSend(Socket connection){
        this.connection = connection;
        clientInfo = new ClientInfo("client", "chat1");
    }

    public String getMessage(){
        return input.nextLine();
    }

    @Override
    public void run() {

        try {
//            DataOutputStream toServer = new DataOutputStream(connection.getOutputStream());
            ObjectOutputStream toServer = new ObjectOutputStream(connection.getOutputStream());
            while(true){
                System.out.print("Enter a message: ");
                String msg = getMessage();
                ChatMessage chatSend = new ChatMessage(clientInfo,  new StringMessage(msg), null);

                toServer.writeObject(chatSend);
                toServer.flush();
                Thread.sleep(10); //TODO: Temporary solution, need to fix.
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
