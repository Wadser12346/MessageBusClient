package MainApplication.Controller;
import CS4B.Messages.ChatroomList;
import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageBusFiles.InternalWrappers.InternalPacket;

import MessageBusFiles.InternalWrappers.OpenChat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Observable;

public class ClientController extends Observable {
      @FXML
      Button disconnectButton;
      @FXML
      Button connectButton;
      @FXML
      Button openChatroom;
      @FXML
      TextField serverName;
      @FXML
      TextField portNumber;
      @FXML
      TextField username;

      @FXML
      ComboBox<String> chatroomSelect;

      private ArrayList<ChatroomController> openChats;

      public void initialize(){
            chatroomSelect.getItems().add("New Chatroom");
            //chatroomSelect.getSelectionModel().selectFirst();
          connectButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                  attemptConnection();
                }
          });
      }

      private void attemptConnection(){
            String server = serverName.getText();
            String port = portNumber.getText();
            if (!server.isBlank() && !port.isBlank()){
                  // Get username
                  String user = username.getText();
                  //Check if empty
                  if (user.isBlank()){
                        user = "Anonymous";
                        username.setPromptText("Anonymous");
                  }
                  setChanged();
                  notifyObservers(new InternalPacket("ConnectionAttempt", new ConnectionAttempt(serverName.getText(), portNumber.getText(), user)));
            }
      }

      public void updateChatroomLists(ArrayList<String>chatrooms){
            Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                        System.out.println("Updating Chatroom List");
                        chatroomSelect.getItems().addAll(chatrooms);
                        chatroomSelect.getSelectionModel().selectFirst();
                  }
            });
      }

      public void updateChatroomLists(ChatroomList list){
            updateChatroomLists(list.getChatrooms());
      }

      private void openChatroomWindow(String chatroomName){
            setChanged();
            notifyObservers(new InternalPacket("OpenChat",new OpenChat(chatroomName)));// Tell client to open a chat on the backend


      }
}
