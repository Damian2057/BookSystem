package org.example.systemDialog.adminOptions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.example.App;
import org.example.dao.ClassFactory;
import org.example.dao.Storage.MainStorage;
import org.example.model.users.Personnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerOptions implements Initializable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    void modifyWorker(MouseEvent event) {

    }

    @FXML
    void addWorker(MouseEvent event) {

    }

    @FXML
    void removeWorker(MouseEvent event) {

    }

    @FXML
    void onsearch(ActionEvent event) {

    }

    public void updateTable(ObservableList<Personnel> list) {
        idtable.setCellValueFactory(new PropertyValueFactory<Personnel,Integer>("ID"));
        nickname.setCellValueFactory(new PropertyValueFactory<Personnel,String>("NickName"));
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println("--------------------");
            ObservableList<Personnel> listOfPersonnel = FXCollections.observableArrayList(ClassFactory.getJDBCLoginSystem(App.LoginURL
            , App.user, App.password).getListofworkers());
            updateTable(listOfPersonnel);
        } catch (Exception e) {
            logger.error("Error while recruiting staff");
        }
    }
}
