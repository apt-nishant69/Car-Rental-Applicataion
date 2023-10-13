package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.io.IOException;

public class App extends Application {


    private static Scene sceneMain;
    private static Scene sceneDriver;
    private static Scene sceneUser;

    private static Stage stage;
    private static DataHandler data;



    @Override
    public void start(Stage stage) throws IOException {
        data = new DataHandler("driverlist.bin", "records.bin");

        Parent rootMain = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent rootDriver = FXMLLoader.load(getClass().getResource("DriverPortal.fxml"));
        Parent rootUser = FXMLLoader.load(getClass().getResource("UserPortal.fxml"));

        stage.setOnCloseRequest(e ->{
            e.consume();
            closeAndSave();
        });

        sceneMain = new Scene(rootMain);
        sceneDriver = new Scene(rootDriver);
        sceneUser = new Scene(rootUser);

        this.stage = stage;

        stage.setTitle("Car Rental App");
        stage.setScene(sceneMain);
        stage.show();

    }

    //Method for passing a reference to the data object
    public static DataHandler getDataHandler() {
        return data;
    }

    public static void changeScene(int num){
        if(num == 0)
            stage.setScene(sceneMain);
        if(num == 1)
            stage.setScene(sceneDriver);
        if(num == 2)
            stage.setScene(sceneUser);

    }

    public static void closeAndSave(){
        data.saveData();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}