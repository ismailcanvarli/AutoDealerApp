package com.example.autodealerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ekleme, Gösterme, Güncelleme butonlarını
        Button addCarButton = findViewById(R.id.addCarButton);
        Button showCarButton = findViewById(R.id.showCarButton);
        Button updateCarButton = findViewById(R.id.updateCarButton);

        // Ekleme butonuna tıklama olayı ekle
        addCarButton.setOnClickListener(v -> {
            // Yeni araç ekleme aktivitesini başlat
            Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
            startActivity(intent);
        });

        // Gösterme butonuna tıklama olayı ekle
        showCarButton.setOnClickListener(v -> {
            // Araçları gösterme aktivitesini başlat
            Intent intent = new Intent(MainActivity.this, CarListActivity.class);
            //isItemClickable değişkenini false olarak ayarla
            intent.putExtra("isItemClickable", false);
            startActivity(intent);
        });

        // Güncelleme butonuna tıklama olayı ekle
        updateCarButton.setOnClickListener(v -> {
            // Araçları gösterme aktivitesini başlat
            Intent intent = new Intent(MainActivity.this, CarListActivity.class);
            // isItemClickable değişkenini true olarak ayarla
            intent.putExtra("isItemClickable", true);
            startActivity(intent);
        });

    }
}
