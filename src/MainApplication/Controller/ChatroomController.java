package MainApplication.Controller;

import CS4B.Messages.ChatMessage;
import CS4B.Messages.Packet;
import CS4B.Messages.StringMessage;
import MessageBusFiles.InternalWrappers.InternalPacket;
import MessageBusFiles.InternalWrappers.SendMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Observable;

public class ChatroomController extends Observable {
    @FXML
    Button messageEnterButton;

    @FXML
    TextField messageTextField;

    @FXML
    TextField userNameTextBox;

    @FXML
    TextField chatroomNameTextBox;

    @FXML
    TextArea textDisplayArea;

    String chatroomName;
    String username;

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
        notifyObservers(new InternalPacket("SendMessage" ,new SendMessage(chatroomName, username, new ChatMessage(new StringMessage(message)))));
    }

    public void displayReceivedMessage(ChatMessage message){
        textDisplayArea.appendText(message.getStringMessage().toString());
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
        chatroomNameTextBox.setText(chatroomName);
    }

    public void setUsername(String username) {
        this.username = username;
        userNameTextBox.setText(username);
    }
}
