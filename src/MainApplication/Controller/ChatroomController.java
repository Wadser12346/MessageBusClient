package MainApplication.Controller;


import CS4B.Messages.*;

import MessageBusFiles.InternalWrappers.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

public class ChatroomController extends Observable {
    @FXML
    private Button messageEnterButton;

    @FXML
    private TextField messageTextField;

    @FXML
    private TextField userNameTextBox;

    @FXML
    private TextField chatroomNameTextBox;

    @FXML
    private TextArea textDisplayArea;

    @FXML
    private Button attachButton;

    private String chatroomName;
    private String username;

    private Image image;

    public void initialize(){
        this.image = null;
        messageEnterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sendMessage();
            }
        });

        attachButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    image = attachImage();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Image attachImage() throws FileNotFoundException {
        Stage selectFile = new Stage();
        selectFile.setTitle("Select Image");
        FileChooser fileChooser = new FileChooser();
        File newImage = fileChooser.showOpenDialog(selectFile);
        FileInputStream in = new FileInputStream(newImage);
        return new Image(in);
    }

    public void sendMessage(){
        System.out.println("Sending Message");
        String message = messageTextField.getText();
        messageTextField.clear();
        setChanged();
        notifyObservers(new InternalPacket("SendMessage" ,new SendMessage(chatroomName, username, new ChatMessage(new StringMessage(message), new PictureMessage(image)))));
        image = null;
    }

    public void displayReceivedMessage(MessageReceived message) throws IOException {
        StringMessage text = message.getMessage().getStringMessage();
        PictureMessage picture = message.getMessage().getPictureMessage();
        if (text != null)
            textDisplayArea.appendText(message.toString() + "\n");

        if (picture != null){
            FXMLLoader imageView = new FXMLLoader(getClass().getResource("../FXML/ImageViewer.fxml"));
            Parent imageViewerWindow = imageView.load();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Stage stage = new Stage();
                    stage.setTitle("Incoming Image");
                    stage.setScene(new Scene(imageViewerWindow, 600, 600));
                    stage.show();
                    ((ImageViewerController)imageView.getController()).displayImage(picture.getPicture());
                }
            });
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
