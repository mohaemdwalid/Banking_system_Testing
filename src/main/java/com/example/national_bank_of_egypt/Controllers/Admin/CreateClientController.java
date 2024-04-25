package com.example.national_bank_of_egypt.Controllers.Admin;

import com.example.national_bank_of_egypt.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable{



    public Label pAddress_lbl;
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public Text sc_amount_;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;
    private String payeeAddress;
    private boolean CreateCheckingAccountFlag = false;
    private boolean CreateSavingAccountFlag = false;

    private void createClient(){
        if (CreateCheckingAccountFlag){
            createAccount("Checking");
        }
        if (CreateSavingAccountFlag){
            createAccount("Savings");
        }
        String fName = fName_fld.getText();
        String LName = lName_fld.getText();
        String password = password_fld.getText();
        Model.getInstance().getDataBaseDriver().createClient(fName,LName,payeeAddress,password, LocalDate.now());
        error_lbl.setText("Done");
        emptyFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(actionEvent -> createClient());
        pAddress_box.selectedProperty().addListener((observableValue, OldVal, newVal) -> {
            if (newVal){
                payeeAddress = createpayeeAddress();
                onCreatePayeeAddress();
            }
        });
            ch_acc_box.selectedProperty().addListener((observableValue1, OldV, newV) -> {
                if (newV){
                    CreateCheckingAccountFlag = true;
                }
        });
        sv_acc_box.selectedProperty().addListener((observableValue, OldVal, newVal) -> {
            if(newVal){
                CreateSavingAccountFlag = true;
            }
        });

    }
    private void createAccount(String accountType){
        double balancec = Double.parseDouble(ch_amount_fld.getText());
        double balances = Double.parseDouble(sv_amount_fld.getText());
        String firstSection = "3201";
        String lastSection = Integer.toString((new Random()).nextInt(9999)+ 1000);
        String accountNumber = firstSection + " " + lastSection;
        if (accountType.equals("Checking")){
            Model.getInstance().getDataBaseDriver().createCheckingAccount(payeeAddress,accountNumber,10,balancec);
        }else {
            Model.getInstance().getDataBaseDriver().createSavingAccount(payeeAddress,accountNumber,2000,balances);
        }
    }

    private void onCreatePayeeAddress(){
        if (fName_fld.getText() != null & lName_fld.getText() != null){
            pAddress_lbl.setText(payeeAddress);
        }
    }

    private String createpayeeAddress(){
        int id = Model.getInstance().getDataBaseDriver().getLatClientsId() + 1;
        char fChar = Character.toLowerCase(fName_fld.getText().charAt(0));
        return "@"+fChar+lName_fld.getText()+id;
    }

    private void emptyFields(){
        fName_fld.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_amount_fld.setText("");
        ch_acc_box.setSelected(false);
        sv_acc_box.setSelected(false);
        sv_amount_fld.setText("");
    }
}
