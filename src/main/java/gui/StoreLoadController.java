package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.GameCharacter;
import logic.StoreLoadSortingBinarySearch;
import java.util.Arrays;

public class StoreLoadController {

    @FXML private VBox box;
    @FXML private TextField characterNameTextField, characteristicsTextField;
    private static String characterName, characteristics;

    @FXML
    public void initialize() {
        characteristicsTextField.setOnAction(e -> {
            characterName = characterNameTextField.getText();
            characteristics = characteristicsTextField.getText();
            box.getChildren().add(getStoreButton());
            characterNameTextField.setDisable(true);
            characteristicsTextField.setDisable(true);
        });
        characterNameTextField.setOnAction(e -> {
            characterName = characterNameTextField.getText();
            box.getChildren().add(getLoadButton());
            characterNameTextField.setDisable(true);
        });
    }

    private Button getStoreButton() {
        Button storeButton = new Button("Store");
        storeButton.setOnAction(e -> box.getChildren().add(showOutput()));
        return storeButton;
    }

    private Button getLoadButton() {
        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> box.getChildren().add(showOutput2()));
        return loadButton;
    }

    private Text showOutput() {
        GameCharacter output = StoreLoadSortingBinarySearch.storeGameCharacterInformation(characterName, readCharacteristicsInput(characteristics));
        return new Text(output.toString());
    }

    private Text showOutput2() {
        GameCharacter output2 = StoreLoadSortingBinarySearch.loadGameCharacterInformation(characterName);
        return new Text(output2.toString());
    }

    private int[] readCharacteristicsInput(String characteristics) {
        return Arrays.stream(characteristics.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
