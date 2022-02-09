module org.example.model {
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires java.sql;


    opens org.example.model;
    exports org.example.model;

    exports org.example.dao;
    opens org.example.dao;

//    exports org.example.dao.usersManager;
//    opens org.example.dao.usersManager;

    exports org.example.model.Client;
    opens org.example.model.Client;

    exports org.example.model.Client.types;
    opens org.example.model.Client.types;

    exports org.example.model.users;
    opens org.example.model.users;

    exports org.example.dao.Storage;
    opens org.example.dao.Storage;

    exports org.example.Exceptions;
    opens org.example.Exceptions;

    exports org.example.Exceptions.Model;
    opens org.example.Exceptions.Model;

//    exports org.example.Exceptions.Dao;
//    opens org.example.Exceptions.Dao;

    exports org.example.SecurityLayer;
    opens org.example.SecurityLayer;
    exports org.example.dao.jdbcmodel;

    exports org.example.AppConfiguration;
    opens org.example.AppConfiguration;

    exports org.example.dao.filemodel;
    opens org.example.dao.filemodel;
}