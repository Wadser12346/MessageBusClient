package MainApplication;

import MainApplication.Controller.ChatroomController;
import MainApplication.Controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class ChatroomUI extends Application implements Observer {
    private String chatroomName;

    public ChatroomUI(String chatroomName){
        this.chatroomName = chatroomName;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader chat = new FXMLLoader(getClass().getResource("FXML/Chatroom.fxml"));
        Parent mainClient = chat.load();
        primaryStage.setTitle(chatroomName);
        primaryStage.setScene(new Scene(mainClient));
        primaryStage.show();
        ((ChatroomController)chat.getController()).addObserver(this);
    }

    public void setChatroomName(String chatroomName){
        this.chatroomName = chatroomName;
    }

    public void messageReceived(String message){

    }

    @Override
    public void update(Observable o, Object arg) {

    }


}
