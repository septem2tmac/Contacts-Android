package com.septem.contact;

import java.util.Comparator;

/**
 * Created by Septem on 2016/3/24.
 */
public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String date;

    public User(String firstName, String lastName, String phoneNumber, String email, String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date = date;
    }

    public User(String[] user) {
        firstName = user[0];
        lastName = user[1];
        phoneNumber = user[2];
        email = user[3];
        date = user[4];
    }

    public String Send() {
        return firstName + "\t" + lastName + "\t" + phoneNumber + "\t" + email + "\t" + date + "\n";
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

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(String date) {
        this.date = date;
    }
}