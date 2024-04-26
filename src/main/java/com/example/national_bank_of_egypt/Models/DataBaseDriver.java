package com.example.national_bank_of_egypt.Models;
import java.sql.*;
import java.time.LocalDate;

public class DataBaseDriver {
    private Connection con;

    public DataBaseDriver(){
        try {
            this.con = DriverManager.getConnection("jdbc:sqlite:National_Bank_of_Egypt.db");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getClientDate(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery( "SELECT * FROM Clients WHERE PayeeAddress = '" + pAddress + "' AND Password='" + password + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public  ResultSet getTransaction(String pAddress,int limit){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender = '" + pAddress + "' OR Receiver ='" + pAddress + "' LIMIT "+limit+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAdminDate(String Username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery( "SELECT * FROM Admins WHERE Username = '" + Username + "' AND Password='" + password + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void createClient(String fName, String lName, String uName,String password, LocalDate dateCreated) {
        Statement statement;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("INSERT INTO Clients(FirstName, LastName, PayeeAddress,Password, Date) " +
                    "VALUES ('" + fName + "', '" + lName + "', '" + uName + "', '" + password + "', '" + dateCreated.toString() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        Statement statement;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("INSERT INTO CheckingAccounts(Owner, AccountNumber, TransactionLimit, Balance) " +
                    "VALUES ('" + owner + "', '" + number + "', " + tLimit + ", " + balance + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSavingAccount(String owner, String number, double wLimit, double balance) {
        Statement statement;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("INSERT INTO SavingsAccounts(Owner, AccountNumber, WithdrawalLimit, Balance) " +
                    "VALUES ('" + owner + "', '" + number + "', " + wLimit + ", " + balance + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllClientData(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients;");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Utility

    public int getLatClientsId(){
        Statement statement;
        ResultSet resultSet;
        int id  = 0;
        try {
            statement  = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='Clients';");
            id = resultSet.getInt("seq");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }
    public ResultSet getCheckingAccountData(String UserName){
        Statement statement;
        ResultSet resultSet =null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner='" + UserName + "';");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }
    //
    public ResultSet getSavingAccountData(String UserName){
        Statement statement;
        ResultSet resultSet =null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='" + UserName + "';");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }
    public  double getSavingsAccountBalance(String pAddress){
        Statement statement;
        ResultSet resultSet;
        double balance = 0 ;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
            balance = resultSet.getDouble("Balance");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return balance;
    }
    public  double getCheckingccountBalance(String pAddress){
        Statement statement;
        ResultSet resultSet;
        double balance = 0 ;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner='"+pAddress+"';");
            balance = resultSet.getDouble("Balance");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return balance;
    }
    // Add or subtract from balance
    public void updateBalanceSaving (String pAdress, double amount, String operation ){
        Statement statement;
        ResultSet resultSet;
        try {
            statement =this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner ='"+pAdress+"';");
            double newBalance;
            if (operation.equals("ADD"))  {
                 newBalance = resultSet.getDouble("Balance")+ amount;
                statement.executeUpdate("UPDATE SavingsAccounts SET Balance = " + newBalance + " WHERE Owner = '" + pAdress + "';");

            }else {
                if (resultSet.getDouble("Balance")>=amount){
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE SavingsAccounts SET Balance = " + newBalance + " WHERE Owner='" + pAdress + "';");

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateBalanceChecking (String pAdress, double amount, String operation ){
        Statement statement;
        ResultSet resultSet;
        try {
            statement =this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner ='"+pAdress+"';");
            double newBalance;
            if (operation.equals("ADD"))  {
                newBalance = resultSet.getDouble("Balance")+ amount;
                statement.executeUpdate("UPDATE CheckingAccounts SET Balance = " + newBalance + " WHERE Owner = '" + pAdress + "';");

            }else {
                if (resultSet.getDouble("Balance")>=amount){
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE CheckingAccounts SET Balance = " + newBalance + " WHERE Owner='" + pAdress + "';");

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //creates and records new Transaction
    public void newTransaction(String sender, String receiver , double amount , String message){
        Statement statement;
        try {
            statement = this.con.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO"+
                    " Transactions (Sender,Receiver, Amount, Date, Message)"+
                    "VALUES('"+sender+"','"+receiver+"',"+amount+",'"+date+"','"+message+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public ResultSet searchClient(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress = '"+pAddress+"';");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void DepositSaving(String Username, double amount) {
        Statement statement;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("UPDATE SavingsAccounts SET Balance = " + amount + " WHERE Owner = '" + Username + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void RequestLoan(String Username, double amount){
        Statement statement;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("UPDATE CheckingAccounts SET Balance = " + amount + " WHERE Owner = '" + Username + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
