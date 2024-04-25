package com.example.national_bank_of_egypt.Controllers.Client;

import com.example.national_bank_of_egypt.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {

            switch (newVal){
                case TRANSACTION -> client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionsView());
                case ACCOUNT -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                case REQUESTLOAN -> client_parent.setCenter(Model.getInstance().getViewFactory().getRequestLoanView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardview());
            }
        });

    }
}
