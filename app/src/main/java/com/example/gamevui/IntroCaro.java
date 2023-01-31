package com.example.gamevui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class IntroCaro extends AppCompatActivity {
    TextView phanTramCaro;
    ProgressBar vaoGameCaro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWinDow();
        setContentView(R.layout.activity_intro_caro);

        phanTramCaro=findViewById(R.id.phanTramCaro);
        vaoGameCaro=findViewById(R.id.VaogameCaro);

        Random random=new Random();
        int x=random.nextInt(30);

        CountDownTimer countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                vaoGameCaro.setProgress(vaoGameCaro.getProgress()+x+10);
                phanTramCaro.setText(vaoGameCaro.getProgress()+"%");
                if(vaoGameCaro.getProgress() >= 100){
                    this.cancel();
                    vaoGameCaro.setProgress(vaoGameCaro.getProgress()+x);
                    phanTramCaro.setText(vaoGameCaro.getProgress()+"%");
                    Intent intent=new Intent();
                    intent.setClass(IntroCaro.this,Caro.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void setWinDow() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {

    }
}