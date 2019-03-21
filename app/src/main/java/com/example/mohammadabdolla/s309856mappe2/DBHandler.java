package com.example.mohammadabdolla.s309856mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_RESTAURANTER = "Restauranter";
    static String KEY_RESTAURANT_ID = "_ID";
    static String KEY_RESTAURANT_NAME = "Navn";
    static String KEY_RESTAURANT_ADDRESS ="Adresse";
    static String KEY_RESTAURANT_PH_NO = "Telefon";
    static String KEY_RESTAURANT_TYPE = "Type";

    static String TABLE_VENNER = "Venner";
    static String KEY_VENN_ID = "_ID";
    static String KEY_VENN_NAME = "Navn";
    static String KEY_VENN_PH_NO = "Telefon";

    static String TABLE_BESTILLING = "Bestilling";
    static String KEY_BESTILLING_VENN_ID = "_ID";
    static String KEY_BESTILLING_RESTAURANT_NAME = "RestaurantNavn";
    static String KEY_BESTILLING_DATE = "Dato";
    static String KEY_BESTILLING_TIME = "Tid";

    static int DATABASE_VERSION = 7;
    static String DATABASE_NAME = "RestaurantensVenner";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_TABELL_RESTAURANTER =
                "CREATE TABLE " + TABLE_RESTAURANTER +
                "(" +
                KEY_RESTAURANT_ID + " INTEGER PRIMARY KEY," +
                KEY_RESTAURANT_NAME + " TEXT," +
                KEY_RESTAURANT_PH_NO + " TEXT," +
                KEY_RESTAURANT_ADDRESS + " TEXT," +
                KEY_RESTAURANT_TYPE + " TEXT" +
                ")";
        Log.d("SQL", LAG_TABELL_RESTAURANTER);
        db.execSQL(LAG_TABELL_RESTAURANTER);

        String LAG_TABELL_VENNER =
                "CREATE TABLE " + TABLE_VENNER +
                "(" +
                KEY_VENN_ID + " INTEGER PRIMARY KEY," +
                KEY_VENN_NAME + " TEXT," +
                KEY_VENN_PH_NO + " TEXT" +
                ")";
        Log.d("SQL", LAG_TABELL_VENNER);
        db.execSQL(LAG_TABELL_VENNER);

        String LAG_TABELL_BESTILLING =
                "CREATE TABLE " + TABLE_BESTILLING +
                "(" +
                KEY_BESTILLING_VENN_ID + " INTEGER," +
                KEY_BESTILLING_RESTAURANT_NAME + " TEXT," +
                KEY_BESTILLING_DATE + " TEXT," +
                KEY_BESTILLING_TIME + " TEXT" +
                ")";
        Log.d("SQL", LAG_TABELL_BESTILLING);
        db.execSQL(LAG_TABELL_BESTILLING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENNER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BESTILLING);
        onCreate(db);
    }

    public void leggTilRestaurant(Restaurant restaurant) {
        Log.d("DBleggTIl",restaurant.getNavn());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RESTAURANT_NAME, restaurant.getNavn());
        values.put(KEY_RESTAURANT_ADDRESS, restaurant.getAdresse());
        values.put(KEY_RESTAURANT_PH_NO, restaurant.getTelefonNummer());
        values.put(KEY_RESTAURANT_TYPE, restaurant.getType());
        long insert = db.insert(TABLE_RESTAURANTER, null, values);
        Log.d("DBleggTIl", String.valueOf(insert));
        db.close();
    }

    public void leggTilVenn (Venn venn) {
        Log.d("DBleggTil", venn.getNavn());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_VENN_NAME, venn.getNavn());
        values.put(KEY_VENN_PH_NO, venn.getTelefonNummer());

        long insert = db.insert(TABLE_VENNER, null, values);
        Log.d("DBleggTil", String.valueOf(insert));
        Log.d("DBleggTil", venn.getNavn());
        db.close();
    }

    public void leggTilBestilling(Bestilling bestilling) {
        Log.d("DBleggTIl",bestilling.getRestaurantNavn());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_BESTILLING_VENN_ID, bestilling.getVenn_ID());
        values.put(KEY_BESTILLING_RESTAURANT_NAME, bestilling.getRestaurantNavn());
        values.put(KEY_BESTILLING_DATE, bestilling.getDate());
        values.put(KEY_BESTILLING_TIME, bestilling.getTime());

        long insert = db.insert(TABLE_BESTILLING, null, values);
        Log.d("DBleggTIl", String.valueOf(insert));
        db.close();
    }

    public List<Restaurant> finnAlleRestauranter() {
        List<Restaurant> restaurantListe = new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.set_ID(cursor.getInt(cursor.getColumnIndex(KEY_RESTAURANT_ID)));
                restaurant.setNavn(cursor.getString(cursor.getColumnIndex(KEY_RESTAURANT_NAME)));
                restaurant.setTelefonNummer(cursor.getString(cursor.getColumnIndex(KEY_RESTAURANT_PH_NO)));
                restaurantListe.add(restaurant);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return restaurantListe;
    }

    public List<Venn> finnAlleVenner() {
        List<Venn> vennListe = new ArrayList<Venn>();
        String selectQuery = "SELECT * FROM " + TABLE_VENNER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Venn venn = new Venn();
                venn.set_ID(cursor.getInt(cursor.getColumnIndex(KEY_VENN_ID)));
                venn.setNavn(cursor.getString(cursor.getColumnIndex(KEY_VENN_NAME)));
                venn.setTelefonNummer(cursor.getString(cursor.getColumnIndex(KEY_VENN_PH_NO)));
                vennListe.add(venn);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return vennListe;
    }

    public Venn hentVenn(long idinn) {
        Venn venn = null;
        String selectQuery = "SELECT * FROM " + TABLE_VENNER + "WHERE " + KEY_VENN_ID + '='+ idinn ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                venn = new Venn();
                venn.set_ID(cursor.getInt(cursor.getColumnIndex(KEY_VENN_ID)));
                venn.setNavn(cursor.getString(cursor.getColumnIndex(KEY_VENN_NAME)));
                venn.setTelefonNummer(cursor.getString(cursor.getColumnIndex(KEY_VENN_PH_NO)));


            } while (cursor.moveToNext());
                cursor.close();
                  db.close();
        }
        return venn;
    }



    public List<Bestilling> hentBestillinger(){
        return finnAlleBestillinger();
    }

    public List<Bestilling> finnAlleBestillinger() {
        List<Bestilling> bestillingsListe = new ArrayList<Bestilling>();
        String selectQuery = "SELECT * FROM " + TABLE_BESTILLING;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Bestilling bestilling = new Bestilling();
                bestilling.setVenn_ID(cursor.getInt(cursor.getColumnIndex(KEY_BESTILLING_VENN_ID)));
                bestilling.setRestaurantNavn(cursor.getString(cursor.getColumnIndex(KEY_BESTILLING_RESTAURANT_NAME)));
                bestilling.setDate(cursor.getString(cursor.getColumnIndex(KEY_BESTILLING_DATE)));
                bestilling.setTime(cursor.getString(cursor.getColumnIndex(KEY_BESTILLING_TIME)));
                bestillingsListe.add(bestilling);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return bestillingsListe;
    }

    public void slettRestaurant(Long inn_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTER, KEY_RESTAURANT_ID + " =? ",
                new String[]{Long.toString(inn_id)});
        db.close();
    }

    public void slettVenn (long inn_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VENNER, KEY_VENN_ID + " =? ",
                new String[]{Long.toString(inn_id)});
        db.close();
    }

    public void slettBestilling(Long inn_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BESTILLING, KEY_BESTILLING_VENN_ID + " =? ",
                new String[]{Long.toString(inn_id)});
        db.close();
    }

    public int oppdaterRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RESTAURANT_NAME, restaurant.getNavn());
        values.put(KEY_RESTAURANT_PH_NO, restaurant.getTelefonNummer());
        int endret = db.update(TABLE_RESTAURANTER, values, KEY_RESTAURANT_ID + "= ?",
                new String[]{String.valueOf(restaurant.get_ID())});
        db.close();
        return endret;
    }

    public int oppdaterVenn (Venn venn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VENN_NAME, venn.getNavn());
        values.put(KEY_VENN_PH_NO, venn.getTelefonNummer());
        int endret = db.update(TABLE_VENNER, values, KEY_VENN_ID + "= ?",
                new String[] {String.valueOf((venn.get_ID()))});
        db.close();
        return endret;
    }

    public int oppdaterBestilling(Bestilling bestilling) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_BESTILLING_RESTAURANT_NAME, bestilling.getRestaurantNavn());
        values.put(KEY_BESTILLING_DATE, bestilling.getDate());

        int endret = db.update(TABLE_BESTILLING, values, KEY_BESTILLING_VENN_ID + "= ?",
                new String[]{String.valueOf(bestilling.getVenn_ID())});
        db.close();
        return endret;
    }

    public int finnAntallRestauranter() {
        String sql = "SELECT * FROM " + TABLE_RESTAURANTER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int antall = cursor.getCount();
        cursor.close();
        db.close();
        return antall;
    }

    /*public Restaurant finnRestaurant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANTER, new String[]{
                        KEY_RESTAURANT_ID, KEY_RESTAURANT_NAME, KEY_RESTAURANT_PH_NO}, KEY_RESTAURANT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Restaurant restaurant = new
                Restaurant(Long.parseLong(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return restaurant;
    }

    */
}
