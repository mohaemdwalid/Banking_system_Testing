package com.example.national_bank_of_egypt.Models;

import com.example.national_bank_of_egypt.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DataBaseDriver dataBaseDriver;
    private final ObservableList<Client> clients;

    private Client client;
    private final ObservableList<Transactions> latesTrans;
    private final ObservableList<Transactions> allTrans;
    private Boolean clientLoginSuccessFlag;
    private Boolean AdminLoginSuccessFlag;

    public Model(){
        this.viewFactory = new ViewFactory();
        this.dataBaseDriver = new DataBaseDriver();
        this.clientLoginSuccessFlag =false;
        this.AdminLoginSuccessFlag = false;
        this.client = new Client("","","",null,null,null);
        this.latesTrans = FXCollections.observableArrayList();
        this.allTrans = FXCollections.observableArrayList();
        this.clients = FXCollections.observableArrayList();

    }
    public static synchronized Model getInstance(){
        if (model == null){
            model = new Model();
        }
        return model;
    }
    // GETTER
    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DataBaseDriver getDataBaseDriver() {
        return dataBaseDriver;
    }

    public Boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }
    public Client getClient() {
        return client;
    }

    public ObservableList<Transactions> getAllTrans() {
        return allTrans;
    }

    public ObservableList<Transactions> getLatesTrans(){
        return latesTrans;
    }

    public Boolean getAdminLoginSuccessFlag() {
        return this.AdminLoginSuccessFlag;
    }
    public ObservableList<Client> getClients() {
        return clients;
    }

    public  CheckingAccount getCheckingAccount(String PAddress){
        CheckingAccount account = null;
        ResultSet resultSet = dataBaseDriver.getCheckingAccountData(PAddress);
        try {
            String num = resultSet.getString("AccountNumber");
            int tLimit = (int)resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new CheckingAccount(PAddress,num,balance,tLimit);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    public  SavingAccount getSavingAccount(String PAddress){
        SavingAccount account = null;
        ResultSet resultSet = dataBaseDriver.getSavingAccountData(PAddress);
        try {
            String num = resultSet.getString("AccountNumber");
            double wLimit = (int)resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            account = new SavingAccount(PAddress,num,balance,wLimit);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public void setClientLoginSuccessFlag(Boolean clientLoginSuccessFlag) {
        this.clientLoginSuccessFlag = clientLoginSuccessFlag;
    }

    public void evaluateClientCred(String userName, String password) {
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet resultSet = dataBaseDriver.getClientDate(userName, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.userNameProperty().set(resultSet.getString("PayeeAddress"));
                String[] dataParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dataParts[0]), Integer.parseInt(dataParts[1]), Integer.parseInt(dataParts[2]));
                this.client.dataCreatedProperty().set(date);
                checkingAccount =getCheckingAccount(userName);
                savingAccount = getSavingAccount(userName);
                this.client.checkingAccountProperty().set(checkingAccount);
                this.client.savingAccountProperty().set(savingAccount);
                this.clientLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setLatesTrans(){
        prepareTransactions(this.latesTrans,4);
    }
    public void setAllTrans(){

        prepareTransactions(this.allTrans,-1);
    }



    public void setAdminLoginSuccessFlag(Boolean adminLoginSuccessFlag) {
        AdminLoginSuccessFlag = adminLoginSuccessFlag;
    }
    public void evaluateAdminCred(String userName, String password) {
        ResultSet resultSet = dataBaseDriver.getAdminDate(userName, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.AdminLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setClient() {
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet resultSet = dataBaseDriver.getAllClientData();
        try {
            while (resultSet.next()){
                String fname = resultSet.getString("FirstName");
                String lname = resultSet.getString("LastName");
                String PAddress = resultSet.getString("PayeeAddress");
                String[] dataParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dataParts[0]), Integer.parseInt(dataParts[1]), Integer.parseInt(dataParts[2]));
                checkingAccount =getCheckingAccount(PAddress);
                savingAccount = getSavingAccount(PAddress);
                clients.add(new Client(fname,lname,PAddress,checkingAccount,savingAccount,date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Client> searchClient(String UserName){
        ObservableList<Client> searchClient = FXCollections.observableArrayList();
        ResultSet resultSet = dataBaseDriver.searchClient(UserName);
        try {
            CheckingAccount checkingAccount =getCheckingAccount(UserName);
            SavingAccount savingAccount = getSavingAccount(UserName);
            String Fname = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dataParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dataParts[0]), Integer.parseInt(dataParts[1]), Integer.parseInt(dataParts[2]));
            searchClient.add(new Client(Fname,lName,UserName,checkingAccount,savingAccount,date));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return searchClient;
    }
    public void prepareTransactions(ObservableList<Transactions> transactions,int limit){
        ResultSet resultSet = dataBaseDriver.getTransaction(this.client.userNameProperty().get(),limit);
        try {
            while (resultSet.next()){
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] dataParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dataParts[0]), Integer.parseInt(dataParts[1]), Integer.parseInt(dataParts[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transactions(sender, receiver, amount,date,message));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
