module org.example.model {
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires java.sql;


    exports org.example.model;
    opens org.example.model;
    exports org.example.dao;
    opens org.example.dao;
    exports org.example.model.Client;
    opens org.example.model.Client;
    exports org.example.model.Client.types;
    opens org.example.model.Client.types;
}