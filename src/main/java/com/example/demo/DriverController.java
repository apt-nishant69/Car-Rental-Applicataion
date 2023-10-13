package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class DriverController {

    @FXML
    ObservableList<String> cartype = FXCollections.observableArrayList("Sedan", "Jeep", "Crossover", "Truck");

    @FXML
    private TextField textRegFullName;
    @FXML
    private TextField textRegLocation;
    @FXML
    private ChoiceBox optionRegCarType;
    @FXML
    private TextField textRegDriverID;

    @FXML
    private void initialize(){
        optionRegCarType.setValue("Sedan");
        optionRegCarType.setItems(cartype);
    }


    @FXML
    private void btnRegisterDriver(ActionEvent event){
        int id = 0;
        String FullName = textRegFullName.getText();
        String Location = textRegLocation.getText();
        String CarType = optionRegCarType.getValue().toString();
        //We assign fare according to the Car Type
        double fare = 0.0;
        if(FullName.length()>0 && Location.length()>0 && CarType.length() > 0){
            DataHandler data = App.getDataHandler();
            try {
                id = Integer.parseInt(textRegDriverID.getText());
                if (data.idExists(id)) {
                    throw new NumberFormatException("Member Id Already Exists.");
                }
                if(CarType.equals("Sedan"))
                    fare = 50.0;
                else if (CarType.equals("Jeep"))
                    fare = 100.99;
                else if(CarType.equals("CrossOver"))
                    fare = 75.05;
                else if(CarType.equals("Truck"))
                    fare = 69.69;
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid! " + e.getLocalizedMessage());
                alert.showAndWait();
                return;
            }
            Driver newDriver = new Driver(id, FullName, fare, CarType, Location);
            System.out.println(newDriver.toString());
            // Implement the data
            data.addDriver(newDriver);
            data.saveData();
            App.changeScene(0);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Kindly fill out all the fields");
            alert.showAndWait();
        }
        System.out.println("You Clicked Register Driver!");

    }




    @FXML
    private void btnCancel(){
        System.out.println("You Clicked Book Ride!");
        App.changeScene(0);
    }




}