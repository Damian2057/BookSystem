package org.example.dao.Storage;

import org.example.Exceptions.Model.EmptyOrderList;
import org.example.Exceptions.Model.WrongOrderIDException;
import org.example.dao.ClassFactory;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class OrderStorage extends Storage<Order>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;
    public OrderStorage(String URL) throws Exception {
        this.URL = URL;
        for (Order a :
                ClassFactory.getJDBCBookSystem(URL).getAllofOrdersINIT()) {
            getAllElementsFromStorage().add(a);
        }
    }

    public Order getOrder(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        logger.error("Cannot Find Author with ID: "+ ID);
        throw new WrongOrderIDException();
    }

    public int getTopID() {
        if(getAllElementsFromStorage().isEmpty()) {
            return 0;
        } else {
            return getAllElementsFromStorage().get(getAllElementsFromStorage().size()-1).getID();
        }
    }

    public ArrayList<Order> getOrdersByClientID(int ID) {
        ArrayList<Order> temp = new ArrayList<>();
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getClientID()) {
                temp.add(getAllElementsFromStorage().get(i));
            }
        }
        if(temp.isEmpty()) {
            logger.error("The given customer has no orders");
            throw new EmptyOrderList();
        }
        logger.info("List of orders Complete");
        return temp;
    }

    public void addBookToOrder(int OrderID, Book book) throws Exception {
        getOrder(OrderID).addBookToOrder(book);
        ClassFactory.getJDBCBookSystem(URL).addBookToOrder(OrderID, book);
    }

    public void removeBookFromOrder(int OrderID, int bookID) throws Exception {
        getOrder(OrderID).removeBookFromOrder(bookID);
        ClassFactory.getJDBCBookSystem(URL).deleteBookInOrder(OrderID, bookID);
    }

    public double endOrder(int OrderID) throws Exception {
        double sum = 0;
        boolean flag = false;
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if(OrderID == getAllElementsFromStorage().get(i).getID()){
                sum = getOrder(OrderID).endOrder();
                ClassFactory.getJDBCBookSystem(URL).addClientOrderCount(getOrder(OrderID).getClientID());
                getAllElementsFromStorage().remove(i);
                ClassFactory.getJDBCBookSystem(URL).removeOrder(OrderID);
                flag = true;
            }
        }
        if(!flag) {
            throw new WrongOrderIDException();
        }
            return sum;
    }

    @Override
    public void addElement(Order obj) throws Exception {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
        ClassFactory.getJDBCBookSystem(URL).addOrder(obj);
    }

}
