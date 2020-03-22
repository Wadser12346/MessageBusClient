package MainApplication.Controller;


import CS4B.Messages.*;

import MessageBusFiles.InternalWrappers.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
        System.out.println("Sending Message");
        String message = messageTextField.getText();
        messageTextField.clear();
        setChanged();
        notifyObservers(new InternalPacket("SendMessage" ,new SendMessage(chatroomName, username, new ChatMessage(new StringMessage(message)))));
    }

    public void displayReceivedMessage(MessageReceived message) throws IOException {
        StringMessage text = message.getMessage().getStringMessage();
        PictureMessage picture = message.getMessage().getPictureMessage();
        if (text != null)
            textDisplayArea.appendText(message.toString() + "\n");

        if (picture != null){
            FXMLLoader imageView = new FXMLLoader(getClass().getResource("../FXML/ImageViewer.fxml"));
            Parent imageViewerWindow = imageView.load();
            Stage stage = new Stage();
            stage.setTitle("Incoming Image");
            stage.setScene(new Scene(imageViewerWindow, 600, 600));
            stage.show();
            ((ImageViewerController)imageView.getController()).displayImage(picture.getPicture());
        }
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
