package org.example.systemDialog.nomalOptions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.dao.Storage.MainStorage;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.example.systemDialog.AdminOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class OrderOptions implements Initializable {

    public TextField onsearchclient;
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

    public OrderOptions() throws Exception {
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
    void createOrder(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addOrder == null && AdminOptionWindow.modifyOrder == null
                && AdminOptionWindow.removeOrder == null) {
            AdminOptionWindow.addOrder = new Stage();
            AdminOptionWindow.addOrder.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.addOrder.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("addOrder.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.addOrder.setScene(scene);
            AdminOptionWindow.addOrder.show();
        }
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

    public void updateClients(ObservableList<Client> list) {
        clientIDinOrder.setCellValueFactory(new PropertyValueFactory<Client,Integer>("ID"));
        fullNameInOrder.setCellValueFactory(new PropertyValueFactory<Client,String>("FullName"));
        clientTableA.setItems(list);
        clientTableA.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observableValue, Client order, Client t1) {
                selectedclient = observableValue.getValue();
            }
        });
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
        ObservableList<Order> listOfOrders
                = FXCollections.observableArrayList(mainStorage.getAllOrders());
        updateTable(listOfOrders);

        for (int i = 1; i < 32; i++) {
            daybox.getItems().add(i);
            daybox1.getItems().add(i);
        }

        for (int i = 1; i < 13; i++) {
            monthbox.getItems().add(i);
            monthbox1.getItems().add(i);
        }

        for (int i = 500; i < LocalDate.now().getYear()+6; i++) {
            yearbox.getItems().add(i);
            yearbox1.getItems().add(i);
        }

        ObservableList<Client> listOfClients
                = FXCollections.observableArrayList(mainStorage.getAllClients());
        updateClients(listOfClients);

    }

    //-------------------------------------Create

    private Client selectedclient;

    @FXML
    private TableView<Book> BookTableA = new TableView<>();

    @FXML
    private TableColumn<Book, Integer> bookIDINOrder = new TableColumn<>();

    @FXML
    private TableColumn<Book, Integer> bookIDinAccessible = new TableColumn<>();

    @FXML
    private TableColumn<Book, String> bookTitleInOrder = new TableColumn<>();

    @FXML
    private TableView<Book> bookinOrderA = new TableView<>();

    @FXML
    private TableColumn<Client, Integer> clientIDinOrder = new TableColumn<>();

    @FXML
    private TableView<Client> clientTableA = new TableView<>();

    @FXML
    private ComboBox daybox = new ComboBox();

    @FXML
    private ComboBox daybox1 = new ComboBox();

    @FXML
    private TableColumn<Client, String> fullNameInOrder = new TableColumn<>();

    @FXML
    private ComboBox monthbox = new ComboBox();

    @FXML
    private ComboBox monthbox1 = new ComboBox();

    @FXML
    private TableColumn<Book, String> titleinAccesible = new TableColumn<>();

    @FXML
    private ComboBox yearbox = new ComboBox();

    @FXML
    private ComboBox yearbox1 = new ComboBox();

    @FXML
    void onadd(ActionEvent event) {

    }

    @FXML
    void oncancel(ActionEvent event) {
        AdminOptionWindow.addOrder.close();
        AdminOptionWindow.addOrder = null;
    }

    @FXML
    void onexit(ActionEvent event) {
        AdminOptionWindow.addOrder.close();
        AdminOptionWindow.addOrder = null;
    }

    public void selectClient(ActionEvent actionEvent) {
        System.out.println(selectedclient.getID());
    }

    public void onsearchclient(KeyEvent keyEvent) {
        ArrayList<Client> allclients = mainStorage.getAllClients();
        ArrayList<Client> clientlist = new ArrayList<>();
        for (Client client :
                allclients) {
            if (client.getFirstName().contains(onsearchclient.getText())
                    || client.getLastName().contains(onsearchclient.getText())) {
                clientlist.add(client);
            }
        }
        ObservableList<Client> listOfClients = FXCollections.observableArrayList(clientlist);
        updateClients(listOfClients);
    }


    //-------------------------------------Create
}
