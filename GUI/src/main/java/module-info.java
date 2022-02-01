module org.example {
    requires org.example.model;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires org.apache.logging.log4j;

    opens org.example;
}