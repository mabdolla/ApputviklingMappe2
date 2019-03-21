package com.example.mohammadabdolla.s309856mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RegBestilling extends Activity{

    EditText idinn;
    EditText restaurantnavninn;
    EditText dateinn;
    EditText timeinn;
    TextView utskrift;
    DBHandler db;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regbestilling);
        idinn = (EditText) findViewById(R.id.bestilling_venn_id);
        restaurantnavninn = (EditText) findViewById(R.id.bestilling_restaurant_navn);
        dateinn = (EditText) findViewById(R.id.bestilling_date);
        timeinn = (EditText) findViewById(R.id.bestilling_time);
        utskrift = (TextView) findViewById(R.id.bestilling_utskrift);
        db = new DBHandler(this);
    }

    public void leggtil(View v) {
        Bestilling bestilling = new Bestilling( Long.valueOf(idinn.getText().toString()),
                restaurantnavninn.getText().toString(),
                dateinn.getText().toString(),
                timeinn.getText().toString()
        );
        Log.d("leggTil",idinn.getText().toString());
        db.leggTilBestilling(bestilling);
        Log.d("Legg inn: ", "legger til kontakter");
    }

    public void visalle(View v) {
        String tekst = "";
        List<Bestilling> bestillinger = db.finnAlleBestillinger();
        for (Bestilling bestilling : bestillinger) {
            tekst = tekst +
                    " Venn ID: " + bestilling.getVenn_ID() +
                    " ,Restaurant navn: " + bestilling.getRestaurantNavn() +
                    " ,Dato: " + bestilling.getDate() +
                    " ,Time: " + bestilling.getTime();

            Log.d("Navn: ", tekst);
        }
        utskrift.setText(tekst);
    }

}
