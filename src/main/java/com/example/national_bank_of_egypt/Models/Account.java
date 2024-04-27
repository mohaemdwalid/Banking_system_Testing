package com.example.national_bank_of_egypt.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private final StringProperty owner;
    private final StringProperty account_number;
    private final DoubleProperty balance;

    public Account(String owner, String account_number, double balance) {
        this.owner = new SimpleStringProperty(this,"Owner",owner);
        this.account_number = new SimpleStringProperty(this,"Account Number",account_number);
        this.balance = new SimpleDoubleProperty(this,"Balance",balance);
    }
    public double setBalance(double balance) {
        this.balance.set(balance);
        return balance;
    }
    public StringProperty ownerProperty() {
        return owner;
    }
    public StringProperty account_numberProperty() {
        return account_number;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

}
