package com.example.national_bank_of_egypt.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingAccount extends Account{
    private final DoubleProperty withdrawLimit;
    public SavingAccount(String owner, String account_number, double balance,double withdrawLimit) {
        super(owner, account_number, balance);
        this.withdrawLimit = new SimpleDoubleProperty(this,"withdraw Limit",withdrawLimit);
    }

    public DoubleProperty withdrawLimitProperty() {
        return withdrawLimit;
    }

    @Override
    public String toString() {
        return account_numberProperty().get();
    }
}
