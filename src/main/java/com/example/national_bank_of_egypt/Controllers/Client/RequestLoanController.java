package com.example.national_bank_of_egypt.Controllers.Client;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RequestLoanController implements Initializable {

    public TextField loan_amount;
    public Button btn_request;
    public Label Done_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_request.setOnAction(actionEvent -> onRequestLoan());
    }

    public void onRequestLoan() {
        String username = Model.getInstance().getClient().getUserName();
        double amount = Double.parseDouble(loan_amount.getText());
        double newAddedBalance = amount + Model.getInstance().getClient().getCheckingAccount().balanceProperty().get();
        Model.getInstance().getDataBaseDriver().RequestLoan(username, newAddedBalance);
        Model.getInstance().getClient().checkingAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getCheckingccountBalance(username));
        loan_amount.clear();
        Done_lbl.setText("Done");

    }
}
