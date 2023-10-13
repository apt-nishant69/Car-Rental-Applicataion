package com.example.demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class MenuController {
//    @FXML
//    private Label welcomeText;


    //Main Menu
    @FXML
    private GridPane gridRecords;

    //Manage Fare Tab
    @FXML
    private TextField textDriverID;

    @FXML
    private GridPane gridFare;

    @FXML
    private TextField newFare;

//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @FXML
    private void btnshowRecords(ActionEvent event){
        System.out.println("You clicked on Show Records!");
        // Switch to the View all scene
        gridRecords.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        DataHandler data = App.getDataHandler();
        ArrayList<Records> allRecords = data.getAllRecords();
        int row = 1;
        for (Records record : allRecords) {
            String vals[] = record.toString().split(" ");
            for (int i = 0; i < 5; i++) {
                Label label = new Label(vals[i].trim());
                gridRecords.add(label, i, row);
            }
            row++;
        }
    }

    //In Managing Fare tab
    @FXML
    private void searchDriver(ActionEvent event){
        System.out.println("You clicked on Show Drivers!");
        // Switch to the View all scene
        gridFare.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        DataHandler data = App.getDataHandler();
        ArrayList<Driver> allDrivers = data.getAllDrivers();
        int row = 1;
        for (Driver driver : allDrivers) {
            String vals[] = driver.toString().split(" ");
            for (int i = 0; i < 5; i++) {
                Label label = new Label(vals[i].trim());
                gridFare.add(label, i, row);
            }
            row++;
        }
        System.out.println("You Clicked Search Driver!");

    }
    //In Managing Fare tab
    @FXML
    private void btnEditFare(ActionEvent event){
        try {
            DataHandler data = App.getDataHandler();
            ArrayList<Driver> allDrivers = data.getAllDrivers();

            int id = Integer.parseInt(textDriverID.getText());
            double newfare = Double.parseDouble(newFare.getText());
            //We now search for the driver

            if(allDrivers != null){
                for (Driver driver : allDrivers) {
                    if(driver.getID() == id){
                        driver.setFare(newfare);
                        break;
                    }
                }
                // Optionally, display a confirmation message to the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fare Changed Successful");
                alert.setHeaderText("Your fare has been changed for Driver (ID): " + id);
                alert.showAndWait();
            }else{
                // Handle the case where no driver is selected (e.g., display an error message)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No driver selected");
                alert.setContentText("Please select a driver before searching.");
                alert.showAndWait();
            }


        }catch(NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Input ON ID or Fare! " + ex.getLocalizedMessage());
            alert.showAndWait();
        }
        System.out.println("You Clicked Edit Fare!");

    }

    @FXML
    private void btnOpenDriverPortal(ActionEvent e){
        System.out.println("You Clicked Driver Port!");

        App.changeScene(1);
    }
    @FXML
    private void btnOpenUserPortal(ActionEvent e){
        System.out.println("You clicked User Port!");

        App.changeScene(2);
    }

    @FXML
    private void closeApp(ActionEvent e){
        System.out.println("You Clicked Save and Exit!");
        App.closeAndSave();
    }

}