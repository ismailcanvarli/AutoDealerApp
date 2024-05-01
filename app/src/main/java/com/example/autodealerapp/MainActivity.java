package com.example.autodealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.adapters.CarAdapter;
import com.example.autodealerapp.data.DbHelper;
import com.example.autodealerapp.ui.AddEditCar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;
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

        // ActionBar'ı tanımla
        actionBar = getSupportActionBar();

        // Fab butonu tanımla
        fab = findViewById(R.id.fab);
        carRecyclerView = findViewById(R.id.carRecyclerView);

        carRecyclerView.setHasFixedSize(true);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditCar.class);
            intent.putExtra("isEditMode", false);
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
    public void onResume() {
        super.onResume();
        loadData(); // Veritabanından tüm araçları al ve göster
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menüyü yükle
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Arama butonunu tanımla
        MenuItem searchItem = menu.findItem(R.id.search_car);
        // Arama butonuna tıklandığında arama yap
        SearchView searchView = (SearchView) searchItem.getActionView();
        // Maksiimum genişlik ayarla
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCar(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCar(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // Arama fonksiyonu
    private void searchCar(String query) {
        carAdapter = new CarAdapter(this, dbHelper.searchCar(query));
        carRecyclerView.setAdapter(carAdapter);
    }

}
