package MainApplication;

import CS4B.Messages.ChatroomList;
import MainApplication.Controller.ChatroomController;
import MainApplication.Controller.ClientController;
import MessageBusFiles.*;
import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageBusFiles.InternalWrappers.InternalPacket;
import MessageBusFiles.InternalWrappers.SendMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ClientUI extends Application implements Observer {
    Client client;
    ClientController clientController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new Client();
        client.addObserver(this);
        FXMLLoader chat = new FXMLLoader(getClass().getResource("FXML/Client.fxml"));
        Parent mainClient = chat.load();
        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(mainClient));
        primaryStage.show();
        clientController = (ClientController)chat.getController();
        clientController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        InternalPacket packet = (InternalPacket)arg;
            if (packet.getPacketType().equals("ConnectionAttempt")){
                // Send to client
                try{
                    System.out.println("Received ConnectionAttempt");
                    client.setServerConnection((ConnectionAttempt) packet.getPacket());
                    client.main();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(packet.getPacketType().equals("ChatroomList")){
                clientController.updateChatroomLists((ChatroomList)packet.getPacket());//Tell the controller to update the list
            }
            else if (packet.getPacketType().equals("SendMessage")){
                client.sendMessage((SendMessage)packet.getPacket());
            }
    }
}
