package org.example.systemDialog.nomalOptions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.Exceptions.Dao.ObjectsDependentException;
import org.example.dao.Storage.MainStorage;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.systemDialog.AdminOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class ClientOptions implements Initializable {

    public Button addoption;
    public Button canceloption;
    public Button exitoption;
    public TextField firstfield;
    public TextField lastfield;
    public TextField phonefield;
    public TextField emailfield;
    public TextField addressfield;
    public ComboBox idBox = new ComboBox();
    public TextField orderscount;
    public TextField firstfieldR;
    public TextField lastfieldR;
    public Text clienterror = new Text();
    public Text clientsucce = new Text();
    public Button removeOption;
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
    void modifyClient(MouseEvent event) throws IOException {
        if(AdminOptionWindow.modifyStageC == null && AdminOptionWindow.addStageC == null) {
            AdminOptionWindow.modifyStageC = new Stage();
            AdminOptionWindow.modifyStageC.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.modifyStageC.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("modifyClient.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.modifyStageC.setScene(scene);
            AdminOptionWindow.modifyStageC.show();
        }
    }

    @FXML
    void addClient(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addStageC == null && AdminOptionWindow.modifyStageC == null
                && AdminOptionWindow.removeStageC == null) {
            AdminOptionWindow.addStageC = new Stage();
            AdminOptionWindow.addStageC.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.addStageC.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("addclient.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.addStageC.setScene(scene);
            AdminOptionWindow.addStageC.show();
        }
    }

    @FXML
    void removeClient(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addStageC == null && AdminOptionWindow.modifyStageC == null
                && AdminOptionWindow.removeStageC == null) {
            AdminOptionWindow.removeStageC = new Stage();
            AdminOptionWindow.removeStageC.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.removeStageC.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("removeclient.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.removeStageC.setScene(scene);
            AdminOptionWindow.removeStageC.show();
        }
    }

    @FXML
    void onsearch(ActionEvent event) {

    }

    public void updateTable(ObservableList<Client> list) {
        idtable.setCellValueFactory(new PropertyValueFactory<Client,Integer>("ID"));
        firsttable.setCellValueFactory(new PropertyValueFactory<Client,String>("FirstName"));
        lasttable.setCellValueFactory(new PropertyValueFactory<Client,String>("LastName"));
        phonetable.setCellValueFactory(new PropertyValueFactory<Client, String>("PhoneNumber"));
        emailtable.setCellValueFactory(new PropertyValueFactory<Client, String>("EmailAddress"));
        addresstable.setCellValueFactory(new PropertyValueFactory<Client,String>("Address"));
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Client> listOfClients
                = FXCollections.observableArrayList(mainStorage.getAllClients());
        updateTable(listOfClients);

        idBox.setVisibleRowCount(7);
        var listClients = mainStorage.getAllClients();
        for (int i = 0; i < listClients.size(); i++) {
            idBox.getItems().add(listClients.get(i).getID());
        }
        clienterror.setVisible(false);
        clientsucce.setVisible(false);
    }

    public void onadd(ActionEvent actionEvent) {
        try {

            mainStorage.addClient(firstfield.getText(),lastfield.getText()
                    ,phonefield.getText(),emailfield.getText(),addressfield.getText());
            AdminOptionWindow.addStageC.close();
            AdminOptionWindow.addStageC = null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during Client addition");
        }
    }

    public void oncancel(ActionEvent actionEvent) {
        AdminOptionWindow.addStageC.close();
        AdminOptionWindow.addStageC = null;
    }

    public void onexit(ActionEvent actionEvent) {
        AdminOptionWindow.addStageC.close();
        AdminOptionWindow.addStageC = null;
    }

    public void onUpdate(ActionEvent actionEvent) throws Exception {
        mainStorage.updateClient(Integer.parseInt(idBox.getValue().toString())
                ,firstfield.getText(),"name");
        mainStorage.updateClient(Integer.parseInt(idBox.getValue().toString())
                ,lastfield.getText(),"lastname");
        mainStorage.updateClient(Integer.parseInt(idBox.getValue().toString())
                ,phonefield.getText(),"phone");
        mainStorage.updateClient(Integer.parseInt(idBox.getValue().toString())
                ,emailfield.getText(),"email");
        mainStorage.updateClient(Integer.parseInt(idBox.getValue().toString())
                ,addressfield.getText(),"address");
        mainStorage.updateClient(Integer.parseInt(idBox.getValue().toString())
                ,orderscount.getText(),"order");
        AdminOptionWindow.modifyStageC.close();
        AdminOptionWindow.modifyStageC = null;
    }

    public void oncancelM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyStageC.close();
        AdminOptionWindow.modifyStageC = null;
    }

    public void onexitM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyStageC.close();
        AdminOptionWindow.modifyStageC = null;
    }

    public void onIDSelected(ActionEvent actionEvent) throws Exception {
        clienterror.setVisible(false);
        Client temp = mainStorage.getClient(Integer.parseInt(idBox.getValue().toString()));
        firstfield.setText(temp.getFirstName());
        lastfield.setText(temp.getLastName());
        phonefield.setText(temp.getPhoneNumber());
        emailfield.setText(temp.getEmailAddress());
        addressfield.setText(temp.getAddress());
        orderscount.setText(String.valueOf(temp.getOrderCount()));
    }

    public void ondelete(ActionEvent actionEvent) throws Exception {
        try {
            removeOption.setDisable(true);
            mainStorage.removeClient(Integer.parseInt(idBox.getValue().toString()));
            clientsucce.setVisible(true);
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
                    AdminOptionWindow.removeStageC.close();
                    AdminOptionWindow.removeStageC = null;
                }
            });
            new Thread(sleeper).start();

        } catch (ObjectsDependentException e) {
            removeOption.setDisable(false);
            clientsucce.setVisible(false);
            clienterror.setVisible(true);
        }
    }

    public void oncancelR(ActionEvent actionEvent) {
        AdminOptionWindow.removeStageC.close();
        AdminOptionWindow.removeStageC = null;
    }

    public void onexitR(ActionEvent actionEvent) {
        AdminOptionWindow.removeStageC.close();
        AdminOptionWindow.removeStageC = null;
    }

    public void onIDSelectedR(ActionEvent actionEvent) throws Exception {
        Client temp = mainStorage.getClient(Integer.parseInt(idBox.getValue().toString()));
        firstfieldR.setText(temp.getFirstName());
        lastfieldR.setText(temp.getLastName());
    }
}
