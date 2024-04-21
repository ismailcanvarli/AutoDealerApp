package com.example.autodealerapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.CarDatabaseHelper;
import com.example.autodealerapp.model.Car;
import com.example.autodealerapp.ui.adapters.CarAdapter;

import java.util.List;

public class CarListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarAdapter adapter;
    private List<Car> carList;
    private CarDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        // Veritabanı yardımcı sınıfını oluştur
        dbHelper = new CarDatabaseHelper(this);

        // Veritabanından araçları al
        carList = dbHelper.getAllCars();

        // RecyclerView ve adaptörü yapılandır
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CarAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}