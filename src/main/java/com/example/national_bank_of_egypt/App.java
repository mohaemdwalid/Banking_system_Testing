package com.example.national_bank_of_egypt;

import com.example.national_bank_of_egypt.Models.Model;
import com.example.national_bank_of_egypt.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application  {
    @Override
    public void start(Stage stage)  {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
