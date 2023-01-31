package com.example.gamevui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView here;
    ImageButton iconCaro,iconDice,iconRace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWinDow();
        setContentView(R.layout.activity_second);

        iconCaro = findViewById(R.id.iconCaro);
        iconDice = findViewById(R.id.iconDice);
        iconRace = findViewById(R.id.iconRace);
        here = findViewById(R.id.gameOther);

        here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.gamevui.vn/"));
                startActivity(intent);
            }
        });

        here.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

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

    private void setWinDow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}