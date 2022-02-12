package org.example.systemDialog.nomalOptions;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class BookOptions {

    public Rectangle addbutton;
    public Rectangle addbutton1;
    public Rectangle addbutton11;


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
}
