package com.jezar.games.ffg.multiplicationstars;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {

    private int firstNum;
    private int secondNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        findViewById(R.id.fullscreen_game).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        firstNum = new Random().nextInt(10) + 1;
        secondNum = new Random().nextInt(10) + 1;

        String q = "What is the result of multiplication " + firstNum + " * " + secondNum;

        int Re1 = firstNum * secondNum;
        int Re2 = firstNum * (secondNum + 1);
        int Re3 = (firstNum + 1) * secondNum;
        if (Re2 == Re3)
            Re3 = (firstNum + 2) * secondNum;

        Button btnRe1 = findViewById(R.id.btnRe1);
        Button btnRe2 = findViewById(R.id.btnRe2);
        Button btnRe3 = findViewById(R.id.btnRe3);
        btnRe1.setText(Re1 + "");
        btnRe2.setText(Re2 + "");
        btnRe3.setText(Re3 + "");

        TextView tvQ = findViewById(R.id.tvQ);
        tvQ.setText(q);


        Button Correct = btnRe1;
        Button Ncorrect1 = btnRe2;
        Button Ncorrect2 = btnRe3;

        Correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });

        Ncorrect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Not Correct", Toast.LENGTH_SHORT).show();
            }
        });

        Ncorrect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Not Correct", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
