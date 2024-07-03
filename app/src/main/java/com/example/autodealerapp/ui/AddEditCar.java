package com.example.autodealerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
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
    private EditText colorEditText;
    private EditText yearEditText;
    private EditText kilometerEditText;
    private EditText fuelEditText;
    private EditText gearboxEditText;
    private EditText priceEditText;


    // Araç özelliklerini tutacak değişkenler
    private String id;
    private String model;
    private String brand;
    private String color;
    private String year;
    private String kilometer;
    private String fuel;
    private String gearbox;
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

        // Bileşenleri başlat
        initViews();

        // ActionBar'ı ayarla
        setupActionBar();

        // EditMode kontrolü ve verileri yükle
        checkEditModeAndLoadData();

        // Olay dinleyicilerini ayarla
        setupEventListeners();
    }

    private void initViews() {
        brandEditText = findViewById(R.id.brandEditText);
        modelEditText = findViewById(R.id.modelEditText);
        colorEditText = findViewById(R.id.colorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        kilometerEditText = findViewById(R.id.kilometerEditText);
        fuelEditText = findViewById(R.id.fuelEditText);
        gearboxEditText = findViewById(R.id.gearboxEditText);
        priceEditText = findViewById(R.id.priceEditText);
        fab = findViewById(R.id.fab);
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(isEditMode ? "Edit Car" : "Add Car");
        }
    }

    private void checkEditModeAndLoadData() {
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            id = intent.getStringExtra("ID");
            brand = intent.getStringExtra("BRAND");
            model = intent.getStringExtra("MODEL");
            color = intent.getStringExtra("COLOR");
            year = intent.getStringExtra("YEAR");
            kilometer = intent.getStringExtra("KILOMETER");
            fuel = intent.getStringExtra("FUEL");
            gearbox = intent.getStringExtra("GEARBOX");
            price = intent.getStringExtra("PRICE");
            addedTime = intent.getStringExtra("ADDED_TIME");
            updatedTime = intent.getStringExtra("UPDATED_TIME");

            // EditText alanlarına araç özelliklerini yaz
            brandEditText.setText(brand);
            modelEditText.setText(model);
            colorEditText.setText(color);
            yearEditText.setText(year);
            kilometerEditText.setText(kilometer);
            fuelEditText.setText(fuel);
            gearboxEditText.setText(gearbox);
            priceEditText.setText(price);
        }
    }

    private void setupEventListeners() {
        fab.setOnClickListener(v -> saveCarData());

        priceEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                fab.performClick();
                return true;
            }
            return false;
        });
    }


    private void saveCarData() {
        if (!retrieveAndValidateInputs()) {
            return;
        }

        String timestamp = String.valueOf(System.currentTimeMillis());

        if (isEditMode) {
            updateCarInDatabase(timestamp);
        } else {
            addCarToDatabase(timestamp);
        }

        Toast.makeText(AddEditCar.this, isEditMode ? "Car updated successfully." : "Car added successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean retrieveAndValidateInputs() {
        brand = brandEditText.getText().toString();
        model = modelEditText.getText().toString();
        color = colorEditText.getText().toString();
        yearString = yearEditText.getText().toString();
        kilometerString = kilometerEditText.getText().toString();
        fuel = fuelEditText.getText().toString();
        gearbox = gearboxEditText.getText().toString();
        priceString = priceEditText.getText().toString();

        if (brand.isEmpty() || model.isEmpty() || color.isEmpty() || yearString.isEmpty() || kilometerString.isEmpty() || priceString.isEmpty() || fuel.isEmpty() || gearbox.isEmpty()) {
            Toast.makeText(AddEditCar.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int year = Integer.parseInt(yearString);
            int kilometer = Integer.parseInt(kilometerString);
            double price = Double.parseDouble(priceString);

            if (!isValidYear(year) || !isValidKilometer(kilometer) || !isValidPrice(price)) {
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(AddEditCar.this, "Please enter valid numerical values", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidYear(int year) {
        if (year < 1900 || year > 2024) {
            Toast.makeText(AddEditCar.this, "Please enter a valid year", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidKilometer(int kilometer) {
        if (kilometer < 0 || kilometer > 1000000) {
            Toast.makeText(AddEditCar.this, "Please enter a valid kilometer", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidPrice(double price) {
        if (price < 0 || price > 100000000) {
            Toast.makeText(AddEditCar.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateCarInDatabase(String timestamp) {
        dbHelper.updateCar(id, brand, model, color, yearString, kilometerString, fuel, gearbox, priceString, timestamp, timestamp);
    }

    private void addCarToDatabase(String timestamp) {
        dbHelper.insertCar(brand, model, color, yearString, kilometerString, fuel, gearbox, priceString, timestamp, timestamp);
    }


    // Geri butonuna tıklanınca geri git
    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return super.onSupportNavigateUp();
    }
}