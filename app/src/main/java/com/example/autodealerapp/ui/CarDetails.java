package com.example.autodealerapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.DbHelper;
import com.example.autodealerapp.helpers.Constants;

import java.util.Calendar;
import java.util.Locale;

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

    //Aksiyon çubuğunu tanımla
    private ActionBar actionBar;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        // DbHelper sınıfını oluştur
        dbHelper = new DbHelper(this);

        // Intent ile gönderilen verileri al
        Intent intent = getIntent();
        id = intent.getStringExtra("carId");

        // ActionBar'ı tanımla
        actionBar = getSupportActionBar();

        // Geri butonunu etkinleştir
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Car Details");

        // View nesnelerini tanımla
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

        loadDataById();

    }

    // Araç detaylarını id'ye göre yükle
    private void loadDataById() {
        if (id != null) {
            String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.COLUMN_ID + " =\"" + id + "\"";

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    // Verileri al
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
                    Calendar calendar = Calendar.getInstance(Locale.getDefault());

                    calendar.setTimeInMillis(Long.parseLong(addTime));
                    String addedTime = "" + android.text.format.DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar);

                    calendar.setTimeInMillis(Long.parseLong(updateTime));
                    String updatedTime = "" + android.text.format.DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar);

                    // View nesnelerine ata
                    brandTv.setText(brand);
                    modelTv.setText(model);
                    colorTv.setText(color);
                    yearTv.setText(year);
                    kilometerTv.setText(kilometer);
                    fuelTv.setText(fuel);
                    gearboxTv.setText(gearbox);
                    priceTv.setText(price);
                    addedTimeTv.setText(addedTime);
                    updatedTimeTv.setText(updatedTime);

                } while (cursor.moveToNext());
            }
            db.close();
        }
    }

    // Geri butonuna tıklanınca geri git
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
