package com.example.mehmood_beeah_wastemanagementsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReciever extends BroadcastReceiver {

    private MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Check your Scheduled Alarm!", Toast.LENGTH_LONG).show();

        // Play alarm sound
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
        mediaPlayer.start();
    }
}
