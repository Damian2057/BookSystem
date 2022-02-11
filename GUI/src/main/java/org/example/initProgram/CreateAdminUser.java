package org.example.initProgram;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.App;
import org.example.AppConfiguration.Config;
import org.example.dao.ClassFactory;
import org.example.dao.jdbcmodel.JDBCLoginSystem;
import org.example.model.users.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.util.ResourceBundle.getBundle;

public class CreateAdminUser implements Initializable {
    public PasswordField pass1 = new PasswordField();
    public PasswordField pass2 = new PasswordField();
    public AnchorPane isok;
    public Button conf;
    public PasswordField pass4 = new PasswordField();
    public AnchorPane isok2;
    public PasswordField pass3 = new PasswordField();
    public TextField adminnick;
    public AnchorPane isok3;
    public Text wrongsdatas = new Text();
    private Stage stage;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CreateAdminUser() {
    }

    public void show() throws IOException {
        stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("createAdminUser.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.show();
    }

    public void showGood() throws IOException, InterruptedException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("allgood.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pass2.setDisable(true);
        pass4.setDisable(true);
        wrongsdatas.setText("");
    }

    public void passwprovied(KeyEvent keyEvent) {
        if(!pass1.getText().isEmpty() && pass1.getText().length() > 3) {
            pass2.setDisable(false);
            if(Objects.equals(pass1.getText(), pass2.getText())) {
                isok.getStyleClass().remove("isnotok");
                isok.getStyleClass().add("isok");
            } else {
                isok.getStyleClass().remove("isok");
                isok.getStyleClass().add("isnotok");
            }
        }
    }

    public void passwproviedd(KeyEvent keyEvent) {
        if(Objects.equals(pass1.getText(), pass2.getText())) {
            isok.getStyleClass().remove("isnotok");
            isok.getStyleClass().add("isok");
        } else {
            isok.getStyleClass().remove("isok");
            isok.getStyleClass().add("isnotok");
        }
    }

    public void confirmation(ActionEvent actionEvent) throws Exception {
        if(Objects.equals(pass1.getText(), pass2.getText())
                && Objects.equals(pass3.getText(), pass4.getText())
                && !adminnick.getText().isEmpty() && adminnick.getText().length() > 4) {
            wrongsdatas.setFill(Color.GREEN);
            wrongsdatas.setText(getBundle("bundle").getString("correctdatas"));
            logger.info("Initializing the user and user base");
            try(var system = ClassFactory.getFileSaverSystem("@../../Config/configuration")) {
                    system.write(App.config);
            }
            try(var loginSystem = ClassFactory.getJDBCLoginSystem("jdbc:derby:LoginSystem", "adminnn", "adminnn")) {
                loginSystem.addPersonel(new Admin(adminnick.getText(), pass4.getText(),1));
            }
                showGood();
        } else {
            wrongsdatas.setText(getBundle("bundle").getString("wrongsdatas"));
        }
    }

    public void passwprovieAdminn(KeyEvent keyEvent) {
        if(Objects.equals(pass3.getText(), pass4.getText())) {
            isok3.getStyleClass().remove("isnotok");
            isok3.getStyleClass().add("isok");
        } else {
            isok3.getStyleClass().remove("isok");
            isok3.getStyleClass().add("isnotok");
        }
    }

    public void passwprovieAdmin(KeyEvent keyEvent) {
        if(!pass3.getText().isEmpty() && pass3.getText().length() > 5) {
            pass4.setDisable(false);
            if(Objects.equals(pass3.getText(), pass4.getText())) {
                isok3.getStyleClass().remove("isnotok");
                isok3.getStyleClass().add("isok");
            } else {
                isok3.getStyleClass().remove("isok");
                isok3.getStyleClass().add("isnotok");
            }
        }
    }

    public void nicknameadmin(KeyEvent keyEvent) {
        if (!adminnick.getText().isEmpty() && adminnick.getText().length() > 4) {
            isok2.getStyleClass().remove("isnotok");
            isok2.getStyleClass().add("isok");
        } else {
            isok3.getStyleClass().remove("isok");
            isok3.getStyleClass().add("isnotok");
        }
    }

    public void closeapp(ActionEvent actionEvent) {
        Platform.exit();
    }
}
