package org.example.systemDialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.model.Book;

import java.io.IOException;
import java.util.Locale;

import static java.util.ResourceBundle.getBundle;

public class WorkerOptionWindow {

    public AnchorPane changepane;

    public static Stage addStageB;
    public static Stage modifyStageB;

    public static Stage addStageC;
    public static Stage modifyStageC;
    public static Stage removeStageC;

    public static Stage addStageA;
    public static Stage modifyStageA;

    public static Stage addWorker;
    public static Stage modifyWorker;
    public static Stage removeWorker;

    public static Stage addOrder;
    public static Stage modifyOrder;
    public static Stage removeOrder;

    public static Book selectedBook;

    public static Stage payment;
    public Button settingbutton;

    public void show() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("WorkerScene.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.show();
    }

    public void clientsget(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ClientOption.fxml"),getBundle("bundle", Locale.getDefault()));
        changepane.getChildren().setAll(pane);
    }

    public void booksget(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BookOption.fxml"),getBundle("bundle", Locale.getDefault()));
        changepane.getChildren().setAll(pane);
    }

    public void ordersget(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("OrderOption.fxml"),getBundle("bundle", Locale.getDefault()));
        changepane.getChildren().setAll(pane);
    }

    public void logout(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void settings(ActionEvent actionEvent) throws IOException {
        Stage prevstage = (Stage) settingbutton.getScene().getWindow();
        prevstage.close();

        Stage stage = new Stage();
        // AdminOptionWindow.addStageC.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("settings.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(windowEvent -> {
            try {
                show();
            } catch (IOException ignored) {
            }
        });
    }

    public void authorsget(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AuthorOption.fxml"),getBundle("bundle", Locale.getDefault()));
        changepane.getChildren().setAll(pane);
    }
}
