package org.example.dao.Storage;

import org.example.Exceptions.Model.EmptyOrderList;
import org.example.Exceptions.Model.WrongOrderIDException;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class OrderStorage extends Storage<Order>{

    private String URL;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public OrderStorage(String URL) {
        this.URL = URL;
        initwithBase(URL);
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

    @Override
    public void addElement(Order obj) {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
    }

    @Override
    public void initwithBase(String URL) {

    }

}
