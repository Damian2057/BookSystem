package org.example.systemDialog.nomalOptions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.Exceptions.Model.IncorrectOrderDateException;
import org.example.Exceptions.Model.OrderTimeException;
import org.example.Exceptions.data.DataConflictException;
import org.example.dao.Storage.MainStorage;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.example.systemDialog.AdminOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class OrderOptions implements Initializable {

    public TextField onsearchclient;
    public TextField onsearchbook;
    public Text ordererror = new Text();
    public Text info;
    public Button okbutton;
    public Button endButton;
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
    void endOrder(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addOrder == null && AdminOptionWindow.modifyOrder == null
                && AdminOptionWindow.removeOrder == null) {
            AdminOptionWindow.removeOrder = new Stage();
            AdminOptionWindow.removeOrder.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.removeOrder.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("endOrder.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.removeOrder.setScene(scene);
            AdminOptionWindow.removeOrder.show();
        }
    }

    @FXML
    void modifyOrder(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addOrder == null && AdminOptionWindow.modifyOrder == null
                && AdminOptionWindow.removeOrder == null) {
            AdminOptionWindow.modifyOrder = new Stage();
            AdminOptionWindow.modifyOrder.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.modifyOrder.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("modifyOrder.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.modifyOrder.setScene(scene);
            AdminOptionWindow.modifyOrder.show();
        }
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

    private void updateClients(ObservableList<Client> list) {
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

    private void updateBooks(ObservableList<Book> list) {
        //left column
        bookIDinAccessible.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ID"));
        titleinAccesible.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
        BookTableA.setItems(list);
        BookTableA.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book client, Book t1) {
                AdminOptionWindow.selectedBook = observableValue.getValue();
            }
        });
    }

    private void updateSelectedBooks() {
        //right column
        ObservableList<Book> listOfBooks
                = FXCollections.observableArrayList(bookArrayList);
        bookIDINOrder.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ID"));
        bookTitleInOrder.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
        bookinOrderA.setItems(listOfBooks);
        bookinOrderA.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book client, Book t1) {
                AdminOptionWindow.selectedBook = observableValue.getValue();
            }
        });
    }

    public void updateTable(ObservableList<Order> list) {
        try {
            idtable.setCellValueFactory(new PropertyValueFactory<Order,Integer>("ID"));
            clientID.setCellValueFactory(new PropertyValueFactory<Order,Integer>("ClientID"));
            fullnametable.setCellValueFactory(new PropertyValueFactory<Order,String>("ClientfullName"));
            StartDate.setCellValueFactory(new PropertyValueFactory<Order,LocalDate>("StartReservationdate"));
            EndDate.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("EndReservationDate"));
            table.setItems(list);
            table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
                @Override
                public void changed(ObservableValue<? extends Order> observableValue, Order order, Order t1) {
                    try {
                        ObservableList<Book> listOfBooks
                                = FXCollections.observableArrayList(observableValue.getValue().getListofBooks());
                        bookIDtable.setCellValueFactory(new PropertyValueFactory<Book, Integer>("ID"));
                        booktitleTable.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
                        bookTable.setItems(listOfBooks);
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
            ObservableList<Order> listOfOrders = FXCollections.observableArrayList(mainStorage.getAllOrders());
            updateTable(listOfOrders);
        }
    }

    private LocalDate createDate(ComboBox yearbox, ComboBox monthbox, ComboBox daybox) {
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
        return LocalDate.parse(year+"-"+month+"-"+day);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ordererror.setText("");
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

        for (int i = LocalDate.now().getYear(); i < LocalDate.now().getYear()+6; i++) {
            yearbox.getItems().add(i);
            yearbox1.getItems().add(i);
        }

        ObservableList<Client> listOfClients
                = FXCollections.observableArrayList(mainStorage.getAllClients());
        updateClients(listOfClients);

        ObservableList<Book> listOfBooks
                = FXCollections.observableArrayList(mainStorage.getAllBooks());
        updateBooks(listOfBooks);

        idclientM.setEditable(false);
        clientnameM.setEditable(false);

        idBox.setVisibleRowCount(7);
        var OrderList = mainStorage.getAllOrders();
        for (Order order : OrderList) {
            idBox.getItems().add(order.getID());
        }

        dayfield.setEditable(false);
        monthfield.setEditable(false);
        yearfield1.setEditable(false);
        dayfield1.setEditable(false);
        monthfield1.setEditable(false);
        yearfield11.setEditable(false);

        fullnameR.setEditable(false);
        idclientR.setEditable(false);

    }

    //-------------------------------------Create-------------------

    private Client selectedclient;
    //private Book selectedBook;
    private ArrayList<Book> bookArrayList = new ArrayList<>();

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
    void onadd(ActionEvent event) throws Exception {
        try {
            int temp = mainStorage.createOrder(selectedclient.getID()
                    ,bookArrayList.get(0).getID(),createDate(yearbox,monthbox,daybox)
                    ,createDate(yearbox1,monthbox1,daybox1));
            for (int i = 1; i < bookArrayList.size(); i++) {
                mainStorage.addBookToOrder(temp,bookArrayList.get(i).getID());
            }
            AdminOptionWindow.addOrder.close();
            AdminOptionWindow.addOrder = null;
        } catch (DataConflictException e) {
            ordererror.setText(getBundle("ExceptionsMessages").getString("DataConflict"));
            cleartext(ordererror);
        } catch (IncorrectOrderDateException e) {
            ordererror.setText(getBundle("ExceptionsMessages").getString("incorrectOrderDate"));
            cleartext(ordererror);
        } catch (OrderTimeException e) {
            ordererror.setText(getBundle("ExceptionsMessages").getString("OrderInProgress"));
            cleartext(ordererror);
        } catch (Exception e) {
            ordererror.setText(getBundle("ExceptionsMessages").getString("EmptyData"));
            cleartext(ordererror);
        }
    }

    private void cleartext(Text text) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                text.setText("");
            }
        });
        new Thread(sleeper).start();
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

    public void onsearchbook(KeyEvent keyEvent) {
        ArrayList<Book> allbook = mainStorage.getAllBooks();
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book book :
                allbook) {
            if (book.getTitle().contains(onsearchbook.getText())
                    || book.getFullName().contains(onsearchbook.getText())) {
                booklist.add(book);
            }
        }
        ObservableList<Book> listOfBooks = FXCollections.observableArrayList(booklist);
        updateBooks(listOfBooks);
    }

    public void addbooktoOrder(ActionEvent actionEvent) {
        try {
            onsearchbook.setText("");
            ObservableList<Book> listOfBooks
                    = FXCollections.observableArrayList(mainStorage.getAllBooks());
            updateBooks(listOfBooks);
            bookArrayList.add(AdminOptionWindow.selectedBook);
            updateSelectedBooks();
        } catch (Exception e) {
            logger.error("Any Book selected");
        }
    }

    public void removebookfromOrder(ActionEvent actionEvent) {
        try {
            bookArrayList.remove(AdminOptionWindow.selectedBook);
            updateSelectedBooks();
        } catch (Exception e) {
            logger.error("Any Book selected");
        }
    }
    //-------------------------------------Modify------------

    public ComboBox idBox = new ComboBox();

    public TextField idclientM = new TextField();
    public TextField clientnameM = new TextField();

    public TextField dayfield = new TextField();
    public TextField monthfield = new TextField();
    public TextField yearfield1 = new TextField();
    public TextField dayfield1 = new TextField();
    public TextField monthfield1 = new TextField();
    public TextField yearfield11 = new TextField();

    @FXML
    private TableView<Book> bookinOrderM = new TableView<>();
    @FXML
    private TableColumn<Book, String> bookIDINOrderM = new TableColumn<>();
    @FXML
    private TableColumn<Book, String> bookTitleInOrderM = new TableColumn<>();


    public void onIDSelected(ActionEvent actionEvent) throws Exception {
        Order temp = mainStorage.getOrder(Integer.parseInt(idBox.getValue().toString()));
        idclientM.setText(String.valueOf(temp.getClientID()));
        clientnameM.setText(temp.getClientfullName());
        bookArrayList = mainStorage.getOrder(Integer.parseInt(idBox.getValue()
                .toString())).getListofBooks();
        updateSelectedBooks();

        dayfield.setText(String.valueOf(temp.getStartReservationdate().getDayOfMonth()));
        monthfield.setText(String.valueOf(temp.getStartReservationdate().getMonthValue()));
        yearfield1.setText(String.valueOf(temp.getStartReservationdate().getYear()));

        dayfield1.setText(String.valueOf(temp.getEndReservationDate().getDayOfMonth()));
        monthfield1.setText(String.valueOf(temp.getEndReservationDate().getMonthValue()));
        yearfield11.setText(String.valueOf(temp.getEndReservationDate().getYear()));
    }

    public void onupdate(ActionEvent actionEvent) {
        AdminOptionWindow.modifyOrder.close();
        AdminOptionWindow.modifyOrder = null;
    }

    public void onexitM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyOrder.close();
        AdminOptionWindow.modifyOrder = null;
    }

    public void addbooktoOrderM(ActionEvent actionEvent) throws Exception {
        try {
            onsearchbook.setText("");
            mainStorage.addBookToOrder(Integer.parseInt(idBox.getValue().toString())
                    ,AdminOptionWindow.selectedBook.getID());

            ObservableList<Book> listOfBooks
                    = FXCollections.observableArrayList(mainStorage.getAllBooks());
            updateBooks(listOfBooks);
            updateSelectedBooks();
        } catch (DataConflictException e) {
            info.setText(getBundle("ExceptionsMessages").getString("DataConflict"));
            logger.error("Data Conflict");
            cleartext(info);
        } catch (OrderTimeException e) {
            info.setText(getBundle("ExceptionsMessages").getString("OrderInProgress"));
            logger.error("Order In Progress");
            cleartext(info);
        } catch (Exception e) {
            info.setText(getBundle("ExceptionsMessages").getString("AnyIDSelected"));
            logger.error("Order In Progress");
            cleartext(info);
        }
    }

    public void removebookfromOrderM(ActionEvent actionEvent) {
        if(bookArrayList.size() > 1) {
            try {
                mainStorage.removeBookFromOrder(Integer.parseInt(idBox.getValue().toString())
                        ,AdminOptionWindow.selectedBook.getID());
                bookArrayList.remove(AdminOptionWindow.selectedBook);
                updateSelectedBooks();
            } catch (Exception e) {
                logger.error("Any Book selected");
            }
        }
    }

    //-------------------------------------END------------

    public TextField fullnameR = new TextField();
    public TextField idclientR = new TextField();

    public void onend(ActionEvent actionEvent) throws Exception {
        if(AdminOptionWindow.removeOrder != null && AdminOptionWindow.payment == null) {
            try {
                endButton.setDisable(true);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/systemDialog/OrderPayment.fxml"),getBundle("bundle", Locale.getDefault()));
                Parent root = loader.load();
                PaymentStage paymentStage = loader.getController();

                paymentStage.setSum(mainStorage.endOrderandGetSum(Integer.parseInt(idBox.getValue().toString())));
                AdminOptionWindow.payment = new Stage();

                AdminOptionWindow.payment.initStyle(StageStyle.UNDECORATED);

                AdminOptionWindow.payment.setScene(new Scene(root));
                AdminOptionWindow.payment.setAlwaysOnTop(true);
                AdminOptionWindow.payment.setResizable(false);
                AdminOptionWindow.payment.show();

                AdminOptionWindow.payment.setOnHidden(windowEvent -> {
                    AdminOptionWindow.payment = null;
                    endButton.setDisable(false);
                });
            } catch (Exception e) {
                logger.error("No ID was selected");
                endButton.setDisable(false);
            }

        }
    }

    public void onexitR(ActionEvent actionEvent) {
        AdminOptionWindow.removeOrder.close();
        AdminOptionWindow.removeOrder = null;
    }

    public void onIDSelectedR(ActionEvent actionEvent) throws Exception {
        Order temp = mainStorage.getOrder(Integer.parseInt(idBox.getValue().toString()));
        idclientR.setText(String.valueOf(temp.getClientID()));
        fullnameR.setText(temp.getClientfullName());
        bookArrayList = mainStorage.getOrder(Integer.parseInt(idBox.getValue()
                .toString())).getListofBooks();
        updateSelectedBooks();

        dayfield.setText(String.valueOf(temp.getStartReservationdate().getDayOfMonth()));
        monthfield.setText(String.valueOf(temp.getStartReservationdate().getMonthValue()));
        yearfield1.setText(String.valueOf(temp.getStartReservationdate().getYear()));

        dayfield1.setText(String.valueOf(temp.getEndReservationDate().getDayOfMonth()));
        monthfield1.setText(String.valueOf(temp.getEndReservationDate().getMonthValue()));
        yearfield11.setText(String.valueOf(temp.getEndReservationDate().getYear()));
    }
}
