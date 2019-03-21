package com.example.mohammadabdolla.s309856mappe2;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MinService extends Service{
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();
        //SendSMS();
        return super.onStartCommand(intent, flags, startId);
    }

    public void SendSMS() {
        int MY_PERMISSIONS_REQUEST_SEND_SMS = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int MY_PHONE_STATE_PERMISSION = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);


        if(MY_PERMISSIONS_REQUEST_SEND_SMS == PackageManager.PERMISSION_GRANTED &&
                MY_PHONE_STATE_PERMISSION ==
                        PackageManager.PERMISSION_GRANTED) {
            sendMessage();

        }

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notifikasjon = new NotificationCompat.Builder(this)
                .setContentTitle("Notifikasjon")
                .setContentText("Funnet bestilling")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notifikasjon.flags|= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notifikasjon);

        }


    public void sendMessage (){
        DBHandler db = new DBHandler(this);
    List<Bestilling> bestillinger = db.hentBestillinger();
        for(Bestilling b : bestillinger)   {
            // hent venn
            Venn venn = db.hentVenn(b.getVenn_ID());
            String telefon = venn.getTelefonNummer();
            String message = "Bestilling finnes";
            SmsManager smsMan= SmsManager.getDefault();
            smsMan.sendTextMessage(telefon, null, message, null, null);
            Toast.makeText(this, "Har sendt sms", Toast.LENGTH_SHORT).show();



        }



    }
}


