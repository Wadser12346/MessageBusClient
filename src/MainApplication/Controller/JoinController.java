package MainApplication.Controller;
import CS4B.Messages.NewChatroom;
import MessageBusFiles.InternalWrappers.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.util.Observable;


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
                addNewChatroom();
            }
        });
    }

    private void addNewChatroom(){
        chatName = chatroomName.getText();
        chatName = chatName.trim();
        if (!chatName.isBlank()) {
            setChanged();
            notifyObservers(new InternalPacket ("NewChatroom", new NewChatroom(chatName)));
        }
    }
}
