package org.example.initProgram;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.App;
import org.example.AppConfiguration.Config;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import static java.util.ResourceBundle.getBundle;

public class InitializeApp {
    public ComboBox languageBox;
    public Button goNext;

    private Stage stage;

    private Config config = new Config();

    public void show() throws IOException {
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InitializeScene.fxml"));
        fxmlLoader.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void goNext(ActionEvent actionEvent) throws IOException {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createAdminUser.fxml"));
        fxmlLoader.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void chooselang(ActionEvent actionEvent) {
    }
}
