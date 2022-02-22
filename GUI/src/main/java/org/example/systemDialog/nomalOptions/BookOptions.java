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
    public ComboBox authorbox = new ComboBox();
    public TextField pagefield;
    public TextField pricefield;
    public ComboBox monthbox = new ComboBox();
    public ComboBox yearbox = new ComboBox();
    public ComboBox daybox = new ComboBox();

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
            AdminOptionWindow.addStage.setOnHidden(windowEvent -> {
                System.out.println("closee");
                updateTable();
            });
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
        authorbox.setVisibleRowCount(7);
        daybox.setVisibleRowCount(7);
        monthbox.setVisibleRowCount(7);
        yearbox.setVisibleRowCount(7);

        for (int i = 1; i < 32; i++) {
            daybox.getItems().add(i);
        }

        for (int i = 1; i < 13; i++) {
            monthbox.getItems().add(i);
        }

        for (int i = 500; i < LocalDate.now().getYear()+6; i++) {
            yearbox.getItems().add(i);
        }

        var list = mainStorage.getAllAuthors();
        for (int i = 0; i < list.size(); i++) {
            authorbox.getItems().add(list.get(i).fullName());
        }
        updateTable();
    }

    public void onsearch(ActionEvent actionEvent) throws Exception {
        //mainStorage.addBook("Titanic",1,LocalDate.parse("2001-10-15"),200,5.5);
    }

    public void onadd(ActionEvent actionEvent) {
        String year;
        String month;
        String day;
        if(Integer.parseInt(yearbox.getSelectionModel().getSelectedItem().toString()) < 1000) {
            year = "0" + yearbox.getSelectionModel().getSelectedItem().toString();
        } else {
            year = yearbox.getSelectionModel().getSelectedItem().toString();
        }
        if(Integer.parseInt(monthbox.getSelectionModel().getSelectedItem().toString()) < 10) {
            month = "0" + monthbox.getItems().get(monthbox.getSelectionModel().getSelectedIndex()).toString();
        } else {
            month = monthbox.getItems().get(monthbox.getSelectionModel().getSelectedIndex()).toString();
        }
        if(Integer.parseInt(daybox.getSelectionModel().getSelectedItem().toString()) < 10) {
            day = "0" + daybox.getItems().get(daybox.getSelectionModel().getSelectedIndex()).toString();
        } else {
            day = daybox.getItems().get(daybox.getSelectionModel().getSelectedIndex()).toString();
        }

        try {

            mainStorage.addBook(titlefield.getText()
                    , mainStorage.getAuthorStorage().getAuthorIDbydata(authorbox.getSelectionModel().getSelectedItem().toString())
                    ,LocalDate.parse(year+"-"+month+"-"+day)
                    ,Integer.parseInt(pagefield.getText())
                    ,Double.parseDouble(pricefield.getText()));

            AdminOptionWindow.addStage.close();
            AdminOptionWindow.addStage = null;
        } catch (Exception e) {
            logger.error("Error during book addition");
        }

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
