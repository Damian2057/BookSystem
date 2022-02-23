package org.example.systemDialog.nomalOptions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.example.App;
import org.example.dao.Storage.MainStorage;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientOptions implements Initializable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MainStorage mainStorage = new MainStorage(App.BookURL);

    @FXML
    private Rectangle addbutton;

    @FXML
    private Rectangle addbutton1;

    @FXML
    private Rectangle addbutton2;

    @FXML
    private TableColumn<Client, String> addresstable = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> emailtable = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> firsttable = new TableColumn<>();

    @FXML
    private TableColumn<Client, Integer> idtable = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> lasttable = new TableColumn<>();

    @FXML
    private TableColumn<Client, String> phonetable = new TableColumn<>();

    @FXML
    private TextField searchfield;

    @FXML
    private TableView<Client> table = new TableView<>();

    public ClientOptions() throws Exception {
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
    void modifyClient(MouseEvent event) {

    }

    @FXML
    void addClient(MouseEvent event) {

    }

    @FXML
    void removeClient(MouseEvent event) {

    }

    @FXML
    void onsearch(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
