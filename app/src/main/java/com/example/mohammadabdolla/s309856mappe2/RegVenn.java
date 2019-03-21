package com.example.mohammadabdolla.s309856mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RegVenn extends Activity{
    EditText navninn;
    EditText telefonnummerinn;
    EditText idinn;
    TextView utskrift;
    DBHandler db;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regvenner);

        navninn = (EditText) findViewById(R.id.venn_navn);
        telefonnummerinn = (EditText) findViewById(R.id.venn_telefon_nummer);
        idinn = (EditText) findViewById(R.id.venn_id);
        utskrift = (TextView) findViewById(R.id.venn_utskrift);
        db = new DBHandler(this);
    }

    public void leggtil(View v) {
        Venn venn = new Venn(   navninn.getText().toString(),
                                telefonnummerinn.getText().toString()
                                );
        Log.d("leggTil",navninn.getText().toString());
        db.leggTilVenn(venn);
        Log.d("Legg inn: ", "legger til kontakter");
    }

    public void visalle(View v) {
        String tekst = "";
        List<Venn> venner = db.finnAlleVenner();
        for (Venn venn : venner) {
            tekst = tekst +
                    " Navn: " + venn.getNavn() +
                    " , TelefonNummer: " + venn.getTelefonNummer() +
                    " , Id: " + venn.get_ID() ;

            Log.d("Navn: ", tekst);
        }
        utskrift.setText(tekst);
    }

    public void slett(View v) {
        Long vennid = (Long.parseLong(idinn.getText().toString()));
        db.slettVenn(vennid);
    }

    public void oppdater(View v) {
        Venn venn = new Venn();
        venn.setNavn(navninn.getText().toString());
        venn.setTelefonNummer(telefonnummerinn.getText().toString());
        venn.set_ID(Long.parseLong(idinn.getText().toString()));
        db.oppdaterVenn(venn);
    }

}
