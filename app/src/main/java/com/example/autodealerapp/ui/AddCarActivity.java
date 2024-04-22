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
        Button saveCarButton = findViewById(R.id.saveCarButton);

        saveCarButton.setOnClickListener(v -> saveCarData());
    }

   private void saveCarData() {
    // Kullanıcıdan alınan bilgilerle yeni bir araç oluştur
    String brand = brandEditText.getText().toString();
    String model = modelEditText.getText().toString();
    String color = colorEditText.getText().toString();
    String yearString = yearEditText.getText().toString();
    String kilometerString = kilometerEditText.getText().toString();
    String priceString = priceEditText.getText().toString();

    if (brand.isEmpty() || model.isEmpty() || color.isEmpty() || yearString.isEmpty() || kilometerString.isEmpty() || priceString.isEmpty()) {
        Toast.makeText(AddCarActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
    } else {
        int year = Integer.parseInt(yearString);
        int kilometer = Integer.parseInt(kilometerString);
        double price = Double.parseDouble(priceString);

        if (year < 1900 || year > 2024) {
            Toast.makeText(AddCarActivity.this, "Please enter a valid year", Toast.LENGTH_SHORT).show();
        } else if (kilometer < 0) {
            Toast.makeText(AddCarActivity.this, "Please enter a valid kilometer", Toast.LENGTH_SHORT).show();
        } else if (price < 0) {
            Toast.makeText(AddCarActivity.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
        } else {
            Car newCar = new Car();

            newCar.setBrand(brand);
            newCar.setModel(model);
            newCar.setYear(year);
            newCar.setKilometer(kilometer);
            newCar.setColor(color);
            newCar.setPrice(price);

            // Veritabanına yeni aracı ekle
            Car result = dbHelper.addCar(newCar);
            Toast.makeText(AddCarActivity.this, "Car added succesfully", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
}