package org.example.model;

import java.time.LocalDate;
import java.util.Date;

public class Author {
    private int ID;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private LocalDate deathDate;

    public Author(int ID, String firstName, String lastName, LocalDate birthDay) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public Author(int ID,String firstName, String lastName, LocalDate birthDay, LocalDate deathDate) {
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public int getID() {
        return ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
