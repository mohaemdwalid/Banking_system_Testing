package com.example.national_bank_of_egypt.Controllers.Client;

import com.example.national_bank_of_egypt.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_balance;
    public Label sv_acc_num;
    public Label withdraw_limit;
    public Label sv_acc_date;
    public Label sv_acc_balance;
    public TextField amount_to_sv;
    public Button transfer_to_sv_btn;
    public Button transfer_to_ch_btn;
    public TextField amount_to_ch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowData();
        transfer_to_sv_btn.setOnAction(actionEvent -> TransferToSaving());
        transfer_to_ch_btn.setOnAction(actionEvent -> TransferToChecking());
    }
    String userName = Model.getInstance().getClient().getUserName().toString();

    private void ShowData(){
        ch_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().account_numberProperty());
        transaction_limit.textProperty().bind(Model.getInstance().getSavingAccount(userName).withdrawLimitProperty().asString());
        ch_acc_date.textProperty().bind(Model.getInstance().getClient().dataCreatedProperty().asString());
        ch_acc_balance.textProperty().bind(Bindings.concat("$").concat(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString()));
        sv_acc_num.textProperty().bind(Model.getInstance().getClient().savingAccountProperty().get().account_numberProperty());
        withdraw_limit.textProperty().bind(Model.getInstance().getCheckingAccount(userName).TransactionLimitProp().asString());
        sv_acc_date.textProperty().bind(Model.getInstance().getClient().dataCreatedProperty().asString());
        sv_acc_balance.textProperty().bind(Bindings.concat("$").concat(Model.getInstance().getClient().savingAccountProperty().get().balanceProperty().asString()));

    }


    private void TransferToSaving(){
        double amount = Double.parseDouble(amount_to_sv.getText());
        ResultSet resultSet = Model.getInstance().getDataBaseDriver().searchClient(userName);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDataBaseDriver().updateBalanceSaving(userName, amount, "ADD");
                Model.getInstance().getDataBaseDriver().updateBalanceChecking(userName, amount, "SUB");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Model.getInstance().getClient().savingAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getSavingsAccountBalance(userName));
        Model.getInstance().getClient().checkingAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getCheckingccountBalance(userName));
        amount_to_sv.setText("");
    }
    private void TransferToChecking(){
        double amount = Double.parseDouble(amount_to_ch.getText());
        ResultSet resultSet = Model.getInstance().getDataBaseDriver().searchClient(userName);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDataBaseDriver().updateBalanceChecking(userName, amount, "ADD");
                Model.getInstance().getDataBaseDriver().updateBalanceSaving(userName, amount, "SUB");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Model.getInstance().getClient().savingAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getSavingsAccountBalance(userName));
        Model.getInstance().getClient().checkingAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getCheckingccountBalance(userName));
        amount_to_ch.setText("");
    }

}
