package com.example.boundserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MusicBoundService mMusicBoundService;
    private boolean isServiceConnected;
    Button mBtnStart, mBtnStop;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicBoundService.MyBinder myBinder= (MusicBoundService.MyBinder) iBinder;
            mMusicBoundService= myBinder.getMusicBoundService();
            mMusicBoundService.startMusic();
            isServiceConnected=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceConnected=false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItem();
        
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStartService();
            }
        });
        
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStopService();
            }
        });
    }

    private void onClickStopService() {
        if(isServiceConnected)
        {
            unbindService(serviceConnection);
            //onServiceDisconnected chay vao khi chet dot ngot, chu khong phai thao bo rang buoc nhay vao ham do
            //cho nen can gan isServiceConnected=false
            isServiceConnected=false;
        }
    }

    private void onClickStartService() {
        Intent intent=new Intent(this, MusicBoundService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    private void initItem() {
        mBtnStart=findViewById(R.id.btnStartBoundService);
        mBtnStop=findViewById(R.id.btnStopBoundService);
    }
}