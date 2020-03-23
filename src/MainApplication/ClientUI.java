package MainApplication;

import CS4B.Messages.ChatroomList;
import MainApplication.Controller.ClientController;
import MessageBusFiles.*;
import MessageBusFiles.InternalWrappers.*;
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
        String messageType = packet.getPacketType();
            if (messageType.equals("ConnectionAttempt")){
                // Send to client
                try{
                    System.out.println("Received ConnectionAttempt");
                    client = new Client();
                    client.addObserver(this);
                    client.setServerConnection((ConnectionAttempt) packet.getPacket());
                    client.main();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(messageType.equals("ChatroomList")){
                clientController.updateChatroomLists((ChatroomList)packet.getPacket());//Tell the controller to update the list
            }
            else if (messageType.equals("SendMessage")){
                System.out.println("Passing message to Client to send");
                client.sendMessage((SendMessage)packet.getPacket());
            }
            else if (messageType.equals("MessageReceived")){
                try {
                    clientController.displayIncomingMessage((MessageReceived) packet.getPacket());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (messageType.equals("OpenChat")){
                System.out.println("Opening new backend chat");
                client.startChatroom(((OpenChat)packet.getPacket()).getChatroomName());
            }
            else if (messageType.equals("DisconnectAttempt")){
                if(client != null) {
                    System.out.println("Client Attempting Disconnect");
                    client.disconnect();
                    client = null;
                }
            }
            else if (messageType.equals("NewChatroom")){
                System.out.println("Crete new chatroom");
            }
    }
}
