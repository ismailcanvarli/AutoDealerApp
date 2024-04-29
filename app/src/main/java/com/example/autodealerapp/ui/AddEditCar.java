package com.example.autodealerapp.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.DbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditCar extends AppCompatActivity {

    // Ekleme butonunu tanımla
    private FloatingActionButton fab;

    // EditText alanlarını tanımla
    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText kilometerEditText;
    private EditText colorEditText;
    private EditText priceEditText;


    // Araç özelliklerini tutacak değişkenler
    private String model;
    private String brand;
    private String color;
    private String yearString;
    private String kilometerString;
    private String priceString;
    private String addedTime;
    private String updatedTime;

    //Aksiyon çubuğunu tanımla
    private ActionBar actionBar;
    // Veritabanı yardımcı sınıfını tanımlandı
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_car);

        // Veritabanı yardımcı sınıfını oluştur
        dbHelper = new DbHelper(this);

        // ActionBar'ı tanımla
        actionBar = getSupportActionBar();

        // Geri butonunu etkinleştir
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // EditText alanlarını bul
        brandEditText = findViewById(R.id.brandEditText);
        modelEditText = findViewById(R.id.modelEditText);
        yearEditText = findViewById(R.id.yearEditText);
        kilometerEditText = findViewById(R.id.kilometerEditText);
        colorEditText = findViewById(R.id.colorEditText);
        priceEditText = findViewById(R.id.priceEditText);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            saveCarData();
        });


        actionBar.setTitle("Add Car");



        // Ekleme butonunu bul
        fab.setOnClickListener(v -> {
            saveCarData();
        });
    }

    private void saveCarData() {
        // Kullanıcıdan alınan bilgilerle yeni bir araç oluştur
        String brand = brandEditText.getText().toString();
        model = modelEditText.getText().toString();
        color = colorEditText.getText().toString();
        yearString = yearEditText.getText().toString();
        kilometerString = kilometerEditText.getText().toString();
        priceString = priceEditText.getText().toString();

        // Şuanki tarih ve saat
        String timestamp = String.valueOf(System.currentTimeMillis());

        if (brand.isEmpty() || model.isEmpty() || color.isEmpty() || yearString.isEmpty() || kilometerString.isEmpty() || priceString.isEmpty()) {
            Toast.makeText(AddEditCar.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            int year = Integer.parseInt(yearString);
            int kilometer = Integer.parseInt(kilometerString);
            double price = Double.parseDouble(priceString);

            if (year < 1900 || year > 2024) {
                Toast.makeText(AddEditCar.this, "Please enter a valid year", Toast.LENGTH_SHORT).show();
            } else if (kilometer < 0) {
                Toast.makeText(AddEditCar.this, "Please enter a valid kilometer", Toast.LENGTH_SHORT).show();
            } else if (price < 0) {
                Toast.makeText(AddEditCar.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
            } else {

                // Veritabanına yeni aracı ekle
                long id = dbHelper.insertCar(
                        brand,
                        model,
                        year,
                        kilometer,
                        color,
                        price,
                        timestamp,
                        timestamp
                );

                // Ekleme işlemi başarılıysa kullanıcıya mesaj göster
                Toast.makeText(AddEditCar.this, "Car added succesfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Geri butonuna tıklanınca geri git
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}