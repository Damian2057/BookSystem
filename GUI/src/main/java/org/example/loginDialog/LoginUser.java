package org.example.loginDialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.App;
import org.example.AppConfiguration.Config;
import org.example.Exceptions.Dao.WrongLoginDataException;
import org.example.dao.ClassFactory;
import org.example.dao.usersManager.LoginStorage;
import org.example.model.users.Admin;
import org.example.model.users.Personnel;
import org.example.model.users.Worker;
import org.example.systemDialog.AdminOptionWindow;
import org.example.systemDialog.WorkerOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class LoginUser implements Initializable {

    public Text date = new Text();
    public Text logininform = new Text();
    public TextField nickname = new TextField();
    public PasswordField pass = new PasswordField();
    public Button loginbutton;
    public AnchorPane loginpane;
    public Button goNext;
    public ComboBox languageBox = new ComboBox();
    public PasswordField oldpass;
    public PasswordField newPass;
    public PasswordField newPass2;
    public Text oldfield;
    public Text newfield;
    public AnchorPane isok3;
    private String password;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text textchoose;

    public PasswordField pinpass;

    public static Personnel loggedPersonnel;

    public void show() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("loginScene.fxml"),getBundle("bundle", Locale.getDefault()));
        loginpane.getChildren().setAll(pane);
    }

    public void onpinType(KeyEvent keyEvent) throws Exception {
        if(Objects.equals(pinpass.getText(), password)) {
            show();
        }
    }

    public void entryPin() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("generalPin.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setText(String.valueOf(LocalDate.now()));
        try {
            password = ClassFactory
                    .getFileSaverSystem("@../../Config/configuration")
                    .read().getAppGeneralPassword();
        } catch (Exception e) {
            logger.error("error occurred");
        }

        try {
            var system = ClassFactory.getFileSaverSystem("@../../Config/configuration");
            var config = ClassFactory.getFileSaverSystem("@../../Config/configuration").read();
            Logger logger = LoggerFactory.getLogger(this.getClass());
            languageBox.getItems().add("Polish");
            languageBox.getItems().add("English");
            languageBox.setOnAction((actionEvent -> {
                if(Objects.equals(languageBox.getSelectionModel().getSelectedItem().toString(), "Polish")) {
                    logger.info("Language set to Polish");
                    config.setBundlePL();
                    Locale.setDefault(new Locale("pl","PL"));
                } else /*(Objects.equals(languageBox.getSelectionModel().getSelectedItem().toString(), "English")) */{
                    logger.info("Language set to English");
                    config.setBundleENG();
                    Locale.setDefault(new Locale("eng","ENG"));
                }
                try {
                    system.write(config);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setnames();
            }));
            newPass.setDisable(true);
            newPass2.setDisable(true);
        } catch (Exception e) {
            logger.error("error occurred");
        }
    }

    public void login(ActionEvent actionEvent) {
        try(var loginSystem= ClassFactory.getJDBCLoginSystem(App.LoginURL,App.user,App.password)) {
            Personnel personnel = loginSystem.loginPersonel(nickname.getText(),pass.getText());
            logger.info("Login to the System ID:{} ,nickname:{}", personnel.getID(),personnel.getNickName());
            Stage stage = (Stage) loginbutton.getScene().getWindow();
            stage.close();
            if(personnel.getPermLevel() == 1) {
                LoginUser.loggedPersonnel = (Admin) personnel;
                AdminOptionWindow adminOptionWindow = new AdminOptionWindow();
                adminOptionWindow.show();
            } else {
                LoginUser.loggedPersonnel = (Worker) personnel;
                WorkerOptionWindow workerOptionWindow = new WorkerOptionWindow();
                workerOptionWindow.show();
            }
        } catch (WrongLoginDataException e) {
            logger.info("failed attempt to log into the system");
            logininform.setText(getBundle("bundle").getString("loginError"));
            nickname.setText("");
            pass.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error occurred");
        }
    }


    public void chooselang(ActionEvent actionEvent) {
    }

    public void GoNext(ActionEvent actionEvent) throws Exception {
        if(Objects.equals(newPass.getText(), newPass2.getText())
                && Objects.equals(oldpass.getText(), LoginUser.loggedPersonnel.getPassword())) {
            logger.info("USER: {}, change password",LoginUser.loggedPersonnel.getID());
            LoginStorage loginStorage = new LoginStorage(App.LoginURL,App.user,App.password);
            loginStorage.updatePersonnel(LoginUser.loggedPersonnel.getID(),newPass.getText(),"Password");
        }
        Stage stage = (Stage) goNext.getScene().getWindow();
        stage.close();
    }


    private void setnames() {
        goNext.setText(getBundle("bundle").getString("next"));
        textchoose.setText(getBundle("bundle").getString("chooseLang"));
        oldfield.setText(getBundle("bundle").getString("oldPass"));
        newfield.setText(getBundle("bundle").getString("newPass"));
    }

    public void oldpassType(KeyEvent keyEvent) {
        if(Objects.equals(oldpass.getText(), loggedPersonnel.getPassword())) {
            newPass.setDisable(false);
        }
    }

    public void newpassType(KeyEvent keyEvent) {
        if(!newPass.getText().isEmpty() && newPass.getText().length() > 5) {
            newPass2.setDisable(false);
            if(Objects.equals(newPass.getText(), newPass2.getText())) {
                isok3.getStyleClass().remove("isnotok");
                isok3.getStyleClass().add("isok");
            } else {
                isok3.getStyleClass().remove("isok");
                isok3.getStyleClass().add("isnotok");
            }
        }
    }

    public void newpassTypee(KeyEvent keyEvent) {
        if(Objects.equals(newPass.getText(), newPass2.getText())) {
            isok3.getStyleClass().remove("isnotok");
            isok3.getStyleClass().add("isok");
        } else {
            isok3.getStyleClass().remove("isok");
            isok3.getStyleClass().add("isnotok");
        }
    }
}
