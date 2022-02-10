package org.example.initProgram;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.App;
import org.example.AppConfiguration.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class InitializeApp implements Initializable {
    public ComboBox languageBox;
    public Button goNext;
    public Text textchoose;

    private Stage stage = new Stage();

    public void show() throws IOException {
        goNext= new Button();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("InitializeScene.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.show();
    }


    public void chooselang(ActionEvent actionEvent) {
    }

    public void GoNext(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goNext.getScene().getWindow();
        stage.close();
        CreateAdminUser createAdminUser = new CreateAdminUser();
        createAdminUser.show();

    }

    private void setnames() {
        goNext.setText(getBundle("bundle").getString("next"));
        textchoose.setText(getBundle("bundle").getString("chooseLang"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.config = new Config();
        Logger logger = LoggerFactory.getLogger(this.getClass());
        languageBox.getItems().add("Polish");
        languageBox.getItems().add("English");
        languageBox.setOnAction((actionEvent -> {
            if(Objects.equals(languageBox.getSelectionModel().getSelectedItem().toString(), "Polish")) {
                logger.info("Language set to Polish");
                App.config.setBundlePL();
                Locale.setDefault(new Locale("pl","PL"));
            } else /*(Objects.equals(languageBox.getSelectionModel().getSelectedItem().toString(), "English")) */{
                logger.info("Language set to English");
                App.config.setBundleENG();
                Locale.setDefault(new Locale("eng","ENG"));
            }
            setnames();
        }));
    }
}
