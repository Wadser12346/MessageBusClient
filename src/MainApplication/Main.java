package MainApplication;

import MessageBusFiles.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread clientChat = new Thread(new ClientChatroom());
        clientChat.start();
    }
}
