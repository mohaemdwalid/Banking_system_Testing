package com.example.national_bank_of_egypt.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transactions {
    private final StringProperty sender;
    private final StringProperty receiver;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;
    public Transactions(String sender,String receiver,double amount,LocalDate date,String message){
        this.sender = new SimpleStringProperty(sender);
        this.receiver = new SimpleStringProperty(receiver);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<>(date);
        this.message = new SimpleStringProperty(message);
    }




    public StringProperty senderProperty() {
        return sender;
    }

    public StringProperty receiverProperty() {
        return receiver;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }



}