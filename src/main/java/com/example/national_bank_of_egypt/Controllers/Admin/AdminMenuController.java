package com.example.national_bank_of_egypt.Controllers.Admin;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Views.AdminMenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button logout_btn;
    public Button deposit_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners(){
        logout_btn.setOnAction(actionEvent -> onLogout());
        create_client_btn.setOnAction((actionEvent -> onCreateClient()));
        clients_btn.setOnAction(actionEvent -> onClients());
        deposit_btn.setOnAction(actionEvent -> onDeposit());
    }

    private  void onCreateClient (){
        Model.getInstance().getViewFactory().getAdminselectedmenuitem().set(AdminMenuOption.CREATE_CLIENT);
    }
    private void onClients(){
        Model.getInstance().getViewFactory().getAdminselectedmenuitem().set(AdminMenuOption.CLIENT);

    }
    private void onDeposit(){
        Model.getInstance().getViewFactory().getAdminselectedmenuitem().set(AdminMenuOption.DEPOSIT);

    }

    private void onLogout() {
        Stage stage = (Stage) clients_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }


}
