package com.example.national_bank_of_egypt.Controllers.Client;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Models.Transactions;
import com.example.national_bank_of_egypt.Views.TransactionCellFactory;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text userName;
    public Label login_date;
    public Label checking_bal;
    public Label checking_acc_num;
    public Label saving_bal;
    public Label saving_acc_num;
    public Label income_bal;
    public Label expenses_bal;
    public ListView<Transactions> Transaction_list;
    public TextField username_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowData();
        intiLatestTransactions();
        Transaction_list.setItems(Model.getInstance().getLatesTrans());
        Transaction_list.setCellFactory(e -> new TransactionCellFactory());
        accountSummary();
        send_btn.setOnAction(event->onSendMoney());
    }
    private void ShowData(){
        userName.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today, " + LocalDate.now());
        checking_bal.textProperty().bind(Bindings.concat("$").concat(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString()));
        saving_bal.textProperty().bind(Bindings.concat("$").concat(Model.getInstance().getClient().savingAccountProperty().get().balanceProperty().asString()));
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().account_numberProperty());
        saving_acc_num.textProperty().bind(Model.getInstance().getClient().savingAccountProperty().get().account_numberProperty());
    }
    private void intiLatestTransactions(){
        if (Model.getInstance().getLatesTrans().isEmpty()){
            Model.getInstance().setLatesTrans();
        }
    }
//    Method calculates all expenses and income
    private void accountSummary(){
        double income = 0;
        double expenses = 0;
        if (Model.getInstance().getAllTrans().isEmpty()){
            Model.getInstance().setAllTrans();
        }
        for (Transactions  trancaions:Model.getInstance().getAllTrans()){
            if (trancaions.senderProperty().get().equals(Model.getInstance().getClient().userNameProperty().get())){
                expenses+= trancaions.amountProperty().get();
            }else {
                income+= trancaions.amountProperty().get();
            }
        }
        income_bal.setText("+$"+income);
        expenses_bal.setText("-$"+expenses);
    }
    private void  onSendMoney(){
        String receiver = username_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().userNameProperty().get();
        ResultSet resultSet = Model.getInstance().getDataBaseDriver().searchClient(receiver);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDataBaseDriver().updateBalance(receiver, amount, "ADD");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            Model.getInstance().getDataBaseDriver().updateBalance(sender, amount, "SUB");

            Model.getInstance().getClient().savingAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getSavingsAccountBalance(sender));
            Model.getInstance().getDataBaseDriver().newTransaction(sender, receiver, amount, message);
            username_fld.setText("");
            amount_fld.setText("");
            message_fld.setText("");

    }
}
