package org.example.systemDialog.adminOptions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.Exceptions.Dao.AdminInSystemException;
import org.example.Exceptions.Dao.ObjectsDependentException;
import org.example.Exceptions.Dao.WrongLoginDataException;
import org.example.dao.ClassFactory;
import org.example.dao.usersManager.LoginStorage;
import org.example.model.Author;
import org.example.model.Client.Client;
import org.example.model.users.Personnel;
import org.example.systemDialog.AdminOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class WorkerOptions implements Initializable {

    public TextField nickfield;
    public PasswordField passField;
    public PasswordField passField2 = new PasswordField();

    public ComboBox idBox = new ComboBox();
    public TextField Nicknamefield = new TextField();

    public Text Adminerror = new Text();
    public Text Workersucce = new Text();
    public Button removeOption;

    public TextField NicknameToEdit;
    public PasswordField pass1;
    public PasswordField pass2 = new PasswordField();
    public AnchorPane isok;
    public AnchorPane isok2;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private LoginStorage loginStorage = new LoginStorage(App.LoginURL,App.user,App.password);

    @FXML
    private Rectangle addbutton;

    @FXML
    private Rectangle addbutton1;

    @FXML
    private Rectangle addbutton2;

    @FXML
    private TableColumn<Personnel, Integer> idtable = new TableColumn<>();

    @FXML
    private TableColumn<Personnel, String> nickname = new TableColumn<>();

    @FXML
    private TextField searchfield;

    @FXML
    private TableView<Personnel> table = new TableView<>();

    public WorkerOptions() throws Exception {
    }

    @FXML
    void hoverin(MouseEvent event) {
        addbutton.getStyleClass().remove("onExit");
        addbutton.getStyleClass().add("onEnter");
    }

    @FXML
    void hoverinn(MouseEvent event) {
        addbutton1.getStyleClass().remove("onExit");
        addbutton1.getStyleClass().add("onEnter");
    }

    @FXML
    void hoverinnn(MouseEvent event) {
        addbutton2.getStyleClass().remove("onExit");
        addbutton2.getStyleClass().add("onEnter");
    }

    @FXML
    void hoverout(MouseEvent event) {
        addbutton.getStyleClass().remove("onEnter");
        addbutton.getStyleClass().add("onExit");
    }

    @FXML
    void hoveroutt(MouseEvent event) {
        addbutton1.getStyleClass().remove("onEnter");
        addbutton1.getStyleClass().add("onExit");
    }

    @FXML
    void hoverouttt(MouseEvent event) {
        addbutton2.getStyleClass().remove("onEnter");
        addbutton2.getStyleClass().add("onExit");
    }


    @FXML
    void modifyWorker(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addWorker == null && AdminOptionWindow.modifyWorker == null
                && AdminOptionWindow.removeWorker == null) {
            AdminOptionWindow.modifyWorker = new Stage();
            AdminOptionWindow.modifyWorker.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.modifyWorker.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("modifyWorker.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.modifyWorker.setScene(scene);
            AdminOptionWindow.modifyWorker.show();
        }
    }

    @FXML
    void addWorker(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addWorker == null && AdminOptionWindow.modifyWorker == null
                && AdminOptionWindow.removeWorker == null) {
            AdminOptionWindow.addWorker = new Stage();
            AdminOptionWindow.addWorker.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.addWorker.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("addWorker.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.addWorker.setScene(scene);
            AdminOptionWindow.addWorker.show();
        }
    }

    @FXML
    void removeWorker(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addWorker == null && AdminOptionWindow.modifyWorker == null
                && AdminOptionWindow.removeWorker == null) {
            AdminOptionWindow.removeWorker = new Stage();
            AdminOptionWindow.removeWorker.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.removeWorker.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("removeWorker.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.removeWorker.setScene(scene);
            AdminOptionWindow.removeWorker.show();
        }
    }

    @FXML
    void onsearch(ActionEvent event) {
        ArrayList<Personnel> allPersonnel = loginStorage.getAllElementsFromStorage();
        ArrayList<Personnel> personnelArrayList = new ArrayList<>();
        for (Personnel personnel :
                allPersonnel) {
            if (personnel.getNickName().contains(searchfield.getText())
                    || String.valueOf(personnel.getID()).contains(searchfield.getText())) {
                personnelArrayList.add(personnel);
            }
        }
        ObservableList<Personnel> listOfPersonnel = FXCollections.observableArrayList(personnelArrayList);
        updateTable(listOfPersonnel);
    }

    public void updateTable(ObservableList<Personnel> list) {
        idtable.setCellValueFactory(new PropertyValueFactory<Personnel,Integer>("ID"));
        nickname.setCellValueFactory(new PropertyValueFactory<Personnel,String>("NickName"));
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Personnel> listOfPersonnel = FXCollections.observableArrayList(ClassFactory.getJDBCLoginSystem(App.LoginURL
            , App.user, App.password).getListofworkers());
            updateTable(listOfPersonnel);
        } catch (Exception e) {
            logger.error("Error while recruiting staff");
        }
        Nicknamefield.setDisable(true);
        idBox.setVisibleRowCount(7);
        var listPersonnel = loginStorage.getAllElementsFromStorage();
        for (int i = 0; i < listPersonnel.size(); i++) {
            idBox.getItems().add(listPersonnel.get(i).getID());
        }
        Adminerror.setVisible(false);
        Workersucce.setVisible(false);
        pass2.setDisable(true);
        passField2.setDisable(true);

    }

    public void onadd(ActionEvent actionEvent) {
        if(nickfield.getText().length() > 4 && passField.getText().length() > 5
                && passField2.getText().length() > 5
                && Objects.equals(passField.getText(), passField2.getText()))
        try {
            if(Objects.equals(passField.getText(), passField2.getText())) {
                loginStorage.addPersonnel(nickfield.getText(),passField.getText());
                AdminOptionWindow.addWorker.close();
                AdminOptionWindow.addWorker = null;
            }
        } catch (Exception e) {
            throw new WrongLoginDataException();
        }
    }

    public void oncancel(ActionEvent actionEvent) {
        AdminOptionWindow.addWorker.close();
        AdminOptionWindow.addWorker = null;
    }

    public void onexit(ActionEvent actionEvent) {
        AdminOptionWindow.addWorker.close();
        AdminOptionWindow.addWorker = null;
    }

    public void ondelete(ActionEvent actionEvent) throws Exception {
        try {
            removeOption.setDisable(true);
            loginStorage.removePersonnel(Integer.parseInt(idBox.getValue().toString()));
            Workersucce.setVisible(true);
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    AdminOptionWindow.removeWorker.close();
                    AdminOptionWindow.removeWorker = null;
                }
            });
            new Thread(sleeper).start();

        } catch (AdminInSystemException e) {
            removeOption.setDisable(false);
            Workersucce.setVisible(false);
            Adminerror.setVisible(true);
        } catch (Exception e ) {
            removeOption.setDisable(false);
            logger.error("No ID selected");
        }
    }

    public void oncancelR(ActionEvent actionEvent) {
        AdminOptionWindow.removeWorker.close();
        AdminOptionWindow.removeWorker = null;
    }

    public void onexitR(ActionEvent actionEvent) {
        AdminOptionWindow.removeWorker.close();
        AdminOptionWindow.removeWorker = null;
    }

    public void onIDSelectedR(ActionEvent actionEvent) {
        Workersucce.setVisible(false);
        Adminerror.setVisible(false);
        Personnel temp = loginStorage.getPersonnel(Integer.parseInt(idBox.getValue().toString()));
        Nicknamefield.setText(temp.getNickName());
    }

    public void onUpdate(ActionEvent actionEvent) throws Exception {
        try {
            if (Objects.equals(pass2.getText(), pass1.getText()) && pass2.getText().length() > 5) {
                loginStorage.updatePersonnel(Integer.parseInt(idBox.getValue().toString())
                        , NicknameToEdit.getText(), "nickName");
                loginStorage.updatePersonnel(Integer.parseInt(idBox.getValue().toString())
                        , pass2.getText(), "Password");

                AdminOptionWindow.modifyWorker.close();
                AdminOptionWindow.modifyWorker = null;
            }
        } catch (Exception e){
            logger.error("No ID selected");
        }

    }

    public void oncancelM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyWorker.close();
        AdminOptionWindow.modifyWorker = null;
    }

    public void onexitM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyWorker.close();
        AdminOptionWindow.modifyWorker = null;
    }

    public void onIDSelected(ActionEvent actionEvent) {
        isok.getStyleClass().clear();
        Personnel temp = loginStorage.getPersonnel(Integer.parseInt(idBox.getValue().toString()));
        NicknameToEdit.setText(temp.getNickName());
        pass1.setText(temp.getPassword());
        pass2.setText(temp.getPassword());
    }

    public void passwproviedd(KeyEvent keyEvent) {
        if(!pass1.getText().isEmpty() && pass1.getText().length() > 5) {
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

    public void passwprovied(KeyEvent keyEvent) {
        if(Objects.equals(pass1.getText(), pass2.getText())) {
            isok.getStyleClass().remove("isnotok");
            isok.getStyleClass().add("isok");
        } else {
            isok.getStyleClass().remove("isok");
            isok.getStyleClass().add("isnotok");
        }
    }

    public void passwprovieddA(KeyEvent keyEvent) {
        if(!passField.getText().isEmpty() && passField.getText().length() > 5) {
            passField2.setDisable(false);
            if(Objects.equals(passField.getText(), passField2.getText())) {
                isok2.getStyleClass().remove("isnotok");
                isok2.getStyleClass().add("isok");
            } else {
                isok2.getStyleClass().remove("isok");
                isok2.getStyleClass().add("isnotok");
            }
        }
    }

    public void passwprovieddAA(KeyEvent keyEvent) {
        if(Objects.equals(passField.getText(), passField2.getText())) {
            isok2.getStyleClass().remove("isnotok");
            isok2.getStyleClass().add("isok");
        } else {
            isok2.getStyleClass().remove("isok");
            isok2.getStyleClass().add("isnotok");
        }
    }
}
