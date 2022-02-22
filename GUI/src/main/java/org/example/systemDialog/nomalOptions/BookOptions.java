package org.example.systemDialog.nomalOptions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.dao.Storage.MainStorage;
import org.example.model.Author;
import org.example.model.Book;
import org.example.systemDialog.AdminOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class BookOptions implements Initializable {

    public Rectangle addbutton;
    public Rectangle addbutton1;
    public Rectangle addbutton11;

    public Button addoption;
    public Button canceloption;
    public Button exitoption;
    public TextField titlefield;
    public ComboBox authorbox;
    public TextField pagefield;
    public TextField pricefield;
    public ChoiceBox monthbox;
    public ChoiceBox yearbox;
    public ChoiceBox daybox;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MainStorage mainStorage = new MainStorage(App.BookURL);

    @FXML
    private TableColumn<Book, String> authortable = new TableColumn<>();

    @FXML
    private TableColumn<Book, LocalDate> datetable = new TableColumn<>();

    @FXML
    private TableColumn<Book, Integer> idtable = new TableColumn<>();

    @FXML
    private TableColumn<Book, Integer> pagetable = new TableColumn<>();

    @FXML
    private TableColumn<Book, Double> pricetable = new TableColumn<>();

    @FXML
    private TableView<Book> table = new TableView<>();

    @FXML
    private TableColumn<Book, String> titletable = new TableColumn<>();

    public BookOptions() throws Exception {
    }

    public void hoverin(MouseEvent mouseEvent) {
        addbutton.getStyleClass().remove("onExit");
        addbutton.getStyleClass().add("onEnter");
    }

    public void hoverout(MouseEvent mouseEvent) {
        addbutton.getStyleClass().remove("onEnter");
        addbutton.getStyleClass().add("onExit");
    }

    public void hoverinn(MouseEvent mouseEvent) {
        addbutton1.getStyleClass().remove("onExit");
        addbutton1.getStyleClass().add("onEnter");
    }

    public void hoveroutt(MouseEvent mouseEvent) {
        addbutton1.getStyleClass().remove("onEnter");
        addbutton1.getStyleClass().add("onExit");
    }

    public void hoverinnn(MouseEvent mouseEvent) {
        addbutton11.getStyleClass().remove("onExit");
        addbutton11.getStyleClass().add("onEnter");
    }

    public void hoverouttt(MouseEvent mouseEvent) {
        addbutton11.getStyleClass().remove("onEnter");
        addbutton11.getStyleClass().add("onExit");
    }

    public void updateTable() {
        ObservableList<Book> list = FXCollections.observableArrayList(mainStorage.getAllBooks());
        idtable.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ID"));
        titletable.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        authortable.setCellValueFactory(new PropertyValueFactory<Book,String>("FullName"));
        datetable.setCellValueFactory(new PropertyValueFactory<Book,LocalDate>("publishDate"));
        pagetable.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pageCount"));
        pricetable.setCellValueFactory(new PropertyValueFactory<Book,Double>("Price"));
        table.setItems(list);
    }



    public void addbook(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addStage == null) {
            AdminOptionWindow.addStage = new Stage();
            AdminOptionWindow.addStage.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.addStage.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("addBookOption.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.addStage.setScene(scene);
            AdminOptionWindow.addStage.show();
        }
    }

    public void modifyBook(MouseEvent event) {

    }

    public void removeBook(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    public void onsearch(ActionEvent actionEvent) throws Exception {
        //mainStorage.addBook("Titanic",1,LocalDate.parse("2001-10-15"),200,5.5);
    }

    public void onadd(ActionEvent actionEvent) {
    }

    public void oncancel(ActionEvent actionEvent) {
        AdminOptionWindow.addStage.close();
        AdminOptionWindow.addStage = null;
    }

    public void onexit(ActionEvent actionEvent) {
        AdminOptionWindow.addStage.close();
        AdminOptionWindow.addStage = null;
    }
}
