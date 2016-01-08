package com.robosoft.archanakumari.androideventmanager;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;

public class AlarmReceiverActivity extends AppCompatActivity {

    private MediaPlayer mMediapPlayer;
    private  PowerManager.WakeLock mWeckLog;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWeckLog = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"My Wake Log");
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mWeckLog.acquire();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_alarm_receiver);

        button = (Button) findViewById(R.id.stopalarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediapPlayer.stop();
                finish();
            }
        });
        playSound(this, getAlarmUri());
    }
    private void playSound(Context context,Uri alert){

        mMediapPlayer = new MediaPlayer();
        try {
            mMediapPlayer.setDataSource(context,alert);
            final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if(audioManager.getStreamVolume(AudioManager.STREAM_ALARM)!=0){
                mMediapPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediapPlayer.prepare();
                mMediapPlayer.start();
            }
        } catch (IOException e) {
            Log.i("Hello", "No audio file is found");
            e.printStackTrace();
        }
    }
    private Uri getAlarmUri(){
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            if (alert == null) {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }
    @Override
    protected void onStop() {
        super.onStop();
        mWeckLog.release();
    }

}
