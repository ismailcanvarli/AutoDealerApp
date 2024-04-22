package com.example.autodealerapp.model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private int kilometer;
    private String color;
    private double price;

    public Car(int id, String brand, String model, int year, int kilometer, String color, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometer = kilometer;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
