package com.example.national_bank_of_egypt.Views;

import com.example.national_bank_of_egypt.Controllers.Admin.AdminController;
import com.example.national_bank_of_egypt.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {

    private AccountType loginAccountType;

    private final ObjectProperty<clientmenuoption> clientSelectedMenuItem;
    private final ObjectProperty<AdminMenuOption> adminselectedmenuitem;
    private AnchorPane dashboardview;
    private AnchorPane accountsView;
    private AnchorPane transactionsView;
    private AnchorPane createClientViews;

    private AnchorPane RequestLoanView;

    private AnchorPane clientsView;

    private AnchorPane depositView;


    public ViewFactory(){
        this.loginAccountType =  AccountType.CLIENT;
        this.clientSelectedMenuItem =  new SimpleObjectProperty<>();
        this.adminselectedmenuitem =  new SimpleObjectProperty<>();
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public ObjectProperty<clientmenuoption> getClientSelectedMenuItem() {

        return clientSelectedMenuItem;
    }


    public AnchorPane getDashboardview(){
        if (dashboardview == null){
            try {
                dashboardview = new FXMLLoader(getClass().getResource("/fxml/Client/Dashboard.fxml")).load();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardview;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null){
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/fxml/Client/Transactions.fxml")).load();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {
        if (accountsView == null){
            try {
                accountsView = new FXMLLoader(getClass().getResource("/fxml/Client/Accounts.fxml")).load();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accountsView;
    }
    public AnchorPane getRequestLoanView() {
        if (RequestLoanView == null){
            try {
                RequestLoanView = new FXMLLoader(getClass().getResource("/fxml/Client/RequestLoan.fxml")).load();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return RequestLoanView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        CreateStage(loader);
    }
    public void showClinetWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        CreateStage(loader);
    }
//    Admin
    public  ObjectProperty<AdminMenuOption>  getAdminselectedmenuitem(){
        return adminselectedmenuitem;
    }
    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        CreateStage(loader);
    }
    public AnchorPane getDepositViews(){
        if (depositView == null){
            try {
                depositView = new FXMLLoader(getClass().getResource( "/Fxml/Admin/Deposit.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }

        } return depositView;
    }

    public AnchorPane getClientViews(){
        if (clientsView == null){
            try {
                clientsView = new FXMLLoader(getClass().getResource( "/Fxml/Admin/Clients.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }

        } return clientsView;
    }


    public AnchorPane getCreateClientViews(){
        if (createClientViews == null){
            try {
                createClientViews = new FXMLLoader(getClass().getResource( "/Fxml/Admin/CreateClient.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }

        } return createClientViews;
    }

    public  void showMessageWindow(String senderAdress , String messagetext){
        StackPane pane =new StackPane();
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        Label sender = new Label(senderAdress);
        Label message  = new Label(messagetext);
        vBox.getChildren().addAll(sender, message);
        pane.getChildren().add(vBox);
        Scene scene = new Scene(pane,300,100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }
    private void CreateStage(FXMLLoader loader){
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
//        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("National Bank of Egypt");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
