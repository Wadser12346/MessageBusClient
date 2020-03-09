package MainApplication;

import MainApplication.Controller.ClientController;
import MessageBusFiles.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader chatTabs = new FXMLLoader(getClass().getResource("src/MainApplication/FXML/Client.fxml"));
        chatTabs.setController(new ClientController());
        primaryStage.setTitle("Message Bus");


//        Thread clientChat = new Thread(new ClientChatroom());
//        clientChat.start();
    }
}
