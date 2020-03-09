package MessageBusFiles;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
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
            DataOutputStream toServer = new DataOutputStream(connection.getOutputStream());
            while(true){
                System.out.print("Enter a message: ");
                toServer.writeUTF(getMessage());
                toServer.flush();
                Thread.sleep(10); //TODO: Temporary solution, need to fix.
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
