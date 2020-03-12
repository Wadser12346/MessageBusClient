package MessageBusFiles;


import MessageTypes.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientSend implements Runnable{
    private Socket connection;

    public ClientSend(Socket connection){
        this.connection = connection;
        Thread self = new Thread(this);
        self.start();

    }

    @Override
    public void run() {

    }
}
