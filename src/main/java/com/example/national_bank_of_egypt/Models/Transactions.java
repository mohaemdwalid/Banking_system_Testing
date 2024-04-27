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

    public StringProperty messageProperty(){return  message;}

    public String getSender() {
        return sender.get();
    }

    public void setSender(String sender) {
        this.sender.set(sender);
    }

    public String getReceiver() {
        return receiver.get();
    }

    public void setReceiver(String receiver) {
        this.receiver.set(receiver);
    }

    public double getAmount() {
        return amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getMessage() {
        return message.get();
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
}