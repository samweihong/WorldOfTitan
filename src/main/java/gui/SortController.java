package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.StoreLoadSortingBinarySearch;

import java.lang.reflect.InvocationTargetException;

public class SortController {

    @FXML private VBox box;
    @FXML private TextField sortingTextField;
    private static String sorting;

    @FXML
    public void initialize() {
        sortingTextField.setOnAction(e -> {
            sorting = sortingTextField.getText();
            box.getChildren().add(getSubmitButton());
            sortingTextField.setDisable(true);
        });
    }

    private Button getSubmitButton() {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            try {
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
        String output = StoreLoadSortingBinarySearch.sortingAttribute(sorting);
        return new Text(output);
    }
}
