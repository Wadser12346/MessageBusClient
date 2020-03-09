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
        System.out.print("Enter a message: ");
        return input.nextLine();
    }

    @Override
    public void run() {
        while (true){
            try {
                DataOutputStream toServer = new DataOutputStream(connection.getOutputStream());
                toServer.writeUTF(getMessage());
                toServer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
