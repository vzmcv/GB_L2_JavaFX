package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Date;

public class Controller {

    @FXML
    private TextArea messageArea;
    @FXML
    private TextField messageField;

    Date date = new Date();

    public void SendButton(ActionEvent actionEvent) {
        final String message = messageField.getText();
        if(message.isEmpty()){
            return;
        }
        messageArea.appendText(message + "\nотправленно: " + date.toString() + "\n");
        messageField.clear();
        messageField.requestFocus();

    }

    public void MenuSelectExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
