package org.example.dao;

import org.example.dao.jdbcmodel.JDBCBookSystem;
import org.example.dao.jdbcmodel.JDBCLoginSystem;

public class ClassFactory {
    private ClassFactory() {
    }

    public static JDBCBookSystem getJDBCBookSystem(String URL) throws Exception {
        return new JDBCBookSystem(URL);
    }

    public static JDBCLoginSystem getJDBCLoginSystem(String URL) throws Exception {
        return new JDBCLoginSystem(URL);
    }


}
