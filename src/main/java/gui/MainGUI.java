package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.WordConverter;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

public class MainGUI extends Application {
    private static Stage stage;
    private static final EnumMap<Screen, FXMLLoader> screenMap;

    static {
        screenMap = new EnumMap<>(Screen.class);
        for (Screen screen : Screen.values())
            screenMap.put(screen, getFXMLLoader(screen));
    }

    private void initialiseStage() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void loadScreen(Screen screen) {
        FXMLLoader loader = screenMap.get(screen);
        stage.getScene().setRoot(loader.getRoot());
    }

    public static FXMLLoader getFXMLLoader(Screen screen) {
        String screenName = screen.toString().toLowerCase();
        System.out.println(screenName);
        try {
            FXMLLoader loader = new FXMLLoader(new File(
                    "src/main/resources/layout/" + screenName + ".fxml").toURI().toURL());
            Parent root = loader.load();
            // Add the respective css file if it exists.
            if (new File("src/main/resources/css/" + screen + ".css").exists())
                root.getStylesheets().add(new File("src/main/resources/css/" + screenName + ".css").toURI().toString());
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        initialiseStage();
        loadScreen(Screen.MENU);
    }
}
