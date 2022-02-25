package org.example.systemDialog.nomalOptions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.example.model.Book;
import org.example.model.Order;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OderOptions implements Initializable {

    @FXML
    private Rectangle addbutton;

    @FXML
    private Rectangle addbutton1;

    @FXML
    private Rectangle addbutton2;

    @FXML
    private TableColumn<Book, Integer> bookIDtable;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> booktitleTable;


    @FXML
    private TableColumn<Order, Integer> clientID = new TableColumn<>();

    @FXML
    private TableColumn<Order, Integer> idtable = new TableColumn<>();

    @FXML
    private TextField searchfield;

    @FXML
    private TableColumn<Order, LocalDate> EndDate  = new TableColumn<>();

    @FXML
    private TableColumn<Order, LocalDate> StartDate  = new TableColumn<>();

    @FXML
    private TableView<Order> table = new TableView<>();

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
    void createOrder(MouseEvent event) {

    }

    @FXML
    void endOrder(MouseEvent event) {

    }

    @FXML
    void modifyOrder(MouseEvent event) {

    }

    @FXML
    void onsearch(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
