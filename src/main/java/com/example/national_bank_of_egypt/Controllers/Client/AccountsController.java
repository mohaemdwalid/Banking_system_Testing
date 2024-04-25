package com.example.national_bank_of_egypt.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

    }
}
