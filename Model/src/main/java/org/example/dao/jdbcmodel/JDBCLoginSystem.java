package org.example.dao.jdbcmodel;

import org.example.Exceptions.Dao.DataBaseCreateException;
import org.example.Exceptions.Dao.DownloadDataException;
import org.example.Exceptions.Dao.StatementReadException;
import org.example.Exceptions.Dao.WrongLoginDataException;
import org.example.model.Client.Client;
import org.example.model.users.Admin;
import org.example.model.users.Personnel;
import org.example.model.users.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class JDBCLoginSystem implements AutoCloseable {

    private static String URL;
    private static String user;
    private static String password;
    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JDBCLoginSystem(String URL, String user, String password) throws Exception {
        JDBCLoginSystem.URL = URL;
        JDBCLoginSystem.user = user;
        JDBCLoginSystem.password = password;
        connectToDataBase();
        close();
    }

    private void connectToDataBase() {
        try {
            connection = DriverManager.getConnection(URL,user,password);
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

    public Personnel loginPersonel(String user, String password) throws Exception {
        for(int i = 0; i < getListofworkers().size(); i++) {
            if(Objects.equals(getListofworkers().get(i).getNickName(), user)
                    && Objects.equals(getListofworkers().get(i).getPassword(), password)) {
                return getListofworkers().get(i);
            }
        }
        throw new WrongLoginDataException();
    }

    public ArrayList<Personnel> getListofworkers() throws Exception {
        connectToDataBase();
        var PersonelList = new ArrayList<Personnel>();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLoginStatements/getAllPersonel.sql"))) {
            try {
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Personnel temp;
                    if(resultSet.getInt(4) == 1){
                        temp = new Admin(resultSet.getString(1)
                                ,resultSet.getString(2),resultSet.getInt(3));
                    } else {
                        temp = new Worker(resultSet.getString(1)
                                ,resultSet.getString(2),resultSet.getInt(3));
                    }
                    PersonelList.add(temp);
                }
                close();
                return PersonelList;
            } catch (SQLException throwables) {
                logger.error("Error during getting Workers list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Personnel SQL Script");
            close();
            throw new StatementReadException();
        }
    }

    public Personnel getPersonnelbyID(int ID) throws Exception {
        for(int i = 0; i < getListofworkers().size(); i++) {
            if(ID == getListofworkers().get(i).getID()) {
                return getListofworkers().get(i);
            }
        }
        return null;
    }



    @Override
    public void close() throws Exception {
        connection.close();
        statement.close();
    }
}
