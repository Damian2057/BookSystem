package org.example.systemDialog.nomalOptions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.dao.Storage.MainStorage;
import org.example.model.Author;
import org.example.model.Client.Client;
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

public class AuthorOptions implements Initializable {

    public TextField firstfield;
    public ComboBox monthbox = new ComboBox();
    public ComboBox yearbox = new ComboBox();
    public ComboBox daybox = new ComboBox();
    public TextField lastfield;
    public ComboBox monthbox1 = new ComboBox();
    public ComboBox yearbox1 = new ComboBox();
    public ComboBox daybox1 = new ComboBox();
    public ComboBox idBox = new ComboBox();
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
    void addAuthor(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addStageA == null && AdminOptionWindow.modifyStageA == null) {
            AdminOptionWindow.addStageA = new Stage();
            AdminOptionWindow.addStageA.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.addStageA.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("addAuthor.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.addStageA.setScene(scene);
            AdminOptionWindow.addStageA.show();
        }
    }

    @FXML
    void modifyAuthor(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addStageA == null && AdminOptionWindow.modifyStageA == null) {
            AdminOptionWindow.modifyStageA = new Stage();
            AdminOptionWindow.modifyStageA.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.modifyStageA.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("modifyAuthor.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.modifyStageA.setScene(scene);
            AdminOptionWindow.modifyStageA.show();
        }
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
        idBox.setVisibleRowCount(7);
        var listAuthors = mainStorage.getAllAuthors();
        for (int i = 0; i < listAuthors.size(); i++) {
            idBox.getItems().add(listAuthors.get(i).getID());
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

    public void onadd(ActionEvent actionEvent) throws Exception {
        try {
                mainStorage.addAuthor(firstfield.getText()
                        ,lastfield.getText()
                        ,createDate(yearbox,monthbox,daybox)
                        ,createDate(yearbox1,monthbox1,daybox1));
        } catch (NullPointerException e) {
            try {
                mainStorage.addAuthor(firstfield.getText()
                        ,lastfield.getText()
                        ,createDate(yearbox,monthbox,daybox));
            } catch (Exception exception) {
                logger.error("error during Author saving");
            }
        }
        AdminOptionWindow.addStageA.close();
        AdminOptionWindow.addStageA = null;
    }

    public void oncancel(ActionEvent actionEvent) {
        AdminOptionWindow.addStageA.close();
        AdminOptionWindow.addStageA = null;
    }

    public void onexit(ActionEvent actionEvent) {
        AdminOptionWindow.addStageA.close();
        AdminOptionWindow.addStageA = null;
    }

    public void onIDSelected(ActionEvent actionEvent) throws Exception {
        Author temp = mainStorage.getAuthor(Integer.parseInt(idBox.getValue().toString()));
        firstfield.setText(temp.getFirstName());
        lastfield.setText(temp.getLastName());

        daybox.setValue(temp.getBirthDay().getDayOfMonth());
        monthbox.setValue(temp.getBirthDay().getMonthValue());
        yearbox.setValue(temp.getBirthDay().getYear());

        daybox1.setValue(temp.getDeathDate().getDayOfMonth());
        monthbox1.setValue(temp.getDeathDate().getMonthValue());
        yearbox1.setValue(temp.getDeathDate().getYear());
    }

    public void onUpdate(ActionEvent actionEvent) throws Exception {
        try {
            mainStorage.updateAuthor(Integer.parseInt(idBox.getValue().toString()),
                    firstfield.getText(),"name");
            mainStorage.updateAuthor(Integer.parseInt(idBox.getValue().toString()),
                    lastfield.getText(),"lastname");
            mainStorage.updateAuthor(Integer.parseInt(idBox.getValue().toString()),
                    createDate(yearbox1,monthbox1,daybox1).toString(),"deathdate");
            AdminOptionWindow.modifyStageA.close();
            AdminOptionWindow.modifyStageA = null;
        } catch (Exception e) {
            logger.error("Any ID selected");
        }
    }

    public void oncancelM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyStageA.close();
        AdminOptionWindow.modifyStageA = null;
    }

    public void onexitM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyStageA.close();
        AdminOptionWindow.modifyStageA = null;
    }
}
