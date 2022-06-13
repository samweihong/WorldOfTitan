package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import logic.WordConverter;

public class WordConverterController {
    @FXML private TextField marleyTextField;
    @FXML private Text paradisText;

    @FXML public void convert(){
        paradisText.setText(WordConverter.convertWord(marleyTextField.getText()));
    }
}
