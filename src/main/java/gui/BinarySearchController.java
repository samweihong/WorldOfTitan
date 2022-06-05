package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.StoreLoadSortingBinarySearch;

import java.lang.reflect.InvocationTargetException;

public class BinarySearchController {

    @FXML private VBox box;
    @FXML private TextField abilityTextField, valueTextField;
    private static String ability;
    private static int value;

    @FXML
    public void initialize() {
        valueTextField.setOnAction(e -> {
            ability = abilityTextField.getText();
            value = Integer.parseInt(valueTextField.getText());
            box.getChildren().add(getSubmitButton());
            abilityTextField.setDisable(true);
            valueTextField.setDisable(true);
        });
    }

    private Button getSubmitButton() {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            try {
                box.getChildren().add(new Text("Soldier :"));
                box.getChildren().add(showOutput());
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        return submitButton;
    }

    private Text showOutput() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String output = StoreLoadSortingBinarySearch.binarySearch(ability, value);
        return new Text(output);
    }
}
