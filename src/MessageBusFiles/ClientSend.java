package MessageBusFiles;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable{
    private Socket connection;
    private static Scanner input = new Scanner(System.in);

    public ClientSend(Socket connection){
        this.connection = connection;
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
                ChatMessage chatSend = new ChatMessage(new StringMessage(getMessage()));
                toServer.writeUTF(getMessage());
                toServer.flush();
                Thread.sleep(10); //TODO: Temporary solution, need to fix.
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
