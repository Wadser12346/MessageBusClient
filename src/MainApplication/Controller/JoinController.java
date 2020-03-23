package MainApplication.Controller;
import MessageBusFiles.InternalWrappers.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class JoinController extends Observable implements Observer {

    @FXML
    Button createButton;
    @FXML
    TextField chatroomName;

    private ArrayList<ChatroomController> openChats;

    public void initialize(){
        do {
            createButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    exit();
                }
            });
        } while (inUse(chatroomName.getText()));
        updateChatroomList(chatroomName.getText());
    }

    private void exit(){
        setChanged();
        notifyObservers(new InternalPacket("error creating chat", new DisconnectAttempt()));
    }

    private boolean inUse(String name){
        String chatName = chatroomName.getText();
        for (ChatroomController chatroom : openChats) {
            if (chatroom.getChatroomName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void updateChatroomList(String chatroom){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Updating Chatroom List");
                ChatroomController chatroomController = new ChatroomController();
                chatroomController.setChatroomName(chatroom);
                openChats.add(chatroomController);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
        System.out.println("message from JoinController");
    }
}
