package org.example.Storage;

import org.example.Exceptions.Model.WrongOrderIDException;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class OrderStorage extends Storage<Order>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Order getOrder(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        logger.error("Cannot Find Author with ID: "+ ID);
        throw new WrongOrderIDException();
    }

    public ArrayList<Order> getOrdersByClientID(int ID) {
        ArrayList<Order> temp = new ArrayList<>();
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getClientID()) {
                temp.add(getAllElementsFromStorage().get(i));
            }
        }
        return temp;
    }

    @Override
    public void addElement(Order obj) {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
    }

}
