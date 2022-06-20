package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import logic.WordConverter;

public class WordConverterController {
    @FXML private TextField marleyTextField;
    @FXML private Text paradisText;

    @FXML public void convert(){
        paradisText.setText(WordConverter.convertWord(marleyTextField.getText()));
    }
    @FXML public void back(){
        MainGUI.loadScreen(Screen.MENU);
    }
}
