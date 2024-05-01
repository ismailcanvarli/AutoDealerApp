package com.example.autodealerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.adapters.CarAdapter;
import com.example.autodealerapp.data.DbHelper;
import com.example.autodealerapp.helpers.Constants;
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
    // Araçları göstermek için adapter tanımla
    private CarAdapter carAdapter;

    // Sıralama seçenekleri
    // Eklenme zamanına göre sıralama
    private String sortByNewest = Constants.COLUMN_ADDED_TIME + " DESC";
    private String sortByOldest = Constants.COLUMN_ADDED_TIME + " ASC";
    // alfabetik sıralama
    private String sortByNameAsc = Constants.COLUMN_BRAND + " ASC";
    private String sortByNameDesc = Constants.COLUMN_BRAND + " DESC";
    // Fiat'a göre sıralama
    private String sortByPriceAsc = Constants.COLUMN_PRICE + " ASC";
    private String sortByPriceDesc = Constants.COLUMN_PRICE + " DESC";
    //Mevcut sıralama
    private String currentSortOrder = sortByNewest;

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
        loadData(currentSortOrder);

    }

    // Veritabanından tüm araçları al
    private void loadData(String currentSortOrder) {
        carAdapter = new CarAdapter(this, dbHelper.getAllData(currentSortOrder));
        carRecyclerView.setAdapter(carAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(currentSortOrder); // Veritabanından tüm araçları al ve göster
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

    // Arama fonksiyonu
    private void searchCar(String query) {
        carAdapter = new CarAdapter(this, dbHelper.searchCar(query));
        carRecyclerView.setAdapter(carAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.sort_car) {
            // Sıralama dialogunu göster
            sortDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDialog() {
        // Sıralama seçenekleri
        String[] options = {
                "Newest",
                "Oldest",
                "Alphabetically (A-Z)",
                "Alphabetically (Z-A)",
                "Price (Low to High)",
                "Price (High to Low)"
        };

        // Dialog oluştur
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            // DialogInterface.OnClickListener, kullanıcı bir seçeneğe tıkladığında çağrılır.
            // Bu yöntem, kullanıcı tıkladığında çağrılır ve tıklanan öğenin konumunu ve tıklanan öğenin kimliğini alır. Bu yöntem, AlertDialog.
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Seçilen sıralama seçeneğine göre verileri yükle
                if (i == 0) {
                    // En yeni
                    currentSortOrder = sortByNewest;
                    loadData(currentSortOrder);
                }
                if (i == 1) {
                    // En eski
                    currentSortOrder = sortByOldest;
                    loadData(currentSortOrder);
                }
                if (i == 2) {
                    // Alfabetik (A-Z)
                    currentSortOrder = sortByNameAsc;
                    loadData(currentSortOrder);
                }
                if (i == 3) {
                    // Alfabetik (Z-A)
                    currentSortOrder = sortByNameDesc;
                    loadData(currentSortOrder);
                }
                if (i == 4) {
                    // Fiyata göre artan
                    currentSortOrder = sortByPriceAsc;
                    loadData(currentSortOrder);
                }
                if (i == 5) {
                    // Fiyata göre azalan
                    currentSortOrder = sortByPriceDesc;
                    loadData(currentSortOrder);
                }
            }
        });
        builder.create().show();

    }


}
