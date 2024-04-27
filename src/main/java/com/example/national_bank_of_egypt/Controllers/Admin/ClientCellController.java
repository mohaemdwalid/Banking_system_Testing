package com.example.national_bank_of_egypt.Controllers.Admin;

import com.example.national_bank_of_egypt.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fName_lbl;
    public Label lName_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label data_lbl;

    public Label UserName_lbl;
    public Button delete_btn;
    private final Client client;
    public ClientCellController(Client client){
        this.client= client ;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_lbl.textProperty().bind(client.firstNameProperty());
        lName_lbl.textProperty().bind(client.lastNameProperty());
        UserName_lbl.textProperty().bind(client.userNameProperty());
        ch_acc_lbl.textProperty().bind(client.checkingAccountProperty().asString());
        sv_acc_lbl.textProperty().bind(client.savingAccountProperty().asString());
        data_lbl.textProperty().bind(client.dataCreatedProperty().asString());

    }
}
