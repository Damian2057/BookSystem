package org.example.dao.jdbcmodel;

import org.example.Exceptions.Dao.DataBaseCreateException;
import org.example.Exceptions.Dao.StatementReadException;
import org.example.model.users.Personnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class JDBCLoginSystem implements AutoCloseable {

    private static String URL;
    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JDBCLoginSystem(String URL) throws Exception {
        JDBCLoginSystem.URL = URL;
        connectToDataBase();
        close();
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
            connectToDataBase();
            logger.info("DataBase creation attempt");
            statement.execute(readstatement("@../../SQLoginStatements/LoginTable.sql"));
            close();
        } catch (Exception throwables) {
            throw new DataBaseCreateException();
        }
    }

    public void addPersonel(Personnel personnel) throws Exception {
        connectToDataBase();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLoginStatements/addPersonel.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1,personnel.getNickName());
            preparedStatement.setString(2,personnel.getPassword());
            preparedStatement.setInt(3,personnel.getID());
            preparedStatement.setInt(4, personnel.getPermLevel());

            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Personel has been saved in the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during saving Author");
        }
        close();
    }

    public void removePersonel(int ID) throws Exception {
        connectToDataBase();
        logger.info("An attempt of removing the Personnel from the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLoginStatements/removePersonel.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Personel has been deleted from the database");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during Personnel removal");
        } catch (Exception e) {
            logger.error("Something goes wrong during Personnel removal");
            throw new StatementReadException();
        }
    }

    public void updatePersonnel(String partToUpdate, int ID, String newData) throws SQLException {
        connectToDataBase();
        logger.info("An attempt of update Personnel in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLoginStatements/updatePersonnel"+ partToUpdate +".sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, newData);
            preparedStatement.setInt(2,ID);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Personnel has been Updated");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during update");
        } catch (Exception e) {
            logger.error("Something goes wrong during update");
            throw new StatementReadException();
        }
    }




    @Override
    public void close() throws Exception {
        connection.close();
        statement.close();
    }
}
