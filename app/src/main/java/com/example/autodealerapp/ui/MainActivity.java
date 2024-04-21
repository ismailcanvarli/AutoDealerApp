package com.example.autodealerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ekleme, Gösterme, Güncelleme ve Silme butonlarını bul
        Button addCarButton = findViewById(R.id.addCarButton);
        Button showCarButton = findViewById(R.id.showCarButton);
        Button updateCarButton = findViewById(R.id.updateCarButton);
        Button deleteCarButton = findViewById(R.id.deleteCarButton);

        // Ekleme butonuna tıklama olayı ekle
        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Yeni araç ekleme aktivitesini başlat
                Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
                startActivity(intent);
            }
        });

        // Gösterme butonuna tıklama olayı ekle
        showCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Araçları gösterme aktivitesini başlat
                Intent intent = new Intent(MainActivity.this, CarListActivity.class);
                startActivity(intent);
            }
        });

        // Güncelleme butonuna tıklama olayı ekle
        updateCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Araçları gösterme aktivitesini başlat
                Intent intent = new Intent(MainActivity.this, CarListActivity.class);
                startActivity(intent);
            }
        });

        // Silme butonuna tıklama olayı ekle
        deleteCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Araç silme aktivitesini başlat
            }
        });
    }
}
