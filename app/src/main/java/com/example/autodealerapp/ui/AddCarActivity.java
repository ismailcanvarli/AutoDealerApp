package com.example.autodealerapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.CarDatabaseHelper;
import com.example.autodealerapp.model.Car;

public class AddCarActivity extends AppCompatActivity {

    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText kilometerEditText;
    private EditText colorEditText;
    private EditText priceEditText;

    private CarDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // EditText alanlarını bul
        brandEditText = findViewById(R.id.brandEditText);
        modelEditText = findViewById(R.id.modelEditText);
        yearEditText = findViewById(R.id.yearEditText);
        kilometerEditText = findViewById(R.id.kilometerEditText);
        colorEditText = findViewById(R.id.colorEditText);
        priceEditText = findViewById(R.id.priceEditText);

        // Veritabanı yardımcı sınıfını oluştur
        dbHelper = new CarDatabaseHelper(this);

        // Kaydet butonunu bul ve tıklama olayını ekle
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcıdan alınan bilgilerle yeni bir araç oluştur
                String brand = brandEditText.getText().toString();
                String model = modelEditText.getText().toString();
                int year = Integer.parseInt(yearEditText.getText().toString());
                int kilometer = Integer.parseInt(kilometerEditText.getText().toString());
                String color = colorEditText.getText().toString();
                double price = Double.parseDouble(priceEditText.getText().toString());

                Car newCar = new Car(brand, model, year, kilometer, color, price);

                // Veritabanına yeni aracı ekle
                long result = dbHelper.addCar(newCar);

                if (result != -1) {
                    Toast.makeText(AddCarActivity.this, "Car added succesfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AddCarActivity.this,"Car not added. " , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}