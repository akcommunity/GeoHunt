package com.google.ar.core.examples.java.geospatial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    public MediaPlayer mediaPlayer;  PowerManager.WakeLock wakeLock;
     PowerManager powerManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
          mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
             powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
    wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag");
    wakeLock.acquire();

    //on click startHunt button go to GeospatialActivity
    findViewById(R.id.startHunt).setOnClickListener(view -> {
        Intent intent = new Intent(this, GeospatialActivity.class);

        intent.putExtra("mode", "read");
        startActivity(intent);

    });
        findViewById(R.id.createHunt).setOnClickListener(view -> {
            Intent intent = new Intent(this, GeospatialActivity.class); 
            intent.putExtra("mode", "write");
            startActivity(intent);

        });
    //hide  on click hidehint switch toggled off otherwise on, handle null view, hide clueimage

    
    

    //play mp3 when app starts

    }

    public void onPause(){
        super.onPause();
        mediaPlayer.pause();
        wakeLock.release();
    }
    public void onResume()
    {
       super.onResume();
         mediaPlayer.start();
    }
    public void onDestroy()
    {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }


}