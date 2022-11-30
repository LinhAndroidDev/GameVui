package com.example.gamevui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ProgressBar vaoGamevui;
    TextView phanTramgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vaoGamevui=findViewById(R.id.Vaogamevui);
        phanTramgame=findViewById(R.id.phanTramgame);

        Random random=new Random();
        int x=random.nextInt(30);

        CountDownTimer countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                vaoGamevui.setProgress(vaoGamevui.getProgress()+x+10);
                phanTramgame.setText(vaoGamevui.getProgress()+"%");
                if(vaoGamevui.getProgress() >= 100){
                    this.cancel();
                    phanTramgame.setText(vaoGamevui.getProgress()+"%");
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}