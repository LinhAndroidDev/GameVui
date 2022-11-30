package com.example.gamevui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class IntroDice extends AppCompatActivity {
    TextView phanTramDice;
    ProgressBar vaoGameDice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_dice);

        phanTramDice=findViewById(R.id.phanTramDice);
        vaoGameDice=findViewById(R.id.VaogameDice);

        Random random=new Random();
        int x=random.nextInt(30);

        CountDownTimer countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                vaoGameDice.setProgress(vaoGameDice.getProgress()+x+10);
                phanTramDice.setText(vaoGameDice.getProgress()+"%");
                if(vaoGameDice.getProgress() >= 100){
                    this.cancel();
                    phanTramDice.setText(vaoGameDice.getProgress()+"%");
                    Intent intent=new Intent();
                    intent.setClass(IntroDice.this,Dice.class);
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