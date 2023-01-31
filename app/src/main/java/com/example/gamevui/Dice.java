package com.example.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    Toolbar toolbarDice;
    ToggleButton button;
    ImageView ketqua;
    ImageView imageView,imageView2,imageView3;
    ArrayList<Integer> imagelist;
    CheckBox Tai,Xiu;
    TextView score,phantram;
    ProgressBar detination;
    ImageView winloss,chan,le;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWinDow();
        setContentView(R.layout.activity_dice);

        Khaibao();
        ActionBar();

        imagelist=new ArrayList<>();
        imagelist.add(0,R.drawable.number1);
        imagelist.add(1,R.drawable.number2);
        imagelist.add(2,R.drawable.number3);
        imagelist.add(3,R.drawable.number4);
        imagelist.add(4,R.drawable.number5);
        imagelist.add(5,R.drawable.number6);

        //Create Animation,Mp3
        Animation animation_progressbar = AnimationUtils.loadAnimation(this,R.anim.animation_progressbar);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.animation_dice);
        Animation animation1=AnimationUtils.loadAnimation(this,R.anim.button_animation);
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
                winloss.setVisibility(View.INVISIBLE);
                if(detination.getProgress() == detination.getMax() || detination.getProgress() == 0){
                    detination.setProgress(50);
                    phantram.setText(50+"%");
                }
                if (!Tai.isChecked() && !Xiu.isChecked()) {
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
                    ketqua.setVisibility(View.INVISIBLE);
                    Tai.setVisibility(View.INVISIBLE);
                    Xiu.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    chan.setVisibility(View.INVISIBLE);
                    le.setVisibility(View.INVISIBLE);

                    //set enable
                    Tai.setEnabled(false);
                    Xiu.setEnabled(false);

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
                            imageView.startAnimation(smalltobig);
                            imageView2.startAnimation(smalltobig);
                            imageView3.startAnimation(smalltobig);

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
                    CountDownTimer countDownTimer2=new CountDownTimer(5000,5000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            score.setText("");
                            ketqua.setVisibility(View.VISIBLE);
                            ketqua.startAnimation(smalltobig);

                            if(number1 == number2 && number2 == number3){
                                detination.startAnimation(animation_progressbar);
                                setProgressBarReduce();
                                ketqua.setImageResource(R.drawable.boss);
                                loss.start();
                            }
                            else   if(Tong >= 3 && Tong <= 18  &&  Tong % 2 == 0){
                                if(Tai.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.win);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.red);
                                    detination.startAnimation(animation_progressbar);
                                    setProgressBarIncrease();
                                    win.start();
                                }
                                else if(Xiu.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.loss);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.red);
                                    detination.startAnimation(animation_progressbar);
                                    setProgressBarReduce();
                                    loss.start();
                                }
                            }
                            else if(Tong >= 3 && Tong <= 18  &&  Tong % 2 == 1){
                                if(Tai.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.loss);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.blue);
                                    detination.startAnimation(animation_progressbar);
                                    setProgressBarReduce();
                                    loss.start();
                                }
                                else if(Xiu.isChecked()) {
                                    winloss.setBackgroundResource(R.drawable.win);
                                    winloss.setVisibility(View.VISIBLE);
                                    winloss.startAnimation(animation_left);
                                    ketqua.setImageResource(R.drawable.blue);
                                    detination.startAnimation(animation_progressbar);
                                    setProgressBarIncrease();
                                    win.start();
                                }
                            }
                        }
                    }.start();

                    CountDownTimer countDownTimer4=new CountDownTimer(8000,8000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            //clear animation
                            ketqua.clearAnimation();
                            winloss.clearAnimation();

                            if(detination.getProgress() == detination.getMax()){
                                winloss.setBackgroundResource(R.drawable.congratulation);
                                winloss.setVisibility(View.VISIBLE);
                                winloss.startAnimation(smalltobig);
                                win.start();
                                CountDownTimer countDownTimer4=new CountDownTimer(1000,1000) {
                                    @Override
                                    public void onTick(long l) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        setfinish();
                                    }
                                }.start();
                            }else if(detination.getProgress() == 0){
                                winloss.setBackgroundResource(R.drawable.gameover);
                                winloss.setVisibility(View.VISIBLE);
                                winloss.startAnimation(smalltobig);
                                gameOver.start();
                                CountDownTimer countDownTimer4=new CountDownTimer(1000,1000) {
                                    @Override
                                    public void onTick(long l) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        setfinish();
                                    }
                                }.start();
                            }else{
                                setfinish();
                            }
                        }
                    }.start();
                }
            }
        });
    }

    private void setWinDow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setfinish() {
        button.setVisibility(View.VISIBLE);
        Tai.setChecked(false);
        Xiu.setChecked(false);
        Tai.setEnabled(true);
        Xiu.setEnabled(true);
    }

    private void setProgressBarReduce() {
        CountDownTimer countDownTimer=new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                detination.setProgress(detination.getProgress()-10);
                phantram.setText(detination.getProgress()+"%");
            }
        }.start();
    }

    private void setProgressBarIncrease() {
        CountDownTimer countDownTimer=new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                detination.setProgress(detination.getProgress()+10);
                phantram.setText(detination.getProgress()+"%");
            }
        }.start();
    }

    private void ActionBar() {
        setSupportActionBar(toolbarDice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Khaibao() {
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        ketqua=findViewById(R.id.icon);
        Tai=findViewById(R.id.Xiu);
        Xiu=findViewById(R.id.Tai);
        chan=findViewById(R.id.chan);
        le=findViewById(R.id.le);
        score=findViewById(R.id.score);
        phantram=findViewById(R.id.phantram);
        winloss=findViewById(R.id.winloss);
        detination=findViewById(R.id.results);
        toolbarDice=findViewById(R.id.toolBarDice);
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
                finish();
                return true;
            case R.id.helpDice:
                AlertDialog.Builder alBuilder=new AlertDialog.Builder(this);
                alBuilder.setTitle("Luật chơi");
                alBuilder.setMessage("Trước khi bấm nút Start bạn cần chọn ô tài hoặc xỉu:" +
                        "\n+ Nếu 3 mặt xúc xắc bằng nhau thì chủ phòng thắng,bạn thua" +
                        "\n+ Nếu tổng 3 xúc xắc là chẵn thì Tài thắng" +
                        "\n+ Còn lại thì Xỉu thắng");
                alBuilder.setIcon(R.drawable.icon);
                alBuilder.show();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help_game,menu);
        return super.onCreateOptionsMenu(menu);
    }
}