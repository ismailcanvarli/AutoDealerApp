package com.example.autodealerapp.ui;

import android.content.Intent;
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

    // Tıklama işleminin etkin olup olmadığını kontrol etmek için bir boolean değişkeni
    private boolean isItemClickable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        dbHelper = new CarDatabaseHelper(this);
        carList = dbHelper.getAllCars();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CarAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //isItemClickable değişkenini kontrol et
        Intent intent = getIntent();
        isItemClickable = intent.getBooleanExtra("isItemClickable", false);

        adapter.setOnItemClickListener(new CarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Tıklama işlemi sadece isItemClickable değişkeni true olduğunda gerçekleşir
                if (isItemClickable) {
                    Car clickedCar = carList.get(position);
                    Intent intent = new Intent(CarListActivity.this, UpdateCarActivity.class);
                    intent.putExtra("carId", clickedCar.getId());
                    startActivity(intent);
                }
            }
        });
    }
}