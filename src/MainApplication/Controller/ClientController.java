package MainApplication.Controller;
import CS4B.Messages.ChatroomList;
import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageBusFiles.InternalWrappers.InternalPacket;
import MessageBusFiles.InternalWrappers.OpenChat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ClientController extends Observable implements Observer {
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
      String clientName;

      private ArrayList<ChatroomController> openChats;

      public void initialize(){
            openChats = new ArrayList<>();
            chatroomSelect.getItems().add("New Chatroom");
            //chatroomSelect.getSelectionModel().selectFirst();
          connectButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                  attemptConnection();
                }
          });
          openChatroom.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                      if (chatroomSelect.getValue().equals("New Chatroom")){
                            //TODO: Add functionality to aks user for chatroom name
                      }
                      else{
                            try {
                                  openChatroomWindow(chatroomSelect.getValue());
                            } catch (IOException e) {
                                  e.printStackTrace();
                            }
                      }
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
                        username.setPromptText(user);
                        clientName = user;
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

      private void openChatroomWindow(String chatroomName) throws IOException {
            setChanged();
            notifyObservers(new InternalPacket("OpenChat",new OpenChat(chatroomName)));// Tell client to open a chat on the backend

            FXMLLoader chatroom = new FXMLLoader(getClass().getResource("../FXML/Chatroom.fxml"));
            Parent chatroomWindow = chatroom.load();
            Stage stage = new Stage();
            stage.setTitle(chatroomName);
            stage.setScene(new Scene(chatroomWindow, 600, 400));
            stage.show();
            ChatroomController controller = (ChatroomController)chatroom.getController();
            controller.setChatroomName(chatroomName);
            controller.addObserver(this);
            controller.setUsername(clientName);
            openChats.add(controller);
      }

      @Override
      public void update(Observable o, Object arg) {
            setChanged();
            notifyObservers(arg);
      }
}
