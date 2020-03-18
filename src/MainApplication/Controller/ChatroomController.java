package MainApplication.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Observable;

public class ChatroomController extends Observable {
    @FXML
    Button messageEnterButton;

    @FXML
    TextField messageTextField;

    public void initialize(){
        messageEnterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sendMessage();
            }
        });
    }

    public void sendMessage(){
        String message = messageTextField.getText();
        setChanged();
        notifyObservers(message);
    }
}
