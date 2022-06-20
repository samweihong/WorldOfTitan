package gui;

import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void toWordConverter(){
        MainGUI.loadScreen(Screen.WORD_CONVERTER);
    }

    @FXML
    public void toWallOfMaria(){
        MainGUI.loadScreen(Screen.WALL_OF_MARIA);
    }

    @FXML
    public void toStoreLoad(){
        MainGUI.loadScreen(Screen.STORE_LOAD);
    }
}
