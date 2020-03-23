package MainApplication.Controller;
import CS4B.Messages.NewChatroom;
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

public class JoinController extends Observable {

    @FXML
    Button createButton;
    @FXML
    TextField chatroomName;

    private String chatName;

    public void initialize(){
        createButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    chatName = chatroomName.getText();
                    chatName.trim();
                    if (!chatName.isBlank()) {
                        System.out.println("Making new Chatroom.");
                        setChanged();
                        notifyObservers(new InternalPacket ("NewChatroom", new NewChatroom(chatName)));
                    }
                }
            });
    }
}
