package com.example.autodealerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
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
    private String id;
    private String model;
    private String brand;
    private String color;
    private String year;
    private String kilometer;
    private String price;
    private String addedTime;
    private String updatedTime;
    private String yearString;
    private String kilometerString;
    private String priceString;

    // Düzenleme modunda mı?
    private boolean isEditMode = false;

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
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Add Car");

        // EditText alanlarını bul
        brandEditText = findViewById(R.id.brandEditText);
        modelEditText = findViewById(R.id.modelEditText);
        colorEditText = findViewById(R.id.colorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        kilometerEditText = findViewById(R.id.kilometerEditText);
        priceEditText = findViewById(R.id.priceEditText);

        fab = findViewById(R.id.fab);

        // Ekleme butonuna tıklanınca yeni aracı veritabanına ekle
        fab.setOnClickListener(v -> saveCarData());
        // Fiyat alanına tıklanınca klavye açılsın ve ekleme butonuna tıklanınca kayıt işlemi yapılsın
        priceEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    fab.performClick();
                    return true;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        // Eğer düzenleme modundaysa
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            // ActionBar'ı güncelle
            actionBar.setTitle("Edit Car");

            // Araç özelliklerini al
            id = intent.getStringExtra("ID");
            brand = intent.getStringExtra("BRAND");
            model = intent.getStringExtra("MODEL");
            color = intent.getStringExtra("COLOR");
            year = intent.getStringExtra("YEAR");
            kilometer = intent.getStringExtra("KILOMETER");
            price = intent.getStringExtra("PRICE");
            addedTime = intent.getStringExtra("ADDED_TIME");
            updatedTime = intent.getStringExtra("UPDATED_TIME");

            // EditText alanlarına araç özelliklerini yaz
            brandEditText.setText(brand);
            modelEditText.setText(model);
            colorEditText.setText(color);
            yearEditText.setText(year);
            kilometerEditText.setText(kilometer);
            priceEditText.setText(price);
        } else {
            // Ekleme modundaysa
            actionBar.setTitle("Add Car");
        }

        fab.setOnClickListener(v -> saveCarData());

    }

    private void saveCarData() {
        // Kullanıcıdan alınan bilgilerle yeni bir araç oluştur
        brand = brandEditText.getText().toString();
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
            } else if (kilometer < 0 || kilometer > 1000000) {
                Toast.makeText(AddEditCar.this, "Please enter a valid kilometer", Toast.LENGTH_SHORT).show();
            } else if (price < 0 || price > 100000000) {
                Toast.makeText(AddEditCar.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
            } else {

                if (isEditMode) {
                    // Eğer düzenleme modundaysa
                    // Veritabanına yeni aracı ekle
                    dbHelper.updateCar(id, brand, model, color, yearString, kilometerString, priceString, timestamp, timestamp);

                    Toast.makeText(AddEditCar.this, "Car updated successfully. ", Toast.LENGTH_SHORT).show();

                } else {
                    // Eğer ekleme modundaysa
                    // Veritabanına yeni aracı ekle
                    long id = dbHelper.insertCar(brand, model, color, yearString, kilometerString, priceString, timestamp, timestamp);

                    // Ekleme işlemi başarılıysa kullanıcıya mesaj göster
                    Toast.makeText(AddEditCar.this, "Car added successfully. ", Toast.LENGTH_SHORT).show();
                }
                // Ana ekrana geri dön
                finish();
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