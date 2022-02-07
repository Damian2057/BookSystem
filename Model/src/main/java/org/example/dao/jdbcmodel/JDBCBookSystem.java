package org.example.dao.jdbcmodel;

import org.example.Exceptions.Dao.*;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class JDBCBookSystem implements AutoCloseable{

    private static String URL;
    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JDBCBookSystem(String URL) throws Exception {
        JDBCBookSystem.URL = URL;
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
            statement.execute(readstatement("@../../SQLStatements/AuthorTablecreate.sql"));
            statement.execute(readstatement("@../../SQLStatements/BookTablecreate.sql"));
            statement.execute(readstatement("@../../SQLStatements/ClientTablecreate.sql"));
            statement.execute(readstatement("@../../SQLStatements/OrderTablecreate.sql"));
            close();
        } catch (Exception throwables) {
            throw new DataBaseCreateException();
        }
    }

    public ArrayList<Author> getListofAuthors() throws Exception {
        connectToDataBase();
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
                logger.error("Error during getting list of Authors");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Author SQL Script");
            close();
            throw new StatementReadException();
        }
    }

    public void addAuthor(Author author) throws Exception {
        connectToDataBase();
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

    public void updateAuthor(int ID, String newData ,String partToUpdate) throws SQLException {
        connectToDataBase();
        logger.info("An attempt of update Author in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/updateAuthor"+ partToUpdate +".sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, newData);
            preparedStatement.setString(2,String.valueOf(ID));
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Author has been Updated");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during update");
        } catch (Exception e) {
            logger.error("Something goes wrong during update");
            throw new StatementReadException();
        }
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
            preparedStatement.setInt(7, client.getOrderCount());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Client has been saved in the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during saving Client");
            throw new StatementReadException();
        }
    }

    public ArrayList<Client> getListofClients() throws Exception {
        connectToDataBase();
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
                        temp.setOrderCount(resultSet.getInt(7));
                    ClientList.add(temp);
                }
                close();
                return ClientList;
            } catch (SQLException throwables) {
                logger.error("Error during getting Client list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Client SQL Script");
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
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during Client removal");
        } catch (Exception e) {
            logger.error("Something goes wrong during Client removal");
            throw new StatementReadException();
        }
    }

    public void UpdateClient(int ID, String newData ,String partToUpdate) throws SQLException {
        connectToDataBase();
        logger.info("An attempt of update Client in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/updateClient"+ partToUpdate +".sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, newData);
            preparedStatement.setString(2,String.valueOf(ID));
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Client has been Updated");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during update");
        } catch (Exception e) {
            logger.error("Something goes wrong during update");
            throw new StatementReadException();
        }
    }

    public void addBook(Book book) throws SQLException {
        connectToDataBase();
        logger.info("An attempt to enter the Book in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/addBook.sql"))) {

            connection.setAutoCommit(false);
            try {
                preparedStatement.setInt(1,book.getID());
                preparedStatement.setString(2,book.getTitle());
                preparedStatement.setInt(3,book.getAuthor().getID());
                preparedStatement.setString(4, book.getPublishDate().toString());
                preparedStatement.setInt(5, book.getPageCount());
                preparedStatement.setDouble(6, book.getBasicOrderPrice());
                preparedStatement.setInt(7, btoi(book.isAccessible()));

                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throw new ReferenceException();
            }
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Book has been saved in the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during saving Book");
            throw new StatementReadException();
        }
    }

    public ArrayList<Book> getListofBooks() throws Exception {
        connectToDataBase();
        var BookList = new ArrayList<Book>();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/getAllBooks.sql"))) {
            try {
                var resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Book temp;
                    temp = new Book(resultSet.getInt(1),resultSet.getString(2), getAuthorByID(resultSet.getInt(3))
                            ,LocalDate.parse(resultSet.getString(4)), resultSet.getInt(5),resultSet.getDouble(6));
                    temp.setAccessible(itob(resultSet.getInt(7)));
                    BookList.add(temp);
                }
                close();
                return BookList;
            } catch (SQLException throwables) {
                logger.error("Error during getting book list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Book SQL Script");
            close();
            throw new StatementReadException();
        }
    }

    public void UpdateBook(int ID, int newData ,String partToUpdate) throws SQLException {
        connectToDataBase();
        logger.info("An attempt of update Book in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/updateBook"+ partToUpdate +".sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, newData);
            preparedStatement.setString(2,String.valueOf(ID));
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Book has been Updated");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during update");
        } catch (Exception e) {
            logger.error("Something goes wrong during update");
            throw new StatementReadException();
        }
    }

    public void addOrder(Order order) throws SQLException {
        connectToDataBase();
        logger.info("An attempt to enter the Order in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/addOrder.sql"))) {

            connection.setAutoCommit(false);
            try {
                for(int i = 0; i < order.getCountOfOrderedBooks(); i++) {
                    preparedStatement.setInt(1,order.getID());
                    preparedStatement.setInt(2,order.getClientID());
                    preparedStatement.setString(3,order.getStartReservationdate().toString());
                    preparedStatement.setString(4, order.getEndReservationDate().toString());
                    preparedStatement.setInt(5, order.getListofBooks().get(i).getID());
                    preparedStatement.setInt(6, btoi(order.isCompleted()));

                    preparedStatement.executeUpdate();

                }
            } catch (SQLException throwables) {
                throw new ReferenceException();
            }
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Order has been saved in the database");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during saving Order");
            throw new StatementReadException();
        }
    }

    public ArrayList<Order> getAllofOrders() throws Exception {
        connectToDataBase();
        var OrderList = new ArrayList<Order>();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/getAllOrders.sql"))) {
            try {
                var resultSet = preparedStatement.executeQuery();
                int index = 0;
                while (resultSet.next()) {
                    Order temp = null;
                    if(OrderList.isEmpty()) {
                        temp = new Order(resultSet.getInt(1),getClientbyID(resultSet.getInt(2))
                                ,LocalDate.parse(resultSet.getString(3)),LocalDate.parse(resultSet.getString(4)));
                        temp.addBookToOrder(getBookbyID(resultSet.getInt(5)));
                        temp.setCompleted(itob(resultSet.getInt(6)));
                        index ++;
                        OrderList.add(temp);
                    } else if(OrderList.get(index-1).getID() == resultSet.getInt(1)) {
                        OrderList.get(index-1).addBookToOrder(getBookbyID(resultSet.getInt(5)));
                    } else {
                        temp = new Order(resultSet.getInt(1),getClientbyID(resultSet.getInt(2))
                                ,LocalDate.parse(resultSet.getString(3)),LocalDate.parse(resultSet.getString(4)));
                        temp.addBookToOrder(getBookbyID(resultSet.getInt(5)));
                        temp.setCompleted(itob(resultSet.getInt(6)));
                        index ++;
                        OrderList.add(temp);
                    }
                }
                close();
                return OrderList;
            } catch (SQLException throwables) {
                logger.error("Error during getting Order list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Order SQL Script");
            close();
            throw new StatementReadException();
        }
    }

    public ArrayList<Order> getClientOrders(int ID) throws Exception {
        connectToDataBase();
        var OrderList = new ArrayList<Order>();
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/getclientOrders.sql"))) {
            try {
                preparedStatement.setInt(1, ID);
                var resultSet = preparedStatement.executeQuery();
                int index = 0;
                while (resultSet.next()) {
                    Order temp = null;
                    if(OrderList.isEmpty()) {
                        temp = new Order(resultSet.getInt(1),getClientbyID(resultSet.getInt(2))
                                ,LocalDate.parse(resultSet.getString(3)),LocalDate.parse(resultSet.getString(4)));
                        temp.addBookToOrder(getBookbyID(resultSet.getInt(5)));
                        temp.setCompleted(itob(resultSet.getInt(6)));
                        index ++;
                        OrderList.add(temp);
                    } else if(OrderList.get(index-1).getID() == resultSet.getInt(1)) {
                        OrderList.get(index-1).addBookToOrder(getBookbyID(resultSet.getInt(5)));
                    } else {
                        temp = new Order(resultSet.getInt(1),getClientbyID(resultSet.getInt(2))
                                ,LocalDate.parse(resultSet.getString(3)),LocalDate.parse(resultSet.getString(4)));
                        temp.addBookToOrder(getBookbyID(resultSet.getInt(5)));
                        temp.setCompleted(itob(resultSet.getInt(6)));
                        index ++;
                        OrderList.add(temp);
                    }
                }
                close();
                return OrderList;
            } catch (SQLException throwables) {
                logger.error("Error during getting Client Order list");
                close();
                throw new DownloadDataException();
            }
        } catch (SQLException throwables) {
            logger.error("Error connected with Client Order SQL Script");
            close();
            throw new StatementReadException();
        }
    }

    public void deleteBookInOrder(int OrderID, int BookID) throws SQLException {
        connectToDataBase();
        logger.info("An attempt of update Order in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/updateOrderdelete.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, OrderID);
            preparedStatement.setInt(2,BookID);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Book from order has been deleted");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during update");
        } catch (Exception e) {
            logger.error("Something goes wrong during update");
            throw new StatementReadException();
        }
    }

    public void addBookToOrder(int OrderID, Book book) throws Exception {
        Order temp = getOrderByID(OrderID);
        connectToDataBase();
        logger.info("An attempt of update Order in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/addOrder.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1,OrderID);
            preparedStatement.setInt(2,temp.getClientID());
            preparedStatement.setString(3, temp.getStartReservationdate().toString());
            preparedStatement.setString(4, temp.getEndReservationDate().toString());
            preparedStatement.setInt(5, book.getID());
            preparedStatement.setInt(6, btoi(temp.isCompleted()));

            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Book has been added");
            close();
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during update");
        } catch (Exception e) {
            logger.error("Something goes wrong during update");
            throw new StatementReadException();
        }
    }

    public void removeOrder(int orderID) throws SQLException {
        connectToDataBase();
        logger.info("An attempt to remove the Order in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/removeOrder.sql"))) {

            connection.setAutoCommit(false);
            try {
                preparedStatement.setInt(1, orderID);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throw new ReferenceException();
            }
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Order has been deleted");
        } catch (SQLException throwables) {
            connection.rollback();
            logger.error("Something goes wrong during removing Order");
            throw new StatementReadException();
        }
    }

    public void UpdateOrderStatus(int orderID, boolean status) throws SQLException {
        connectToDataBase();
        logger.info("An attempt of update Order status in the database");
        try(PreparedStatement preparedStatement = connection
                .prepareStatement(readstatement("@../../SQLStatements/updateOrderStatus.sql"))) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, btoi(status));
            preparedStatement.setInt(2, orderID);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("The Order status has been Updated");
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

    private Author getAuthorByID(int ID) throws Exception {
        for(int i = 0; i < getListofAuthors().size(); i++) {
            if(ID == getListofAuthors().get(i).getID()) {
                return getListofAuthors().get(i);
            }
        }
        return null;
    }

    public Order getOrderByID(int ID) throws Exception {
        for(int i = 0; i < getAllofOrders().size(); i++) {
            if(ID == getAllofOrders().get(i).getID()) {
                return getAllofOrders().get(i);
            }
        }
        return null;
    }

    private Client getClientbyID(int ID) throws Exception {
        for(int i = 0; i < getListofClients().size(); i++) {
            if(ID == getListofClients().get(i).getID()) {
                return getListofClients().get(i);
            }
        }
        return null;
    }

    private Book getBookbyID(int ID) throws Exception {
        for(int i = 0; i < getListofBooks().size(); i++) {
            if(ID == getListofBooks().get(i).getID()) {
                return getListofBooks().get(i);
            }
        }
        return null;
    }

    private int btoi(boolean b) {
        if(b){
            return 1;
        } else{
            return 0;
        }
    }

    private boolean itob(int i) {
        return i == 1;
    }
}
