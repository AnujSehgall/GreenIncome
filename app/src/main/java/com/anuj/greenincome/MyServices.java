package com.anuj.greenincome;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyServices extends Service {
    public MediaPlayer mp;
    int k=0,j=39,curr;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        //When Event is published, onReceive method is called
        public void onReceive(Context c, Intent i) {
            //Get Battery %
            int level = i.getIntExtra("level", 0);
            //Find the progressbar creating in main.xml
            if(curr==j) k=0;
            if (level==40){
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                mp = MediaPlayer.create(getApplicationContext(), notification);
                if(k==0) {
                    k=1;
                    mp.start();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            mp.stop();
                        }
                    }, 6000);
                }curr=level;

            }
        }

    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        registerReceiver(mBatInfoReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}