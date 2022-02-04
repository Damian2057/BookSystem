package org.example.dao.jdbcmodel;

import org.example.Exceptions.Dao.StatementReadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBookSystem implements AutoCloseable{

    private static String URL;
    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JDBCBookSystem(String URL) {
        JDBCBookSystem.URL = URL;
    }

    private void connectToDataBase() {
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            logger.error("Error occurred during dataBase connection");
            throwables.printStackTrace();
        }
    }

    private String readstatement(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n ");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            throw new StatementReadException();
        }
    }

    public void createDataBase() {
        try {
            logger.info("DataBase creation attempt");
            connectToDataBase();
            statement.execute(readstatement("@../../SQLStatements/AuthorTablecreate.sql"));
            statement.execute(readstatement("@../../SQLStatements/BookTablecreate.sql"));
            statement.execute(readstatement("@../../SQLStatements/ClientTablecreate.sql"));
            statement.execute(readstatement("@../../SQLStatements/OrderTablecreate.sql"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    @Override
    public void close() throws Exception {
        connection.close();
        statement.close();
    }
}
