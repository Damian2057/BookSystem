package org.example.systemDialog.nomalOptions;

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
import org.example.dao.Storage.MainStorage;
import org.example.model.Author;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuthorOptions implements Initializable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MainStorage mainStorage = new MainStorage(App.BookURL);

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

    public AuthorOptions() throws Exception {
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
        ArrayList<Author> allAuthors = mainStorage.getAllAuthors();
        ArrayList<Author> authorlist = new ArrayList<>();
        for (Author author :
                allAuthors) {
            if (author.getFirstName().contains(searchfield.getText())
                    || author.getLastName().contains(searchfield.getText())) {
                authorlist.add(author);
            }
        }
        ObservableList<Author> listOfAuthors = FXCollections.observableArrayList(authorlist);
        updateTable(listOfAuthors);
    }

    private void updateTable(ObservableList<Author> list) {
        idtable.setCellValueFactory(new PropertyValueFactory<Author,Integer>("ID"));
        firsttable.setCellValueFactory(new PropertyValueFactory<Author,String>("FirstName"));
        lasttable.setCellValueFactory(new PropertyValueFactory<Author,String>("LastName"));
        birthdaytable.setCellValueFactory(new PropertyValueFactory<Author,LocalDate>("BirthDay"));
        deathdate.setCellValueFactory(new PropertyValueFactory<Author, LocalDate>("DeathDate"));
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Author> listOfAuthors
                = FXCollections.observableArrayList(mainStorage.getAllAuthors());
        updateTable(listOfAuthors);
    }
}
