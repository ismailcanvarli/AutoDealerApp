package com.example.autodealerapp.helpers;

public class Constants {

    // Veritabanı adı
    public static final String DATABASE_NAME = "CAR_DB";

    // Veritabanı sürümü
    public static final int DATABASE_VERSION = 1;

    // Tablo adı
    public static final String TABLE_NAME = "CAR_TABLE";

    // Tablo sütunları
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BRAND = "BRAND";
    public static final String COLUMN_MODEL = "MODEL";
    public static final String COLUMN_COLOR = "COLOR";
    public static final String COLUMN_YEAR = "YEAR";
    public static final String COLUMN_KILOMETER = "KILOMETER";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_ADDED_TIME = "ADDED_TIME";
    public static final String COLUMN_UPDATED_TIME = "UPDATED_TIME";

    // Tablo oluşturma sorgusu
    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BRAND + " TEXT,"
            + COLUMN_MODEL + " TEXT,"
            + COLUMN_COLOR + " TEXT,"
            + COLUMN_YEAR + " TEXT,"
            + COLUMN_KILOMETER + " TEXT,"
            + COLUMN_PRICE + " TEXT,"
            + COLUMN_ADDED_TIME + " TEXT,"
            + COLUMN_UPDATED_TIME + " TEXT"
            + ")";

}