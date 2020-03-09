package MessageBusFiles;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import MessageFiles.*;

public class ClientSend implements Runnable{
    private Socket connection;
    private static Scanner input = new Scanner(System.in);

    public ClientSend(Socket connection){
        this.connection = connection;
    }

    public ChatMessage getMessage(){
        System.out.print("Enter a message: ");
        return new ChatMessage(new StringMessage(input.nextLine()));
    }

    @Override
    public void run() {

        try {
            ObjectOutputStream toServer = new ObjectOutputStream(connection.getOutputStream());
            while(true){
                toServer.writeObject(getMessage());
                toServer.flush();
                Thread.sleep(10); //TODO: Temporary solution, need to fix.
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
