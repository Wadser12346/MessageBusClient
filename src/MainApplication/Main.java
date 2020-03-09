package MainApplication;

import MainApplication.Controller.ClientController;
import MessageBusFiles.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader chatTabs = new FXMLLoader(getClass().getResource("src/MainApplication/FXML/Client.fxml"));
        chatTabs.setController(new ClientController());
        Parent mainClient = chatTabs.load();
        primaryStage.setTitle("Message Bus");
        primaryStage.setScene(new Scene(mainClient, 600, 400));
        primaryStage.show();

//        Thread clientChat = new Thread(new ClientChatroom());
//        clientChat.start();
    }
}
