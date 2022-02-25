package org.example.systemDialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

import static java.util.ResourceBundle.getBundle;

public class AdminOptionWindow {

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

    public void show() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("AdminScene.fxml"));
        fxmlLoader2.setResources(getBundle("bundle", Locale.getDefault()));
        Scene scene = new Scene(fxmlLoader2.load());
        stage.setScene(scene);
        stage.setResizable(false);
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

    public void workersget(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("WorkerOption.fxml"),getBundle("bundle", Locale.getDefault()));
        changepane.getChildren().setAll(pane);
    }

    public void logout(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void settings(ActionEvent actionEvent) {
    }

    public void authorsget(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AuthorOption.fxml"),getBundle("bundle", Locale.getDefault()));
        changepane.getChildren().setAll(pane);
    }
}
