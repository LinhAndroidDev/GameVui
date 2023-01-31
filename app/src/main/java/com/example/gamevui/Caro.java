package com.example.gamevui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Caro extends AppCompatActivity {
    Toolbar toolbarCaro;
    ImageButton playagain;
    ImageView icon;
    TextView result;
    ImageView imageView;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int nguoichoi = 0;
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWinDow();
        setContentView(R.layout.activity_caro);

        Khaibao();
        ActionBar();

        playagain.setVisibility(View.INVISIBLE);
        icon.setVisibility(View.INVISIBLE);
    }

    private void setWinDow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarCaro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Khaibao() {
        icon = findViewById(R.id.icon);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        result = findViewById(R.id.result);
        playagain = findViewById(R.id.playagain);
        toolbarCaro = findViewById(R.id.toolBarCaro);
    }

    public void dropIn(View view) {
        //Khai báo Animation and Media
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_caro);
        Animation translateRight = AnimationUtils.loadAnimation(this,R.anim.translate_right);
        MediaPlayer victory = MediaPlayer.create(this, R.raw.caro);
        MediaPlayer equal = MediaPlayer.create(this, R.raw.lose_caro);
        MediaPlayer tiengCo=MediaPlayer.create(this,R.raw.game_caro);

        Khaibao();
        imageView = (ImageView) view;
        int positive = Integer.parseInt(imageView.getTag().toString());
        if (gamestate[positive] == 2 && gameActive) {
            gamestate[positive] = nguoichoi;
            imageView.setTranslationY(-1500);
            if (nguoichoi == 0) {
                imageView.setImageResource(R.drawable.x);
                nguoichoi = 1;
            } else {
                imageView.setImageResource(R.drawable.o);
                nguoichoi = 0;
            }
            tiengCo.start();
            imageView.animate().translationYBy(1500).rotation(3600).setDuration(300);

            int count = 0;
            for (int i = 0; i < gamestate.length; i++) {
                if (gamestate[i] != 2) {
                    count++;
                }
            }

            //Check win
            for (int[] wins : win) {
                if (gamestate[wins[0]] == gamestate[wins[1]]
                        && gamestate[wins[1]] == gamestate[wins[2]]
                        && gamestate[wins[0]] != 2) {
                    //Appear
                    playagain.setVisibility(View.VISIBLE);
                    playagain.setAnimation(animation);
                    result.setVisibility(View.VISIBLE);
                    result.setAnimation(translateRight);
                    icon.setVisibility(View.VISIBLE);
                    icon.setBackgroundResource(R.drawable.finish);
                    icon.setAnimation(animation);
                    victory.start();

                    //find player win
                    gameActive = false;
                    if (nguoichoi == 0) {
                        result.setText("O win!");
                        result.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        result.setText("X win!");
                        result.setTextColor(getResources().getColor(R.color.green));
                    }
                } else {
                    if (count == 9 && gamestate[wins[0]] != 2 && gamestate[wins[1]] != 2 && gamestate[wins[2]] != 2
                            && checkfalse() == false) {
                        gameActive = false;

                        //Appear
                        playagain.setVisibility(View.VISIBLE);
                        playagain.setAnimation(animation);
                        result.setVisibility(View.VISIBLE);
                        icon.setVisibility(View.VISIBLE);
                        icon.setAnimation(animation);

                        //results
                        result.setText("Hoà");
                        icon.setBackgroundResource(R.drawable.equal);
                        equal.start();
                    }
                }
            }
        }


        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Game Caro");
                result.setVisibility(View.VISIBLE);
                result.setTextColor(getResources().getColor(R.color.black));
                playagain.clearAnimation();
                playagain.setVisibility(View.INVISIBLE);
                icon.clearAnimation();
                icon.setVisibility(View.INVISIBLE);
                nguoichoi = 0;
                gameActive = true;
                img1.setImageResource(R.drawable.white);
                img2.setImageResource(R.drawable.white);
                img3.setImageResource(R.drawable.white);
                img4.setImageResource(R.drawable.white);
                img5.setImageResource(R.drawable.white);
                img6.setImageResource(R.drawable.white);
                img7.setImageResource(R.drawable.white);
                img8.setImageResource(R.drawable.white);
                img9.setImageResource(R.drawable.white);

                for (int i = 0; i < gamestate.length; i++) {
                    gamestate[i] = 2;
                }
            }
        });
    }

    private boolean checkfalse() {
        for (int[] wins : win) {
            if (gamestate[wins[0]] == gamestate[wins[1]]
                    && gamestate[wins[1]] == gamestate[wins[2]]
                    && gamestate[wins[0]] != 2) {
                return true;
            }
        }
        return false;
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
                alBuilder.setMessage("2 người chơi lần lượt đánh X và O vào các ô" +
                        "\n+ Nếu X hoặc O đạt được 3 ô thẳng hàng nhau (ngang,chéo,dọc) thì thắng");
                alBuilder.setIcon(R.drawable.caro);
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