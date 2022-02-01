package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import static java.util.ResourceBundle.getBundle;

import org.example.model.Client.types.Premium;
import org.example.model.Client.types.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");
        Type t = new Premium();
        System.out.println(t.getReduction());
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}