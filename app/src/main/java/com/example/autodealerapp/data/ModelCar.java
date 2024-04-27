package com.example.autodealerapp.data;

public class ModelCar {
    private String id;
    private String brand;
    private String model;
    private String year;
    private String kilometer;
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String price;

    public ModelCar(String id, String brand, String model, String year, String kilometer, String color, String price, String addedTime, String updatedTime, String note) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometer = kilometer;
        this.color = color;
        this.price = price;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
        this.note = note;
    }

    private String addedTime;
    private String updatedTime;
    private String note;



}
