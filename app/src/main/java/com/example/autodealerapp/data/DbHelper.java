package com.example.autodealerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.autodealerapp.helpers.Constants;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    // DbHelper sınıfı oluşturulur
    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    // Tablo sütunları tanımlama
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_QUERY);
    }

    // Veritabanı sürümü değiştiğinde tabloyu güncelle
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Tablo güncelleme sorgusunu çalıştır
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    // Araç ekleme işlemi
    public long insertCar(String brand, String model, String year, String kilometer, String color, String price, String addedDate, String updatedDate) {
        // Veritabanı yazılabilir modda açılır
        SQLiteDatabase db = this.getWritableDatabase();
        // ContentValues nesnesi oluşturulur ve veriler eklenir
        ContentValues values = new ContentValues();

        // Araç bilgileri eklenir
        values.put(Constants.COLUMN_BRAND, brand);
        values.put(Constants.COLUMN_MODEL, model);
        values.put(Constants.COLUMN_YEAR, year);
        values.put(Constants.COLUMN_KILOMETER, kilometer);
        values.put(Constants.COLUMN_COLOR, color);
        values.put(Constants.COLUMN_PRICE, price);
        values.put(Constants.COLUMN_ADDED_DATE, addedDate);
        values.put(Constants.COLUMN_UPDATED_DATE, updatedDate);

        // Veritabanına ekleme işlemi yapılır
        long id = db.insert(Constants.TABLE_NAME, null, values);

        db.close();
        return id;
    }

    public ArrayList<ModelCar> getAllData() {
        // ModelCar listesi oluşturulur
        ArrayList<ModelCar> carArrayList = new ArrayList<>();

        // Tüm verileri getirme sorgusu
        String selecetQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        // Veritabanı okuma modunda açılır
        SQLiteDatabase db = getWritableDatabase();

        // Sorguyu çalıştır ve sonucu al
        Cursor cursor = db.rawQuery(selecetQuery, null);

        // Verileri al ve modelCarList'e ekle
        if (cursor.moveToFirst()) {
            do {
                ModelCar modelCar = new ModelCar(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COLUMN_ID)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_BRAND)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_MODEL)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_YEAR)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_KILOMETER)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_COLOR)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_PRICE)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_ADDED_DATE)),
                        "" +  cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_UPDATED_DATE))
                );

                carArrayList.add(modelCar);
            } while (cursor.moveToNext());
        }

        db.close();
        return carArrayList;
    }

}
