package com.example.autodealerapp.helpers;

public class Constants {

    // Veritabanı adı
    public static final String DATABASE_NAME = "CAR_DATABASE";

    // Veritabanı sürümü
    public static final int DATABASE_VERSION = 1;

    // Tablo adı
    public static final String TABLE_NAME = "CAR_TABLE";

    // Tablo sütunları
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BRAND = "BRAND";
    public static final String COLUMN_MODEL = "MODEL";
    public static final String COLUMN_YEAR = "YEAR";
    public static final String COLUMN_KILOMETER = "KILOMETER";
    public static final String COLUMN_COLOR = "COLOR";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_ADDED_DATE = "ADDED_DATE";
    public static final String COLUMN_UPDATED_DATE = "UPDATED_DATE";

    // Tablo oluşturma sorgusu
    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BRAND + " TEXT,"
            + COLUMN_MODEL + " TEXT,"
            + COLUMN_YEAR + " INTEGER,"
            + COLUMN_KILOMETER + " INTEGER,"
            + COLUMN_COLOR + " TEXT,"
            + COLUMN_PRICE + " REAL,"
            + COLUMN_ADDED_DATE + " TEXT,"
            + COLUMN_UPDATED_DATE + " TEXT"
            + ")";

    // Veritabanı CRUD işlemleri
}