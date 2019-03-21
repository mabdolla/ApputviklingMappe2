package com.example.mohammadabdolla.s309856mappe2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if(MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED &&
                MY_PHONE_STATE_PERMISSION ==
                        PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Har Permisjon", Toast.LENGTH_SHORT).show();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_PHONE_STATE},0);
        }
        Intent intent= new Intent();
        intent.setAction("com.example.serviceeksempel.mittbroadcast");
        sendBroadcast(intent);
    }

    public void visRestaurant(View view) {
        Intent intent = new Intent(this, RegRestaurant.class);
        startActivity(intent);
    }

    public void visVenn(View view) {
        Intent intent = new Intent(this, RegVenn.class);
        startActivity(intent);
    }

    public void visBestillinger(View view) {
        Intent intent = new Intent(this, RegBestilling.class);
        startActivity(intent);
    }

    public void startService(View v) {
        Intent intent= new Intent(this, MinService.class);this.startService(intent);
    }
}
