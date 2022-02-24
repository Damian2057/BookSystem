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
    exports org.example.initProgram;
    opens org.example.initProgram;
    exports org.example;
    opens org.example.loginDialog;
    exports org.example.loginDialog;
    opens org.example.systemDialog;
    exports org.example.systemDialog;
    opens org.example.systemDialog.nomalOptions;
    exports org.example.systemDialog.nomalOptions;
    exports org.example.systemDialog.adminOptions;
    opens org.example.systemDialog.adminOptions;
}