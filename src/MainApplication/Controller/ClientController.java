package MainApplication.Controller;
import MessageBusFiles.InternalWrappers.ConnectionAttempt;
import MessageBusFiles.InternalWrappers.InternalPacket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

      public void initialize(){
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
}
