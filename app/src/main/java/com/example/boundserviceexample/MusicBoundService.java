package com.example.boundserviceexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.Printer;

import androidx.annotation.Nullable;

public class MusicBoundService extends Service {

    private MyBinder mBinder = new MyBinder();
    private MediaPlayer mMediaPlayer;

    //su dung Binder class de giao tiep voi service
    public class MyBinder extends Binder {
        MusicBoundService getMusicBoundService() {
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MusicBoundService", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MusicBoundService", "onBind");
        return mBinder;

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("MusicBoundService", "onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MusicBoundService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("MusicBoundService", "onDestroy");
        super.onDestroy();
        //huy service dong nghia voi media = null
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void startMusic() {
        //start service dong nghia start media
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.baiso01);
            mMediaPlayer.start();
        }
    }
}
