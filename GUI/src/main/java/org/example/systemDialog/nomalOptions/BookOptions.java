package org.example.systemDialog.nomalOptions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.example.model.Author;
import org.example.model.Book;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookOptions implements Initializable {

    public Rectangle addbutton;
    public Rectangle addbutton1;
    public Rectangle addbutton11;

    @FXML
    private TableColumn<Book, String> authortable;

    @FXML
    private TableColumn<Book, LocalDate> datetable;

    @FXML
    private TableColumn<Book, Integer> idtable;

    @FXML
    private TableColumn<Book, Integer> pagetable;

    @FXML
    private TableColumn<Book, Double> pricetable;

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, String> titletable;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Book> list = FXCollections.observableArrayList(
                new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                        , LocalDate.parse("2001-10-15"))
                        ,LocalDate.parse("2015-10-16"),200,51.50)
        );
        idtable.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ID"));
        titletable.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));

        table.setItems(list);

    }

}
