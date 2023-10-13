package com.example.demo;

import java.io.*;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DataHandler {
    private String driverFileName;
    private String recordsFileName;

    private ArrayList<Driver> driverList;
    private ArrayList<Records> recordsList; // Changed to Record

    public DataHandler(String driverFileName, String recordsFileName) throws FileNotFoundException {
        this.driverFileName = driverFileName;
        this.recordsFileName = recordsFileName;
        // Initialize driverList ArrayList
        this.driverList = new ArrayList<Driver>();
        this.recordsList = new ArrayList<Records>(); // Changed to Record
        // Read data from saved files
        readDataFile();
    }

    private void readDataFile() {
        // Read data from driverFileName (binary file) and populate driverList
        try (ObjectInputStream driverInput = new ObjectInputStream(new FileInputStream(driverFileName))) {
            driverList = (ArrayList<Driver>) driverInput.readObject();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle other I/O exceptions
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle class not found exception
            e.printStackTrace();
        }

        // Read data from recordsFileName (binary file) and populate recordsList
        try (ObjectInputStream recordsInput = new ObjectInputStream(new FileInputStream(recordsFileName))) {
            recordsList = (ArrayList<Records>) recordsInput.readObject();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle other I/O exceptions
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle class not found exception
            e.printStackTrace();
        }

        System.out.println("Data Read from Files!");

    }

    public void saveData() {
        // Save driverList to driverFileName (binary file)
        try (ObjectOutputStream driverOutput = new ObjectOutputStream(new FileOutputStream(driverFileName))) {
            driverOutput.writeObject(driverList);
        } catch (IOException e) {
            // Handle I/O exceptions
            e.printStackTrace();
        }

        // Save recordsList to recordsFileName (binary file)
        try (ObjectOutputStream recordsOutput = new ObjectOutputStream(new FileOutputStream(recordsFileName))) {
            recordsOutput.writeObject(recordsList);
        } catch (IOException e) {
            // Handle I/O exceptions
            e.printStackTrace();
        }

        System.out.println("You Data Saved in Files!");

    }

    public boolean idExists(int id) {
        // Check if a driver with the given id exists in driverList
        for (Driver driver : driverList) {
            if (driver.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public void addDriver(Driver newDriver) {
        // Add a newDriver to the driverList
        driverList.add(newDriver);
    }

    public ArrayList<Driver> filterDrivers(String location, String carType) {
        ArrayList<Driver> filteredDrivers = new ArrayList<>();

        for (Driver driver : driverList) {
            if (driver.getLocation().equalsIgnoreCase(location) || driver.getCarType().equalsIgnoreCase(carType)) {
                filteredDrivers.add(driver);
            }
        }

        return filteredDrivers;
    }

    public void addRecord(Records newRecord) {
        // Add a newRecord to the recordsList
        recordsList.add(newRecord);
    }
    
    public ArrayList<Records> getAllRecords(){
        return recordsList;
    }
    public ArrayList<Driver> getAllDrivers(){
        return driverList;
    }
}