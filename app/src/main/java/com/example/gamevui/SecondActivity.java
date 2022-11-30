package com.example.gamevui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {
    ImageButton iconCaro,iconDice,iconRace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        iconCaro=findViewById(R.id.iconCaro);
        iconDice=findViewById(R.id.iconDice);
        iconRace=findViewById(R.id.iconRace);

        Intent intentCaro=new Intent();
        Intent intentDice=new Intent();
        Intent intentRace=new Intent();

        iconCaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCaro.setClass(SecondActivity.this,IntroCaro.class);
                startActivity(intentCaro);
            }
        });

        iconDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentDice.setClass(SecondActivity.this,IntroDice.class);
                startActivity(intentDice);
            }
        });

        iconRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentRace.setClass(SecondActivity.this,IntroRace.class);
                startActivity(intentRace);
            }
        });
    }
}