package MainApplication;

import MainApplication.Controller.ChatroomController;
import MainApplication.Controller.ClientController;
import MessageBusFiles.*;
import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageBusFiles.InternalWrappers.InternalPacket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ClientUIMain extends Application implements Observer {
    Client client;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new Client();
        FXMLLoader chat = new FXMLLoader(getClass().getResource("FXML/Client.fxml"));
        Parent mainClient = chat.load();
        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(mainClient));
        primaryStage.show();
        ((ClientController)chat.getController()).addObserver(this);
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
    }
}
