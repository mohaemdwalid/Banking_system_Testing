package com.example.national_bank_of_egypt.Controllers;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label username_lbl;
    public TextField Username_fld;
    public PasswordField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable ->setAcc_selector());
        login_btn.setOnAction(actionEvent ->  onLogin());
    }

    private void onLogin(){
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            Model.getInstance().evaluateClientCred(Username_fld.getText(), password_fld.getText());
            if (Model.getInstance().getClientLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showClinetWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                Username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such a User.");
            }
        }else {

            Model.getInstance().evaluateAdminCred(Username_fld.getText(), password_fld.getText());
            if (Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                Username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such a User.");
            }
        }
    }
    private void  setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        if (acc_selector.getValue() == AccountType.ADMIN){
            username_lbl.setText("AdminName");
        }else {
            username_lbl.setText("UserName");

        }
    }
}
