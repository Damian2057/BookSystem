package org.example.model;

import java.util.Date;

public class Author {
    private int ID;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Date deathDate;

    public Author(int ID, String firstName, String lastName, Date birthDay) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public Author(int ID,String firstName, String lastName, Date birthDay, Date deathDate) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.deathDate = deathDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public int getID() {
        return ID;
    }
}
