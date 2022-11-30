package com.example.gamevui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class IntroRace extends AppCompatActivity {
    TextView phanTramRace;
    ProgressBar vaoGameRace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_race);

        phanTramRace=findViewById(R.id.phanTramRace);
        vaoGameRace=findViewById(R.id.VaogameRace);

        Random random=new Random();
        int x=random.nextInt(30);

        CountDownTimer countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                vaoGameRace.setProgress(vaoGameRace.getProgress()+x+10);
                phanTramRace.setText(vaoGameRace.getProgress()+"%");
                if(vaoGameRace.getProgress() >= 100){
                    this.cancel();
                    phanTramRace.setText(vaoGameRace.getProgress()+"%");
                    Intent intent=new Intent();
                    intent.setClass(IntroRace.this,Gameduaxe.class);
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