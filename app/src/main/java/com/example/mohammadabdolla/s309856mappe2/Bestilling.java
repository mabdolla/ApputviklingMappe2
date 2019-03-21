package com.example.mohammadabdolla.s309856mappe2;
import java.util.Date;
import java.sql.Time;

public class Bestilling {
    public long Venn_ID;
    public String RestaurantNavn;
    public String Date;
    public String Time;

    public Bestilling() {
    }

    public Bestilling(long venn_ID, String restaurantNavn, String date, String time) {
        Venn_ID = venn_ID;
        RestaurantNavn = restaurantNavn;
        Date = date;
        Time = time;
    }

    public Bestilling(String restaurantNavn, String date, String time) {
        RestaurantNavn = restaurantNavn;
        Date = date;
        Time = time;
    }

    public long getVenn_ID() {
        return Venn_ID;
    }

    public void setVenn_ID(long venn_ID) {
        Venn_ID = venn_ID;
    }

    public String getRestaurantNavn() {
        return RestaurantNavn;
    }

    public void setRestaurantNavn(String restaurantNavn) {
        RestaurantNavn = restaurantNavn;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
