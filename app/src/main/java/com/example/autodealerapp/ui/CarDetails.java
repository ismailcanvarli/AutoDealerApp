package com.example.autodealerapp.ui;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.DbHelper;
import com.example.autodealerapp.helpers.Constants;

import java.util.Calendar;

public class CarDetails extends AppCompatActivity {

    // Araç detayları için view nesneleri tanımla
    private TextView brandTv;
    private TextView modelTv;
    private TextView colorTv;
    private TextView yearTv;
    private TextView kilometerTv;
    private TextView fuelTv;
    private TextView gearboxTv;
    private TextView priceTv;
    private TextView addedTimeTv;
    private TextView updatedTimeTv;

    private String id;
    private ActionBar actionBar;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        // ActionBar'ı tanımla ve ayarla
        setupActionBar();

        // View nesnelerini bağla
        bindViews();

        // Veritabanı işlemleri için DbHelper sınıfını oluştur
        dbHelper = new DbHelper(this);

        // Intent'ten gelen verileri al
        id = getIntent().getStringExtra("carId");

        // Verileri yükle
        loadDataById();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Car Details");
        }
    }

    private void bindViews() {
        brandTv = findViewById(R.id.brandTv);
        modelTv = findViewById(R.id.modelTv);
        colorTv = findViewById(R.id.colorTv);
        yearTv = findViewById(R.id.yearTv);
        kilometerTv = findViewById(R.id.kilometerTv);
        fuelTv = findViewById(R.id.fuelTv);
        gearboxTv = findViewById(R.id.gearboxTv);
        priceTv = findViewById(R.id.priceTv);
        addedTimeTv = findViewById(R.id.addedTimeTv);
        updatedTimeTv = findViewById(R.id.updatedTimeTv);
    }

    private void loadDataById() {
        if (id == null) {
            return;
        }

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.COLUMN_ID + " = ?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try (Cursor cursor = db.rawQuery(selectQuery, new String[]{id})) {
            if (cursor.moveToFirst()) {
                String brand = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_BRAND));
                String model = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_MODEL));
                String color = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_COLOR));
                String year = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_YEAR));
                String kilometer = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_KILOMETER));
                String fuel = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_FUEL));
                String gearbox = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_GEARBOX));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_PRICE));
                String addTime = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_ADDED_TIME));
                String updateTime = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_UPDATED_TIME));

                // Zamanı okunabilir formata çevir
                String addedTimeFormatted = formatDateTime(addTime);
                String updatedTimeFormatted = formatDateTime(updateTime);

                // View nesnelerine ata
                brandTv.setText(brand);
                modelTv.setText(model);
                colorTv.setText(color);
                yearTv.setText(year);
                kilometerTv.setText(kilometer);
                fuelTv.setText(fuel);
                gearboxTv.setText(gearbox);
                priceTv.setText(price);
                addedTimeTv.setText(addedTimeFormatted);
                updatedTimeTv.setText(updatedTimeFormatted);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    private String formatDateTime(String timestamp) {
        if (timestamp == null) return "";

        try {
            long timeInMillis = Long.parseLong(timestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeInMillis);
            return android.text.format.DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar).toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
    }


    // Geri butonuna tıklanınca geri git
    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return super.onSupportNavigateUp();
    }

}
