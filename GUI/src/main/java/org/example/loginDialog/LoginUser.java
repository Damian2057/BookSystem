package org.example.loginDialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.Exceptions.Dao.WrongLoginDataException;
import org.example.dao.ClassFactory;
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
    private String password;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PasswordField pinpass;

    public void show() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("loginScene.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.show();
    }

    public void onpinType(KeyEvent keyEvent) throws Exception {
        if(Objects.equals(pinpass.getText(), password)) {
            Stage stage = (Stage) pinpass.getScene().getWindow();
            stage.close();
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
    }

    public void login(ActionEvent actionEvent) {
        try(var loginSystem= ClassFactory.getJDBCLoginSystem("jdbc:derby:LoginSystem", "adminnn", "adminnn")) {
            Personnel personnel = loginSystem.loginPersonel(nickname.getText(),pass.getText());
            logger.info("Login to the System ID:{} ,nickname:{}", personnel.getID(),personnel.getNickName());
            Stage stage = (Stage) loginbutton.getScene().getWindow();
            stage.close();
            if(personnel.getPermLevel() == 1) {
                Admin admin = (Admin) personnel;
                AdminOptionWindow adminOptionWindow = new AdminOptionWindow();
                adminOptionWindow.show();

            } else {
                Worker worker = (Worker) personnel;
                WorkerOptionWindow workerOptionWindow = new WorkerOptionWindow();

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


}
