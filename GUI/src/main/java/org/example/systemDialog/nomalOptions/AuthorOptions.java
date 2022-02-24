package org.example.systemDialog.nomalOptions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.example.model.Author;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AuthorOptions implements Initializable {

    @FXML
    private Rectangle addbutton;

    @FXML
    private Rectangle addbutton1;

    @FXML
    private TableColumn<Author, LocalDate> birthdaytable = new TableColumn<>();

    @FXML
    private TableColumn<Author, LocalDate> deathdate = new TableColumn<>();

    @FXML
    private TableColumn<Author, String> firsttable = new TableColumn<>();

    @FXML
    private TableColumn<Author, Integer> idtable = new TableColumn<>();

    @FXML
    private TableColumn<Author, String> lasttable = new TableColumn<>();

    @FXML
    private TextField searchfield;

    @FXML
    private TableView<Author> table = new TableView<>();

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
    void addAuthor(MouseEvent event) {

    }

    @FXML
    void modifyAuthor(MouseEvent event) {

    }

    @FXML
    void onsearch(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
