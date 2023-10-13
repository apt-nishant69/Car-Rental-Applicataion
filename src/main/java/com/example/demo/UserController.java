package com.example.demo;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UserController {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtDriverLocation;

    @FXML
    private ChoiceBox<String> optionDriverCarType;


    @FXML
    private GridPane gridBooking;

    @FXML
    private TextField txtSelectedDriverID;

    private ArrayList<Driver> driverList;

    public void initialize() {
        // Initialize the choice box with car types
        ObservableList<String> carTypes = FXCollections.observableArrayList("Sedan", "Jeep", "CrossOver", "Truck");
        optionDriverCarType.setItems(carTypes);
    }

    @FXML
    private void searchDriverByLocation(ActionEvent event) {

        gridBooking.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        try {
            String location = txtDriverLocation.getText();
            String cartype = optionDriverCarType.getValue();

            DataHandler data = App.getDataHandler();
            ArrayList<Driver> listDrivers = data.filterDrivers(location, cartype);
            int row = 1;
            for (Driver driver : listDrivers) {
                String vals[] = driver.toString().split(" ");
                for (int i = 0; i < 5; i++) {
                    Label label = new Label(vals[i].trim());
                    gridBooking.add(label, i, row);
                }
                row++;
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Input! " + ex.getLocalizedMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void btnbookRide(ActionEvent event) {
        // Get the selected driver from the grid
//        RadioButton selectedRadioButton = (RadioButton) gridDriverList.getSelectedToggle();
        String Username = txtUserName.getText();
        try{
            int selectedId = Integer.parseInt(txtSelectedDriverID.getText());
            DataHandler data = App.getDataHandler();
            driverList = data.getAllDrivers();
            //We find the driver
            Driver selectedDriver = null;
            for (Driver driver : driverList) {
                if (driver.getID() == selectedId) {
                    selectedDriver = driver;
                    break;
                }
            }
            if (selectedDriver != null){


                //we make a record
                Records newRecord = new Records(Username, selectedDriver.getFullName(), selectedDriver.getID(), selectedDriver.getLocation(), selectedDriver.getFare(), selectedDriver.getCarType());
                newRecord.setDriverName(selectedDriver.getFullName());
                newRecord.setLocation(selectedDriver.getLocation());
                newRecord.setCarType(selectedDriver.getCarType());
                newRecord.setFare(selectedDriver.getFare());

                // You can then add the newRecord to your data handler or perform any other booking-related actions
                data.addRecord(newRecord);

                // Optionally, display a confirmation message to the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Booking Successful");
                alert.setHeaderText("Your ride has been booked.");
                alert.setContentText("Driver: " + selectedDriver.getFullName() + "\nLocation: " + selectedDriver.getLocation() + "\nCar Type: " + selectedDriver.getCarType() + "\nFare: $" + selectedDriver.getFare());
                alert.showAndWait();
                App.changeScene(0);
            }else{
                // Handle the case where no driver is selected (e.g., display an error message)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No driver selected");
                alert.setContentText("Please select a driver before booking.");
                alert.showAndWait();
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid ID selected! " + e.getLocalizedMessage());
            alert.showAndWait();
        }
        System.out.println("You Clicked Book Ride!");
    }





    @FXML
    private void btncancel(ActionEvent event) {
        // Implement cancel logic here
        System.out.println("You Clicked Cancel!");

        App.changeScene(0);
    }
}