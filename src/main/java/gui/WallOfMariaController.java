package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.Utils;
import logic.WallOfMaria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WallOfMariaController {

    @FXML private VBox box;
    @FXML private TextField numberOfLayersTextField;
    private static int numberOfLayers;

    @FXML
    public void initialize() {
        numberOfLayersTextField.setOnAction(e -> {
            numberOfLayers = Integer.parseInt(numberOfLayersTextField.getText());
            for (int i = 1; i <= numberOfLayers; i++)
                box.getChildren().add(getEdgesInputBox(i));
            box.getChildren().add(getSubmitButton());
            numberOfLayersTextField.setDisable(true);
        });
    }

    private HBox getEdgesInputBox(int index) {
        HBox box = new HBox();
        box.getChildren().add(new Text(String.format("Enter number of bricks for layer %d: ", index)));
        box.getChildren().add(new TextField());
        return box;
    }

    private Button getSubmitButton() {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> box.getChildren().add(showOutput(readEdgesInput())));
        return submitButton;
    }

    private Text showOutput(List<List<Integer>> wallStructure) {
        List<Integer> output = WallOfMaria.getWeakestPoints(wallStructure);
        if (output.size() == 1)
            return new Text("Weakest part of the wall is at position " + output.get(0));
        else
            return new Text("Weakest parts of the wall are at position " + Utils.formatList(output));
    }

    private List<List<Integer>> readEdgesInput() {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numberOfLayers; i++) {
            HBox edgesInputBox = (HBox) box.getChildren().get(i);
            TextField edges = (TextField) edgesInputBox.getChildren().get(1);
            list.add(Arrays.stream(edges.getText()
                            .split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList());
        }
        return list;
    }
}
