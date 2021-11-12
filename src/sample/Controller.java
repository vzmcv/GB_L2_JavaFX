package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Date;

public class Controller {

    @FXML
    private TextArea messageArea;
    @FXML
    private TextField messageField;

    private ChatClient client;

    Date date = new Date();

    public Controller() throws IOException {
        client = new ChatClient(this);
    }

    public void SendButton(ActionEvent actionEvent) {
        final String message = messageField.getText().trim();
        if(message.isEmpty()){
            return;
        }

        client.sendMessage(message);
        messageArea.appendText(message + "\nотправленно: " + date.toString() + "\n");
        messageField.clear();
        messageField.requestFocus();

    }

    public void MenuSelectExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addMessage(String message) {
        messageArea.appendText(message + "\n");
    }
}
