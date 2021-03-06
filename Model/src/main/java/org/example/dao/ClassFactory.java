package org.example.dao;

import org.example.AppConfiguration.Config;
import org.example.dao.filemodel.FilesaverSystem;
import org.example.dao.jdbcmodel.JDBCBookSystem;
import org.example.dao.jdbcmodel.JDBCLoginSystem;

public class ClassFactory {
    private ClassFactory() {
    }

    public static JDBCBookSystem getJDBCBookSystem(String URL) throws Exception {
        return new JDBCBookSystem(URL);
    }

    public static JDBCLoginSystem getJDBCLoginSystem(String URL, String user, String password) throws Exception {
        return new JDBCLoginSystem(URL, user, password);
    }

    public static Dao<Config> getFileSaverSystem(String path) throws Exception {
        return new FilesaverSystem(path);
    }


}
