package com.example.autodealerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.autodealerapp.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarDatabaseHelper extends SQLiteOpenHelper {

    // Veritabanı bilgileri
    private static final String DATABASE_NAME = "car_dealer.db";
    private static final int DATABASE_VERSION = 1;

    // Tablo adı ve sütunları
    public static final String TABLE_NAME = "cars";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_KILOMETER = "kilometer";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_PRICE = "price";

    // Tablo oluşturma sorgusu
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_BRAND + " TEXT," + COLUMN_MODEL + " TEXT," + COLUMN_YEAR + " INTEGER," + COLUMN_KILOMETER + " INTEGER," + COLUMN_COLOR + " TEXT," + COLUMN_PRICE + " REAL)";

    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tablo oluşturma işlemi
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Veritabanı sürümü değiştiğinde gerekli güncelleme işlemleri burada yapılır
        // Şu an için basitçe tabloyu yeniden oluşturuyoruz
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Araç ekleme işlemi
    public long addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BRAND, car.getBrand());
        values.put(COLUMN_MODEL, car.getModel());
        values.put(COLUMN_YEAR, car.getYear());
        values.put(COLUMN_KILOMETER, car.getKilometer());
        values.put(COLUMN_COLOR, car.getColor());
        values.put(COLUMN_PRICE, car.getPrice());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    // Tüm araçları al
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_BRAND, COLUMN_MODEL, COLUMN_YEAR, COLUMN_KILOMETER, COLUMN_COLOR, COLUMN_PRICE};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setBrand(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BRAND)));
                car.setModel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL)));
                car.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_YEAR)));
                car.setKilometer(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_KILOMETER)));
                car.setColor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR)));
                car.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                carList.add(car);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return carList;
    }
}
