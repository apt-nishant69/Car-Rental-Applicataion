package com.example.demo;

import java.io.Serializable;

public class Records implements Serializable{
    private String userName;
    private String DriverName;
    private int DriverID;
    private String Location;
    private double Fare;
    private String CarType;

    public Records(String userName, String driverName, int driverID, String location, double fare, String carType) {
        this.userName = userName;
        DriverName = driverName;
        DriverID = driverID;
        Location = location;
        Fare = fare;
        CarType = carType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getFare() {
        return Fare;
    }

    public void setFare(double fare) {
        Fare = fare;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String toString(){
        return userName + " " + DriverName + " " + Location + " " + Fare + " " + CarType;
    }
}