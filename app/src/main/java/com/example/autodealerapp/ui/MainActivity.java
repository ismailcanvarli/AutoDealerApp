package com.example.autodealerapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    // Araçları göstermek için RecyclerView nesnesi
    private RecyclerView carRecyclerView;
    //
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fab butonu tanımla
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditCar.class);
            startActivity(intent);
        });



    }
}
