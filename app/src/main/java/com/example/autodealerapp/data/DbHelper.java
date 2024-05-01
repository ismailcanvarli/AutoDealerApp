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
    public long insertCar(String brand, String model, String color, String year, String kilometer, String price, String addedTime, String updatedTime) {
        // Veritabanı yazılabilir modda açılır
        SQLiteDatabase db = this.getWritableDatabase();
        // ContentValues nesnesi oluşturulur ve veriler eklenir
        ContentValues contentValues = new ContentValues();

        // Araç bilgileri eklenir. Id otomatik artan olduğu için eklemeye gerek yok.
        contentValues.put(Constants.COLUMN_BRAND, brand);
        contentValues.put(Constants.COLUMN_MODEL, model);
        contentValues.put(Constants.COLUMN_COLOR, color);
        contentValues.put(Constants.COLUMN_YEAR, year);
        contentValues.put(Constants.COLUMN_KILOMETER, kilometer);
        contentValues.put(Constants.COLUMN_PRICE, price);
        contentValues.put(Constants.COLUMN_ADDED_TIME, addedTime);
        contentValues.put(Constants.COLUMN_UPDATED_TIME, updatedTime);

        // Veritabanına ekleme işlemi yapılır
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);

        db.close();
        return id;
    }

    // Araç güncelleme işlemi
    public void updateCar(String id, String brand, String model, String color, String year, String kilometer, String price, String addedTime, String updatedTime) {
        // Veritabanı yazılabilir modda açılır
        SQLiteDatabase db = this.getWritableDatabase();
        // ContentValues nesnesi oluşturulur ve veriler eklenir
        ContentValues contentValues = new ContentValues();

        // Araç bilgileri eklenir.
        contentValues.put(Constants.COLUMN_BRAND, brand);
        contentValues.put(Constants.COLUMN_MODEL, model);
        contentValues.put(Constants.COLUMN_COLOR, color);
        contentValues.put(Constants.COLUMN_YEAR, year);
        contentValues.put(Constants.COLUMN_KILOMETER, kilometer);
        contentValues.put(Constants.COLUMN_PRICE, price);
        contentValues.put(Constants.COLUMN_ADDED_TIME, addedTime);
        contentValues.put(Constants.COLUMN_UPDATED_TIME, updatedTime);

        // Veritabanında güncelleme işlemi yapılır
        db.update(Constants.TABLE_NAME, contentValues, Constants.COLUMN_ID + " = ?", new String[]{id});

        db.close();
    }

    // Id ile araç silme işlemi
    public void deleteCar(String id) {
        // Veritabanı yazılabilir modda açılır
        SQLiteDatabase db = this.getWritableDatabase();
        // Veritabanında silme işlemi yapılır
        db.delete(Constants.TABLE_NAME, Constants.COLUMN_ID + " = ?", new String[]{id});
        db.close();
    }

    // Arama işlemi
    public ArrayList<ModelCar> searchCar(String query) {
        // ModelCar listesi oluşturulur
        ArrayList<ModelCar> carArrayList = new ArrayList<>();

        // Veritabanı okuma modunda açılır
        SQLiteDatabase db = getWritableDatabase();

        String selecetQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.COLUMN_BRAND + " LIKE '%" + query + "%' OR " + Constants.COLUMN_MODEL + " LIKE '%" + query + "%' OR " + Constants.COLUMN_COLOR + " LIKE '%" + query + "%' OR " + Constants.COLUMN_YEAR + " LIKE '%" + query + "%' OR " + Constants.COLUMN_KILOMETER + " LIKE '%" + query + "%' OR " + Constants.COLUMN_PRICE + " LIKE '%" + query + "%'";

        // Sorguyu çalıştır ve sonucu al
        Cursor cursor = db.rawQuery(selecetQuery, null);

        // Verileri al ve modelCarList'e ekle
        if (cursor.moveToFirst()) {
            do {
                ModelCar modelCar = new ModelCar(
                        // Sütun isimlerini yazarak verileri alabiliriz.
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_BRAND)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_MODEL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_YEAR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_KILOMETER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_PRICE))
                );

                carArrayList.add(modelCar);
            } while (cursor.moveToNext());
        }

        db.close();
        return carArrayList;
    }


    // Tüm araçları getirme işlemi
    public ArrayList<ModelCar> getAllData(String sortOrder) {
        // ModelCar listesi oluşturulur
        ArrayList<ModelCar> carArrayList = new ArrayList<>();

        // Tüm verileri getirme sorgusu
        String selecetQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + sortOrder;

        // Veritabanı okuma modunda açılır
        SQLiteDatabase db = getWritableDatabase();

        // Sorguyu çalıştır ve sonucu al
        Cursor cursor = db.rawQuery(selecetQuery, null);

        // Verileri al ve modelCarList'e ekle
        if (cursor.moveToFirst()) {
            do {
                ModelCar modelCar = new ModelCar(
                        // Sütun isimlerini yazarak verileri alabiliriz.
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_BRAND)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_MODEL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_YEAR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_KILOMETER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_PRICE))
                );

                carArrayList.add(modelCar);
            } while (cursor.moveToNext());
        }

        db.close();
        return carArrayList;
    }

}
