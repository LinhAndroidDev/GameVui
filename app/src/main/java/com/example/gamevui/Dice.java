package com.example.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class Dice extends AppCompatActivity {
    ToggleButton button;
    ImageView ketqua;
    ImageView imageView,imageView2,imageView3;
    ArrayList<Integer> imagelist;
    CheckBox Tai,Xiu;
    TextView thongbao;
    TextView score,phantram;
    ProgressBar detination;
    ImageView winloss,chan,le;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        ketqua=findViewById(R.id.icon);
        Tai=findViewById(R.id.Xiu);
        Xiu=findViewById(R.id.Tai);
        chan=findViewById(R.id.chan);
        le=findViewById(R.id.le);
        thongbao=findViewById(R.id.Thongbao);
        score=findViewById(R.id.score);
        phantram=findViewById(R.id.phantram);
        winloss=findViewById(R.id.winloss);
        detination=findViewById(R.id.results);

        //back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagelist=new ArrayList<>();
        imagelist.add(0,R.drawable.number1);
        imagelist.add(1,R.drawable.number2);
        imagelist.add(2,R.drawable.number3);
        imagelist.add(3,R.drawable.number4);
        imagelist.add(4,R.drawable.number5);
        imagelist.add(5,R.drawable.number6);

        //Create Animation,Mp3
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.animation_dice);
        Animation animation1=AnimationUtils.loadAnimation(this,R.anim.button_animation);
        Animation result = AnimationUtils.loadAnimation(this,R.anim.result);
        Animation taixiu = AnimationUtils.loadAnimation(this,R.anim.taixiu);
        Animation bigtosmall=AnimationUtils.loadAnimation(this,R.anim.bigtosmall);
        Animation smalltobig=AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        Animation animation_left=AnimationUtils.loadAnimation(this,R.anim.animtion_left);
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.tienglacxucxac_dice);
        MediaPlayer win=MediaPlayer.create(this,R.raw.win_dice);
        MediaPlayer loss=MediaPlayer.create(this,R.raw.lose_caro);
        MediaPlayer gameOver=MediaPlayer.create(this,R.raw.tiengbaoloi_dice);
        Check();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.startAnimation(animation1);
                if(detination.getProgress() == detination.getMax() || detination.getProgress() == 0){
                    detination.setProgress(50);
                    phantram.setText(50+"%");
                }
                if (Tai.isChecked() == false && Xiu.isChecked() == false) {
                    AlertDialog.Builder alBuilder=new AlertDialog.Builder(Dice.this);
                    alBuilder.setTitle("Thông Báo!!");
                    alBuilder.setMessage("Bạn chưa chọn Tài Xỉu!"+"\nVui lòng chọn Tài Xỉu.");
                    alBuilder.setIcon(R.drawable.icon);
                    alBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alBuilder.show();
                } else {
                    //set INVISIBLE
                    winloss.setVisibility(View.INVISIBLE);
                    thongbao.setVisibility(View.INVISIBLE);
                    ketqua.setVisibility(View.INVISIBLE);
                    Tai.setVisibility(View.INVISIBLE);
                    Xiu.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    chan.setVisibility(View.INVISIBLE);
                    le.setVisibility(View.INVISIBLE);

                    //set enable
                    Tai.setEnabled(false);
                    Xiu.setEnabled(false);

                    //set thông báo
                    thongbao.setText("");

                    //Tạo mặt xúc xắc
                    Random random = new Random();
                    int number1 = random.nextInt(imagelist.size());
                    int number2 = random.nextInt(imagelist.size());
                    int number3 = random.nextInt(imagelist.size());
                    int Tong = number1 + number2 + number3 + 3;
                    imageView.startAnimation(animation);
                    imageView2.startAnimation(animation);
                    imageView3.startAnimation(animation);
                    mediaPlayer.start();

                    //set animation xúc xắc
                    CountDownTimer countDownTimer=new CountDownTimer(2000,2000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            //set animation
                            imageView.startAnimation(taixiu);
                            imageView2.startAnimation(taixiu);
                            imageView3.startAnimation(taixiu);

                            //set xúc xắc
                            Drawable img = AppCompatResources.getDrawable(Dice.this, imagelist.get(number1));
                            imageView.setImageDrawable(img);
                            Drawable img2 = AppCompatResources.getDrawable(Dice.this, imagelist.get(number2));
                            imageView2.setImageDrawable(img2);
                            Drawable img3 = AppCompatResources.getDrawable(Dice.this, imagelist.get(number3));
                            imageView3.setImageDrawable(img3);
                        }
                    }.start();

                    //set appear
                    CountDownTimer countDownTimer1=new CountDownTimer(3000,3000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            //set VISIBLE
                            Tai.setVisibility(View.VISIBLE);
                            Xiu.setVisibility(View.VISIBLE);
                            chan.setVisibility(View.VISIBLE);
                            le.setVisibility(View.VISIBLE);

                            score.setText("Score: "+Tong);
                            score.startAnimation(bigtosmall);
                        }
                    }.start();

                    //check win,loss
                    CountDownTimer countDownTimer2=new CountDownTimer(6000,6000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            score.setText("");
                            ketqua.setVisibility(View.VISIBLE);
                            ketqua.startAnimation(taixiu);

                            if(number1 == number2 && number2 == number3){
                                thongbao.setVisibility(View.VISIBLE);
                                thongbao.startAnimation(result);
                                thongbao.setText("Nhà Cái ăn!!");
                                detination.setProgress(detination.getProgress()-10);
                                phantram.setText(detination.getProgress()+"%");
                                ketqua.setImageResource(R.drawable.boss);
                                loss.start();
                            }
                            else   if(Tong >= 3 && Tong <= 18  &&  Tong % 2 == 0){
                                if(Tai.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.win);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.red);
                                    detination.setProgress(detination.getProgress()+10);
                                    phantram.setText(detination.getProgress()+"%");
                                    win.start();
                                }
                                else if(Xiu.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.loss);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.red);
                                    detination.setProgress(detination.getProgress()-10);
                                    phantram.setText(detination.getProgress()+"%");
                                    loss.start();
                                }
                            }
                            else if(Tong >= 3 && Tong <= 18  &&  Tong % 2 == 1){
                                if(Tai.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.loss);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.blue);
                                    detination.setProgress(detination.getProgress()-10);
                                    phantram.setText(detination.getProgress()+"%");
                                    loss.start();
                                }
                                else if(Xiu.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.win);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.blue);
                                    detination.setProgress(detination.getProgress()+10);
                                    phantram.setText(detination.getProgress()+"%");
                                    win.start();
                                }
                            }
                        }
                    }.start();

                    CountDownTimer countDownTimer4=new CountDownTimer(11000,11000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            if(detination.getProgress() == detination.getMax()){
                                winloss.setBackgroundResource(R.drawable.congratulation);
                                winloss.setVisibility(View.VISIBLE);
                                winloss.startAnimation(smalltobig);
                                win.start();
                                CountDownTimer countDownTimer4=new CountDownTimer(12000,12000) {
                                    @Override
                                    public void onTick(long l) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        button.setVisibility(View.VISIBLE);
                                        Tai.setChecked(false);
                                        Xiu.setChecked(false);
                                        Tai.setEnabled(true);
                                        Xiu.setEnabled(true);
                                    }
                                }.start();
                            }else if(detination.getProgress() == 0){
                                winloss.setBackgroundResource(R.drawable.gameover);
                                winloss.setVisibility(View.VISIBLE);
                                winloss.startAnimation(smalltobig);
                                gameOver.start();
                                CountDownTimer countDownTimer4=new CountDownTimer(12000,12000) {
                                    @Override
                                    public void onTick(long l) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        button.setVisibility(View.VISIBLE);
                                        Tai.setChecked(false);
                                        Xiu.setChecked(false);
                                        Tai.setEnabled(true);
                                        Xiu.setEnabled(true);
                                    }
                                }.start();
                            }else{
                                button.setVisibility(View.VISIBLE);
                                Tai.setChecked(false);
                                Xiu.setChecked(false);
                                Tai.setEnabled(true);
                                Xiu.setEnabled(true);
                            }
                        }
                    }.start();
                }
            }
        });

        //clear animation
        thongbao.clearAnimation();
        ketqua.clearAnimation();
        winloss.clearAnimation();
    }

    private void Check(){
        Tai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Xiu.setChecked(false);
                }
            }
        });

        Xiu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    Tai.setChecked(false);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    }