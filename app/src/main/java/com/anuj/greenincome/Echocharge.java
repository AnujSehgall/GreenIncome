package com.anuj.greenincome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Echocharge extends AppCompatActivity {
    public MediaPlayer mp;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        //When Event is published, onReceive method is called
        public void onReceive(Context c, Intent i) {
            //Get Battery %
            int level = i.getIntExtra("level", 0);
            //Find the progressbar creating in main.xml
            ProgressBar pb = (ProgressBar) findViewById(R.id.progressbar);
            //Set progress level with battery % value
            pb.setProgress(level);
            //Find textview control created in main.xml
            TextView tv = (TextView) findViewById(R.id.textfield);
            //Set TextView with text
            tv.setText("Battery Level: " + Integer.toString(level) + "%");
            if (level==100){
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                mp = MediaPlayer.create(getApplicationContext(), notification);
                mp.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        mp.stop();
                    }
                }, 6000);
            }
        }

    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set layout we created
        setContentView(R.layout.activity_echocharge);
        //Register the receiver which triggers event
        //when battery charge is changed
        registerReceiver(mBatInfoReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));


    }
}