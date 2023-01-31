package com.example.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Gameduaxe extends AppCompatActivity {
    Toolbar toolbarGameDuaXe;
    TextView textView;
    CheckBox cb1,cb2,cb3;
    SeekBar sb1,sb2,sb3,cay1,cay2,cay3;
    ImageButton imb,reset;
    ImageView winlose,chuong;
    PopupMenu popupMenu;
    int sodiem=100;
    int x=0;

    @Override
    protected void onResume() {
        Animation lacchuong=AnimationUtils.loadAnimation(this,R.anim.lacchuong_race);
        chuong.startAnimation(lacchuong);
        super.onResume();
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWinDow();
        setContentView(R.layout.activity_gameduaxe);

        Khaibao();
        ActionBar();

        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.tienggameduaxe_race);
        MediaPlayer dragonBall=MediaPlayer.create(this,R.raw.songoku_race);
        MediaPlayer pikachu=MediaPlayer.create(this,R.raw.pokemon_race);
        MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.win_race);
        MediaPlayer mediaPlayer2=MediaPlayer.create(this,R.raw.lose_race);
        Animation smalltobig= AnimationUtils.loadAnimation(this,R.anim.smalltobig_race);
        Animation animation_score=AnimationUtils.loadAnimation(this,R.anim.animation_score_race);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu=new PopupMenu(Gameduaxe.this,reset);
                popupMenu.getMenuInflater().inflate(R.menu.reset,popupMenu.getMenu());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true);
                }
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.car:
                                sb1.setThumb(getDrawable(R.drawable.car1));
                                sb2.setThumb(getDrawable(R.drawable.car2));
                                sb3.setThumb(getDrawable(R.drawable.car3));
                                cay1.setPadding(0,0,0,0);
                                cay2.setPadding(0,0,0,0);
                                cay3.setPadding(0,0,0,0);
                                sb1.setPadding(0,0,0,0);
                                sb2.setPadding(0,0,0,0);
                                sb3.setPadding(0,0,0,0);
                                x=0;
                                break;
                            case R.id.goku:
                                sb1.setThumb(getDrawable(R.drawable.goku1));
                                sb2.setThumb(getDrawable(R.drawable.goku2));
                                sb3.setThumb(getDrawable(R.drawable.goku3));
                                cay1.setPadding(0,100,0,0);
                                cay2.setPadding(0,130,0,0);
                                cay3.setPadding(0,100,0,0);
                                sb2.setPadding(0,60,0,0);
                                sb3.setPadding(0,40,0,0);
                                cb1.setPadding(0,60,0,0);
                                cb2.setPadding(0,60,0,0);
                                cb3.setPadding(0,60,0,0);
                                x=1;
                                break;

                            case R.id.pikachu:
                                sb1.setThumb(getDrawable(R.drawable.pikachu1));
                                sb2.setThumb(getDrawable(R.drawable.pikachu2));
                                sb3.setThumb(getDrawable(R.drawable.pikachu3));
                                cay1.setPadding(0,0,0,0);
                                cay2.setPadding(0,30,0,0);
                                cay3.setPadding(0,10,0,0);
                                sb1.setPadding(0,0,0,0);
                                sb2.setPadding(0,30,0,0);
                                sb3.setPadding(0,10,0,0);
                                x=2;
                                break;
                        }
                        return false;
                    }
                });
            }
        });

        //ẩn cây
        cay1.setVisibility(View.INVISIBLE);
        cay2.setVisibility(View.INVISIBLE);
        cay3.setVisibility(View.INVISIBLE);
        cay1.setEnabled(false);
        cay2.setEnabled(false);
        cay3.setEnabled(false);

        //set enabled cho xe
        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);

        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb1.setProgress(0);
                sb2.setProgress(0);
                sb3.setProgress(0);

                if(cb1.isChecked()||cb2.isChecked()||cb3.isChecked()) {
                    if(x == 0){
                        mediaPlayer.start();
                    }else if(x == 1){
                        dragonBall.start();
                    }else if(x == 2){
                        pikachu.start();
                    }

                    imb.setVisibility(View.INVISIBLE);
                    reset.setVisibility(View.INVISIBLE);
                    cb1.setEnabled(false);
                    cb2.setEnabled(false);
                    cb3.setEnabled(false);
                    winlose.setVisibility(View.INVISIBLE);

                    cay1.setProgress(cay1.getMax());
                    cay1.setProgress(cay2.getMax());
                    cay3.setProgress(cay3.getMax());

                    //hiển thị cây
                    cay1.setVisibility(View.VISIBLE);
                    cay2.setVisibility(View.VISIBLE);
                    cay3.setVisibility(View.VISIBLE);

                    CountDownTimer countDownTimer = new CountDownTimer(60000,50) {
                        @Override
                        public void onTick(long l) {
                            Random random = new Random();
                            int x1 = random.nextInt(2);
                            int x2 = random.nextInt(2);
                            int x3 = random.nextInt(2);

                            //ô tô di chuyển
                            sb1.setProgress(sb1.getProgress() + x1);
                            sb2.setProgress(sb2.getProgress() + x2);
                            sb3.setProgress(sb3.getProgress() + x3);

                            //cây di chuyển
                            cay1.setProgress(cay1.getProgress() - x1 -3);
                            cay2.setProgress(cay2.getProgress() - x2 -3);
                            cay3.setProgress(cay3.getProgress() - x3 -3);

                            if (cay1.getProgress() <= 0){
                                cay1.setProgress(cay1.getMax());
                            }
                            if (cay2.getProgress() <= 0){
                                cay2.setProgress(cay2.getMax());
                            }
                            if(cay3.getProgress() <= 0){
                                cay3.setProgress(cay3.getMax());
                            }


                            if (sb1.getProgress() >= sb1.getMax()){
                                this.cancel();
                                Toast.makeText(Gameduaxe.this,"Số 1 thắng",Toast.LENGTH_SHORT).show();
                                if(cb1.isChecked()){
                                    Toast.makeText(Gameduaxe.this,"Bạn được + "+10+" điểm",Toast.LENGTH_LONG).show();
                                    mediaPlayer1.start();
                                    winlose.setVisibility(View.VISIBLE);
                                    winlose.setImageResource(R.drawable.win_race);
                                    winlose.startAnimation(smalltobig);
                                    sodiem+=10;
                                }else{
                                    Toast.makeText(Gameduaxe.this,"Bạn bị - "+10+" điểm",Toast.LENGTH_LONG).show();
                                    mediaPlayer2.start();
                                    winlose.setVisibility(View.VISIBLE);
                                    winlose.setImageResource(R.drawable.lose);
                                    winlose.startAnimation(smalltobig);
                                    sodiem-=10;
                                }
                                textView.setText(sodiem+"");
                                textView.startAnimation(animation_score);
                            }
                            if(sb2.getProgress() >= sb2.getMax()){
                                this.cancel();
                                Toast.makeText(Gameduaxe.this,"Số 2 thắng",Toast.LENGTH_SHORT).show();
                                if(cb2.isChecked()){
                                    Toast.makeText(Gameduaxe.this,"Bạn được + "+10+" điểm",Toast.LENGTH_LONG).show();
                                    mediaPlayer1.start();
                                    winlose.setVisibility(View.VISIBLE);
                                    winlose.setImageResource(R.drawable.win_race);
                                    winlose.startAnimation(smalltobig);
                                    sodiem+=10;
                                }else{
                                    Toast.makeText(Gameduaxe.this,"Bạn bị - "+10+" điểm",Toast.LENGTH_LONG).show();
                                    winlose.setVisibility(View.VISIBLE);
                                    winlose.setImageResource(R.drawable.lose);
                                    winlose.startAnimation(smalltobig);
                                    mediaPlayer2.start();
                                    sodiem-=10;
                                }
                                textView.setText(sodiem+"");
                                textView.startAnimation(animation_score);
                            }
                            if(sb3.getProgress() >= sb3.getMax()) {
                                this.cancel();
                                Toast.makeText(Gameduaxe.this, "Số 3 thắng", Toast.LENGTH_SHORT).show();
                                if (cb3.isChecked()) {
                                    Toast.makeText(Gameduaxe.this, "Bạn được + "+10+" điểm", Toast.LENGTH_LONG).show();
                                    mediaPlayer1.start();
                                    winlose.setImageResource(R.drawable.win_race);
                                    winlose.startAnimation(smalltobig);
                                    winlose.setVisibility(View.VISIBLE);
                                    sodiem += 10;
                                } else {
                                    Toast.makeText(Gameduaxe.this, "Bạn bị - "+10+" điểm", Toast.LENGTH_LONG).show();
                                    mediaPlayer2.start();
                                    winlose.setImageResource(R.drawable.lose);
                                    winlose.startAnimation(smalltobig);
                                    winlose.setVisibility(View.VISIBLE);
                                    sodiem -= 10;
                                }
                                textView.setText(sodiem+"");
                                textView.startAnimation(animation_score);
                            }
                        }
                        @Override
                        public void onFinish() {

                        }
                    }.start();

                    CountDownTimer countDownTimer2=new CountDownTimer(17000,17000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            winlose.setVisibility(View.INVISIBLE);
                            imb.setVisibility(View.VISIBLE);
                            reset.setVisibility(View.VISIBLE);
                            cb1.setEnabled(true);
                            cb2.setEnabled(true);
                            cb3.setEnabled(true);
                            cb1.setChecked(false);
                            cb2.setChecked(false);
                            cb3.setChecked(false);
                        }
                    }.start();
                }else{
                    AlertDialog.Builder aleBuilder=new AlertDialog.Builder(Gameduaxe.this);
                    aleBuilder.setMessage("Vui lòng chọn cược!!");
                    aleBuilder.setTitle("Thông báo!");
                    aleBuilder.setIcon(R.drawable.start);
                    aleBuilder.setPositiveButton("OK" ,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    aleBuilder.show();
                }
            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
    }

    private void setWinDow() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarGameDuaXe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Khaibao() {
        toolbarGameDuaXe = findViewById(R.id.toolBarGameDuaXe);
        textView=findViewById(R.id.textView);
        cb1=findViewById(R.id.checkbox1);
        cb2=findViewById(R.id.checkbox2);
        cb3=findViewById(R.id.checkbox3);
        sb1=findViewById(R.id.seebar1);
        sb2=findViewById(R.id.seebar2);
        sb3=findViewById(R.id.seebar3);
        cay1=findViewById(R.id.cay1);
        cay2=findViewById(R.id.cay2);
        cay3=findViewById(R.id.cay3);
        imb=findViewById(R.id.imagebutton);
        reset=findViewById(R.id.change);
        chuong=findViewById(R.id.chuong);
        winlose=findViewById(R.id.winlose);
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
                alBuilder.setMessage("Trước khi bấm nút Start bạn cần chọn cược 1 trong 3 xe:" +
                        "\n+ Nếu xe bạn chọn về đích trước thì bạn thắng" +
                        "\n+ Và điểm của bạn sẽ được cộng 10 điểm" +
                        "\n+ Còn lại nếu bạn thua sẽ bị trừ 10 điểm");
                alBuilder.setIcon(R.drawable.start);
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
