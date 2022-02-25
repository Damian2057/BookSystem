package org.example.systemDialog.nomalOptions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OderOptions implements Initializable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MainStorage mainStorage = new MainStorage(App.BookURL);

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
    private TableColumn<Order, String> fullnametable  = new TableColumn<>();

    @FXML
    private TableView<Order> table = new TableView<>();

    public OderOptions() throws Exception {
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
        ArrayList<Order> allOrders = mainStorage.getAllOrders();
        ArrayList<Order> orderList = new ArrayList<>();
        for (Order order :
                allOrders) {
            if (String.valueOf(order.getClientID()).contains(searchfield.getText())
                    || order.getClientfullName().contains(searchfield.getText())) {
                orderList.add(order);
            }
        }
        ObservableList<Order> listOfOrders = FXCollections.observableArrayList(orderList);
        updateTable(listOfOrders);
    }

    public void updateTable(ObservableList<Order> list) {
        idtable.setCellValueFactory(new PropertyValueFactory<Order,Integer>("ID"));
        clientID.setCellValueFactory(new PropertyValueFactory<Order,Integer>("ClientID"));
        fullnametable.setCellValueFactory(new PropertyValueFactory<Order,String>("ClientfullName"));
        StartDate.setCellValueFactory(new PropertyValueFactory<Order,LocalDate>("StartReservationdate"));
        EndDate.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("EndReservationDate"));
        table.setItems(list);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue, Order order, Order t1) {
                ObservableList<Book> listOfBooks
                        = FXCollections.observableArrayList(observableValue.getValue().getListofBooks());
                bookIDtable.setCellValueFactory(new PropertyValueFactory<Book, Integer>("ID"));
                booktitleTable.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
                bookTable.setItems(listOfBooks);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Order> listOfClients
                = FXCollections.observableArrayList(mainStorage.getAllOrders());
        updateTable(listOfClients);
    }
}
