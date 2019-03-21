package com.example.mohammadabdolla.s309856mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RegRestaurant extends Activity {
    EditText navninn;
    EditText addresseinn;
    EditText telefonnummerinn;
    EditText typeinn;
    EditText idinn;
    TextView utskrift;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regrestaurant);

        navninn = (EditText) findViewById(R.id.restaurant_navn);
        addresseinn = (EditText) findViewById(R.id.restaurant_addresse);
        telefonnummerinn = (EditText) findViewById(R.id.restaurant_telefon);
        typeinn = (EditText) findViewById(R.id.restaurant_type);
        idinn = (EditText) findViewById(R.id.restaurant_min_id);
        utskrift = (TextView) findViewById(R.id.restaurant_utskrift);
        db = new DBHandler(this);
    }

    public void leggtil(View v) {
        Restaurant restaurant = new Restaurant( navninn.getText().toString(),
                                                addresseinn.getText().toString(),
                                                telefonnummerinn.getText().toString(),
                                                typeinn.getText().toString()
                                                );
        Log.d("leggTil",navninn.getText().toString());
        db.leggTilRestaurant(restaurant);
        Log.d("Legg inn: ", "legger til kontakter");
    }

    public void visalle(View v) {
        String tekst = "";
        List<Restaurant> restauranter = db.finnAlleRestauranter();
        for (Restaurant restaurant : restauranter) {
            tekst = tekst +
                    " Navn: " + restaurant.getNavn() +
                    " ,Adresse: " + restaurant.getAdresse() +
                    " ,TelefonNummer: " + restaurant.getTelefonNummer() +
                    " ,Type: " + restaurant.getType() +
                    " ,Id: " + restaurant.get_ID() ;

                    Log.d("Navn: ", tekst);
        }
        utskrift.setText(tekst);
    }

    public void slett(View v) {
        Long restauranttid = (Long.parseLong(idinn.getText().toString()));
        db.slettRestaurant(restauranttid);
    }

    public void oppdater(View v) {
        Restaurant restaurant = new Restaurant();
        restaurant.setNavn(navninn.getText().toString());
        restaurant.setTelefonNummer(telefonnummerinn.getText().toString());
        restaurant.set_ID(Long.parseLong(idinn.getText().toString()));
        db.oppdaterRestaurant(restaurant);
    }



}