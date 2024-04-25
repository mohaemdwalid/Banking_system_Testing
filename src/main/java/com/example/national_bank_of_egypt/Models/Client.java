package com.example.national_bank_of_egypt.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Locale;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty UserName;

    private final ObjectProperty<Account> CheckingAccount;
    private final ObjectProperty<Account> SavingAccount;
    private final ObjectProperty<LocalDate> dataCreated;

    public Client(String fName, String lName, String uName, Account checkingAccount, Account savingAccount, LocalDate dateCreated) {
        this.firstName = new SimpleStringProperty(this, "firstName", fName);
        this.lastName = new SimpleStringProperty(this, "lastName", lName);
        this.UserName = new SimpleStringProperty(this, "userName", uName);
        this.CheckingAccount = new SimpleObjectProperty<>(this, "CheckingAccounts", checkingAccount);
        this.SavingAccount = new SimpleObjectProperty<>(this, "SavingsAccounts", savingAccount);
        this.dataCreated = new SimpleObjectProperty<>(this, "dateCreated", dateCreated);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public StringProperty userNameProperty() {
        return UserName;
    }
    public ObjectProperty<LocalDate> dataCreatedProperty() {
        return dataCreated;
    }

    public ObjectProperty<Account> checkingAccountProperty() {

        return CheckingAccount;
    }
    public ObjectProperty<Account> savingAccountProperty() {

        return SavingAccount;
    }

    public Account getCheckingAccount() {
        return CheckingAccount.get();
    }

    public Account getSavingAccount() {
        return SavingAccount.get();
    }
}
