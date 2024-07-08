//Created by canVarli on 7/9/2024

package com.example.autodealerapp.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ModelCarTest {
    private ModelCar modelCar;

    @Before
    public void setUp() {
        modelCar = new ModelCar("1", "Toyota", "Corolla", "Red", "2020", "10000", "Petrol", "Automatic", "20000");
    }

    @Test
    public void testGetId() {
        assertEquals("1", modelCar.getId());
    }

    @Test
    public void testGetBrand() {
        assertEquals("Toyota", modelCar.getBrand());
    }

    @Test
    public void testGetModel() {
        assertEquals("Corolla", modelCar.getModel());
    }

    @Test
    public void testGetColor() {
        assertEquals("Red", modelCar.getColor());
    }

    @Test
    public void testGetYear() {
        assertEquals("2020", modelCar.getYear());
    }

    @Test
    public void testGetKilometer() {
        assertEquals("10000", modelCar.getKilometer());
    }

    @Test
    public void testGetFuel() {
        assertEquals("Petrol", modelCar.getFuel());
    }

    @Test
    public void testGetGearBox() {
        assertEquals("Automatic", modelCar.getGearBox());
    }

    @Test
    public void testGetPrice() {
        assertEquals("20000", modelCar.getPrice());
    }

    @Test
    public void testSetId() {
        modelCar.setId("2");
        assertEquals("2", modelCar.getId());
    }

    @Test
    public void testSetBrand() {
        modelCar.setBrand("Ford");
        assertEquals("Ford", modelCar.getBrand());
    }

    @Test
    public void testSetModel() {
        modelCar.setModel("Focus");
        assertEquals("Focus", modelCar.getModel());
    }

    @Test
    public void testSetColor() {
        modelCar.setColor("Blue");
        assertEquals("Blue", modelCar.getColor());
    }

    @Test
    public void testSetYear() {
        modelCar.setYear("2019");
        assertEquals("2019", modelCar.getYear());
    }

    @Test
    public void testSetKilometer() {
        modelCar.setKilometer("20000");
        assertEquals("20000", modelCar.getKilometer());
    }

    @Test
    public void testSetFuel() {
        modelCar.setFuel("Diesel");
        assertEquals("Diesel", modelCar.getFuel());
    }

    @Test
    public void testSetGearBox() {
        modelCar.setGearBox("Manual");
        assertEquals("Manual", modelCar.getGearBox());
    }

    @Test
    public void testSetPrice() {
        modelCar.setPrice("25000");
        assertEquals("25000", modelCar.getPrice());
    }

    @Test
    public void testSetAddedTime() {
        modelCar.setAddedTime("2021-07-09T12:00:00Z");
        assertEquals("2021-07-09T12:00:00Z", modelCar.getAddedTime());
    }

    @Test
    public void testSetUpdatedTime() {
        modelCar.setUpdatedTime("2021-07-09T12:00:00Z");
        assertEquals("2021-07-09T12:00:00Z", modelCar.getUpdatedTime());
    }

}