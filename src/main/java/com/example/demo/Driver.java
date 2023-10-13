package com.example.demo;

import java.io.Serializable;

public class Driver implements Serializable {
    private int ID;
    private String fullName;
    private double fare;
    private String carType;
    private String location;




    public Driver(int ID, String fullName, double fare, String carType, String location) {
        this.ID = ID;
        this.fullName = fullName;
        this.fare = fare;
        this.carType = carType;
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return  ID +
                " " + fullName +
                " " + location +
                " " + carType +
                ' ' + fare;
    }
}