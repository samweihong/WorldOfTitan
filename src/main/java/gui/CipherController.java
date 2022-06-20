package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.WordConverter;

public class CipherController {
    @FXML private TextField textField;
    @FXML private Text resultText;

    @FXML public void encrypt(){
        resultText.setText(WordConverter.encryptText(textField.getText(), "marley"));
    }

    @FXML public void decrypt(){
        resultText.setText(WordConverter.decryptText(textField.getText(), "marley"));
    }
}
