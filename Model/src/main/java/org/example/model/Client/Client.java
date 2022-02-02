package org.example.model.Client;

import org.example.model.Client.types.Normal;
import org.example.model.Client.types.Premium;
import org.example.model.Client.types.Type;

public class Client {
    private final int ID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String Address;

    private int OrderCount = 0;
    private Type type = new Normal();

    public Client(int ID,String firstName, String lastName, String phoneNumber, String emailAddress, String address) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        Address = address;
    }

    public double getReduction() {
        return type.getReduction();
    }

    public int getID() {
        return ID;
    }

    public int getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(int orderCount) {
        OrderCount = orderCount;
        setType();
    }

    public void addOrderCount() {
        OrderCount++;
        setType();
    }

    private void setType() {
        if (getOrderCount() >= 5) {
            this.type = new Premium();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return Address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
