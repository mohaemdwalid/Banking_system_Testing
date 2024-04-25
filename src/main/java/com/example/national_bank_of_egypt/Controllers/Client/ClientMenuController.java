package com.example.national_bank_of_egypt.Controllers.Client;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Views.clientmenuoption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    public Button loan_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addlisteners();

    }

    private void addlisteners(){
        dashboard_btn.setOnAction(actionEvent -> onDashboard());
        loan_btn.setOnAction(actionEvent -> onRequestLoan());
        transaction_btn.setOnAction(actionEvent -> onTransactions());
        accounts_btn.setOnAction(actionEvent -> onAccounts());
        logout_btn.setOnAction(actionEvent -> onLogout());

    }



    private void onLogout() {
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setClientLoginSuccessFlag(false);
    }



    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientmenuoption.ACCOUNT);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientmenuoption.TRANSACTION);
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientmenuoption.DASHBOARD);
    }
    private void onRequestLoan(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientmenuoption.REQUESTLOAN);
    }
}
