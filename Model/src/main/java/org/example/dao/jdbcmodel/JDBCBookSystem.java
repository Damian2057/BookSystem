package org.example.dao.jdbcmodel;

import org.example.Exceptions.Dao.DownloadDataException;
import org.example.Exceptions.Dao.StatementReadException;
import org.example.model.Author;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
            logger.info("Connection");
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
            close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Author> getListofAuthors() throws Exception {
        connectToDataBase();
        logger.info("Downloading authors");
        var AuthorList = new ArrayList<Author>();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/getAllAuthors.sql"))) {
            try {
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Author temp;
                    if(resultSet.getDate(5) == null) {
                         temp = new Author(resultSet.getInt(1),resultSet.getString(2)
                                ,resultSet.getString(3), LocalDate.parse(resultSet.getString(4)));
                    } else {
                         temp = new Author(resultSet.getInt(1),resultSet.getString(2)
                                ,resultSet.getString(3),LocalDate.parse(resultSet.getString(4))
                                 ,LocalDate.parse(resultSet.getString(5)));
                    }
                    AuthorList.add(temp);
                }
                close();
                return AuthorList;
            } catch (SQLException throwables) {
                logger.error("Error during getting list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Script");
            close();
            throw new StatementReadException();
        }
    }

    public void addAuthor(Author author) throws Exception {
        connectToDataBase();
        logger.info("An attempt to enter the Author in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/addAuthor.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1,author.getID());
            preparedStatement.setString(2,author.getFirstName());
            preparedStatement.setString(3,author.getLastName());
            preparedStatement.setString(4, author.getBirthDay().toString());
            if(author.getDeathDate() != null) {
                preparedStatement.setString(5, author.getDeathDate().toString());
            } else {
                preparedStatement.setString(5, null);
            }
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The author has been saved in the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during saving Author");
        }
        close();
    }

    public void addClient(Client client) throws SQLException {
        connectToDataBase();
        logger.info("An attempt to enter the Client in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/addClient.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1,client.getID());
            preparedStatement.setString(2,client.getFirstName());
            preparedStatement.setString(3,client.getLastName());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.setString(5, client.getEmailAddress());
            preparedStatement.setString(6, client.getAddress());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Client has been saved in the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during saving Client");
            throwables.printStackTrace();
        }
    }

    public ArrayList<Client> getListofClients() throws Exception {
        connectToDataBase();
        logger.info("Downloading Clients");
        var ClientList = new ArrayList<Client>();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/getAllClients.sql"))) {
            try {
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Client temp;
                        temp = new Client(resultSet.getInt(1),resultSet.getString(2)
                                ,resultSet.getString(3), resultSet.getString(4)
                                ,resultSet.getString(5),resultSet.getString(6));
                    ClientList.add(temp);
                }
                close();
                return ClientList;
            } catch (SQLException throwables) {
                logger.error("Error during getting list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Script");
            close();
            throw new StatementReadException();
        }
    }

    public void deleteClient(int ID) throws SQLException {
        connectToDataBase();
        logger.info("An attempt to delete the Client from the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/DeleteClient.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, String.valueOf(ID));

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Client has been deleted from the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during deleting Client");
            throwables.printStackTrace();
        }
    }



    @Override
    public void close() throws Exception {
        logger.info("Disconnect");
        connection.close();
        statement.close();
    }
}
