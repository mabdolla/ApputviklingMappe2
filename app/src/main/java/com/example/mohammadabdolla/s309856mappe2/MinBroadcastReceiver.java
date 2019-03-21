package com.example.mohammadabdolla.s309856mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MinBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I BroadcastReceiver", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, SettPeriodiskService.class);
        context.startService(i);
    }
}
