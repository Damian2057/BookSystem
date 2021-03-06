package org.example.systemDialog.nomalOptions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.App;
import org.example.dao.ClassFactory;
import org.example.dao.Storage.MainStorage;
import org.example.model.Book;
import org.example.model.Order;
import org.example.systemDialog.AdminOptionWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
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

    public Button updateOption;
    public Button canceloptionM;
    public Button exitoptionM;
    public TextField titlefieldM;
    public TextField pricefieldM;
    public ComboBox idBox = new ComboBox();
    public ComboBox avalibox = new ComboBox();
    public GridPane calendar;
    public Text monthtext = new Text();
    public TextField searchfield;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MainStorage mainStorage = new MainStorage(App.BookURL);

    @FXML
    private TableColumn<Book, String> authortable = new TableColumn<>();

    @FXML
    private TableColumn<Book, LocalDate> datetable = new TableColumn<>();

    @FXML
    private TableColumn<Book,String> Avatable = new TableColumn();

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

    public void updateTable(ObservableList<Book> list) {
        idtable.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ID"));
        titletable.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        authortable.setCellValueFactory(new PropertyValueFactory<Book,String>("FullName"));
        datetable.setCellValueFactory(new PropertyValueFactory<Book,LocalDate>("publishDate"));
        pagetable.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pageCount"));
        pricetable.setCellValueFactory(new PropertyValueFactory<Book,Double>("Price"));
        Avatable.setCellValueFactory(new PropertyValueFactory<Book,String>("Accessible"));
        table.setItems(list);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book book, Book t1) {
                calendar.getChildren().clear();
                ArrayList<Order> ordertemp = new ArrayList<>();
                try {
                    ordertemp = ClassFactory.getJDBCBookSystem(App.BookURL).getOrderBybookID(observableValue.getValue().getID());
                } catch (Exception e) {
                    calendar.getChildren().clear();
                }
                int index = 1;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 7; j++) {
                        TextField text = new TextField();
                        try {
                            if(mainStorage.checkAvailabilityInDay(ordertemp,observableValue.getValue().getID(),index)) {
                                text = new TextField(String.valueOf(index));
                                text.getStyleClass().add("okdate");
                            } else {
                                text = new TextField(String.valueOf(index));
                                text.getStyleClass().add("nodate");
                            }
                            text.setMaxSize(500,500);
                            text.setAlignment(Pos.CENTER);
                            text.setEditable(false);
                            calendar.add(text,j,i);
                            index++;
                        } catch (Exception e) {
                            calendar.getChildren().clear();
                        }
                        if(index == LocalDate.now().getMonth().length(true)){
                            break;
                        }
                    }
                    if(index == LocalDate.now().getMonth().length(true)){
                        break;
                    }
                }
            }
        });
    }

    public void addbook(MouseEvent event) throws IOException {
        if(AdminOptionWindow.addStageB == null && AdminOptionWindow.modifyStageB == null) {
            AdminOptionWindow.addStageB = new Stage();
            AdminOptionWindow.addStageB.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.addStageB.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("addBookOption.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.addStageB.setScene(scene);
            AdminOptionWindow.addStageB.show();
        }
    }

    public void modifyBook(MouseEvent event) throws IOException {
        if(AdminOptionWindow.modifyStageB == null && AdminOptionWindow.addStageB == null) {
            AdminOptionWindow.modifyStageB = new Stage();
            AdminOptionWindow.modifyStageB.initStyle(StageStyle.UNDECORATED);
            AdminOptionWindow.modifyStageB.setAlwaysOnTop(true);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("modifyBookOption.fxml"));
            fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
            Scene scene = new Scene(fxmlLoader2.load());
            AdminOptionWindow.modifyStageB.setScene(scene);
            AdminOptionWindow.modifyStageB.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthtext.setText(LocalDate.now().getMonth().toString());
        authorbox.setVisibleRowCount(7);
        daybox.setVisibleRowCount(7);
        monthbox.setVisibleRowCount(7);
        yearbox.setVisibleRowCount(7);
        idBox.setVisibleRowCount(7);

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

        var listBook = mainStorage.getAllBooks();
        for (int i = 0; i < listBook.size(); i++) {
            idBox.getItems().add(listBook.get(i).getID());
        }
        avalibox.getItems().add(getBundle("bundle").getString("yes"));
        avalibox.getItems().add(getBundle("bundle").getString("no"));
        ObservableList<Book> listOfBooks = FXCollections.observableArrayList(mainStorage.getAllBooks());
        updateTable(listOfBooks);
    }

    public void onsearch(ActionEvent actionEvent) throws Exception {
        ArrayList<Book> allbook = mainStorage.getAllBooks();
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book book :
                allbook) {
            if (book.getTitle().contains(searchfield.getText())
                    || book.getFullName().contains(searchfield.getText())) {
                booklist.add(book);
            }
        }
        ObservableList<Book> listOfBooks = FXCollections.observableArrayList(booklist);
        updateTable(listOfBooks);
    }

    private LocalDate createDate() {
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

    public void onadd(ActionEvent actionEvent) {
        try {
            if(Integer.parseInt(pagefield.getText()) > 5 && Double.parseDouble(pricefield.getText()) >= 0) {
                mainStorage.addBook(titlefield.getText()
                        , mainStorage.getAuthorStorage().getAuthorIDbydata(authorbox.getSelectionModel().getSelectedItem().toString())
                        ,createDate()
                        ,Integer.parseInt(pagefield.getText())
                        ,Double.parseDouble(pricefield.getText()));
                AdminOptionWindow.addStageB.close();
                AdminOptionWindow.addStageB = null;
            }
        } catch (Exception e) {
            logger.error("Error during book addition");
        }
    }

    public void oncancel(ActionEvent actionEvent) {
        AdminOptionWindow.addStageB.close();
        AdminOptionWindow.addStageB = null;
    }

    public void onexit(ActionEvent actionEvent) {
        AdminOptionWindow.addStageB.close();
        AdminOptionWindow.addStageB = null;
    }

    public void onUpdate(ActionEvent actionEvent) throws Exception {
        try {
            mainStorage.updateBook(Integer.parseInt(idBox.getValue().toString()),
                    titlefieldM.getText(),"title");
            mainStorage.updateBook(Integer.parseInt(idBox.getValue().toString()),
                    pricefieldM.getText(),"price");
            String status;
            if(Objects.equals(avalibox.getSelectionModel().getSelectedItem().toString(), getBundle("bundle").getString("yes"))) {
                status = "true";
            } else {
                status = "false";
            }
            mainStorage.updateBook(Integer.parseInt(idBox.getValue().toString()),
                    status,"status");
            mainStorage.updateBook(Integer.parseInt(idBox.getValue().toString()),
                    createDate().toString(),"publicationDate");

            AdminOptionWindow.modifyStageB.close();
            AdminOptionWindow.modifyStageB = null;
        } catch (Exception e) {
            logger.info("No ID selected");
        }

    }

    public void oncancelM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyStageB.close();
        AdminOptionWindow.modifyStageB = null;
    }

    public void onexitM(ActionEvent actionEvent) {
        AdminOptionWindow.modifyStageB.close();
        AdminOptionWindow.modifyStageB = null;
    }

    public void onIDSelected(ActionEvent actionEvent) throws Exception {
        Book temp = mainStorage.getBook(Integer.parseInt(idBox.getValue().toString()));
        titlefieldM.setText(temp.getTitle());
        pricefieldM.setText(String.valueOf(temp.getPrice()));
        daybox.setValue(temp.getPublishDate().getDayOfMonth());
        monthbox.setValue(temp.getPublishDate().getMonthValue());
        yearbox.setValue(temp.getPublishDate().getYear());
        if(temp.isAccessible()) {
            avalibox.getSelectionModel().selectFirst();
        } else {
            avalibox.getSelectionModel().selectLast();
        }
    }
}
