package com.example.national_bank_of_egypt.Controllers.Admin;

import com.example.national_bank_of_egypt.Models.Client;
import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView<Client> Clients_ListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetData();
        Clients_ListView.setItems(Model.getInstance().getClients());
        Clients_ListView.setCellFactory(e -> new ClientCellFactory());
    }

    private void SetData(){
        if(Model.getInstance().getClients().isEmpty()){
            Model.getInstance().setClient();
        }
    }
}
