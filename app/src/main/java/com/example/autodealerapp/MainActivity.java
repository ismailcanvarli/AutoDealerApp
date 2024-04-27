package com.example.autodealerapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.data.DbHelper;
import com.example.autodealerapp.ui.AddEditCar;
import com.example.autodealerapp.adapters.CarAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    // Araçları göstermek için RecyclerView nesnesi
    private RecyclerView carRecyclerView;
    // Fab butonu tanımlandın
    private FloatingActionButton fab;

    // Veritabanı yardımcı sınıfını tanımla
    private DbHelper dbHelper;

    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Veritabanı yardımcı sınıfını oluştur
        dbHelper = new DbHelper(this);

        // Fab butonu tanımla
        fab = findViewById(R.id.fab);
        carRecyclerView = findViewById(R.id.carRecyclerView);

        carRecyclerView.setHasFixedSize(true);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditCar.class);
            startActivity(intent);
        });

        // Veritabanından tüm araçları al ve göster
        loadData();

    }

    // Veritabanından tüm araçları al
    private void loadData() {
        carAdapter = new CarAdapter(this, dbHelper.getAllData());
        carRecyclerView.setAdapter(carAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Veritabanından tüm araçları al ve göster
    }


}
