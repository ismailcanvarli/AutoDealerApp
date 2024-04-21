package com.example.autodealerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.CarDatabaseHelper;
import com.example.autodealerapp.model.Car;

public class UpdateCarActivity extends AppCompatActivity {

    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText kilometerEditText;
    private EditText colorEditText;
    private EditText priceEditText;
    private Button updateButton;

    private CarDatabaseHelper dbHelper;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        brandEditText = findViewById(R.id.brandEditText);
        modelEditText = findViewById(R.id.modelEditText);
        yearEditText = findViewById(R.id.yearEditText);
        kilometerEditText = findViewById(R.id.kilometerEditText);
        colorEditText = findViewById(R.id.colorEditText);
        priceEditText = findViewById(R.id.priceEditText);
        updateButton = findViewById(R.id.updateButton);

        dbHelper = new CarDatabaseHelper(this);

        Intent intent = getIntent();
        int carId = intent.getIntExtra("carId", -1);
        car = dbHelper.getCar(carId);

        if (car != null) {
            brandEditText.setText(car.getBrand());
            modelEditText.setText(car.getModel());
            yearEditText.setText(String.valueOf(car.getYear()));
            kilometerEditText.setText(String.valueOf(car.getKilometer()));
            colorEditText.setText(car.getColor());
            priceEditText.setText(String.valueOf(car.getPrice()));
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand = brandEditText.getText().toString();
                String model = modelEditText.getText().toString();
                int year = Integer.parseInt(yearEditText.getText().toString());
                int kilometer = Integer.parseInt(kilometerEditText.getText().toString());
                String color = colorEditText.getText().toString();
                double price = Double.parseDouble(priceEditText.getText().toString());

                car.setBrand(brand);
                car.setModel(model);
                car.setYear(year);
                car.setKilometer(kilometer);
                car.setColor(color);
                car.setPrice(price);

                dbHelper.updateCar(car);
                Toast.makeText(UpdateCarActivity.this, "Car updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}