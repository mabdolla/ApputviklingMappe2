package com.example.mohammadabdolla.s309856mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SettPeriodiskService extends Service {
   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
       return null;
   }

   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
       Calendar cal= Calendar.getInstance();
       Intent i = new Intent(this, MinService.class);
       PendingIntent pintent= PendingIntent.getService(this, 0, i, 0);
       AlarmManager alarm =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
       alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000 * 60 * 24, pintent);
       return super.onStartCommand(intent, flags, startId);
   }
}
