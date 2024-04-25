package com.example.national_bank_of_egypt.Controllers.Client;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Views.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView Transaction_ListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        intialAllTransactionList();
        Transaction_ListView.setItems(Model.getInstance().getAllTrans());
        Transaction_ListView.setCellFactory(e -> new TransactionCellFactory());

    }

    private void intialAllTransactionList(){
        if (Model.getInstance().getAllTrans().isEmpty()){
            Model.getInstance().getAllTrans();
        }
    }


}
