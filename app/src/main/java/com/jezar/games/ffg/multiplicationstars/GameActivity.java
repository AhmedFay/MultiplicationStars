package com.jezar.games.ffg.multiplicationstars;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {

    private int firstNum;
    private int secondNum;
    Button Correct;
    Button NCorrect1;
    Button NCorrect2;

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

        List<Integer> solution = new ArrayList<>();
        solution.add(Re1);
        solution.add(Re2);
        solution.add(Re3);
        Collections.shuffle(solution);

        Button btnRe1 = findViewById(R.id.btnRe1);
        Button btnRe2 = findViewById(R.id.btnRe2);
        Button btnRe3 = findViewById(R.id.btnRe3);
        btnRe1.setText(solution.get(0) + "");
        btnRe2.setText(solution.get(1) + "");
        btnRe3.setText(solution.get(2) + "");

        TextView tvQ = findViewById(R.id.tvQ);
        tvQ.setText(q);


        List<Button> btns = new ArrayList<>();
        btns.add(btnRe1);
        btns.add(btnRe2);
        btns.add(btnRe3);
        Collections.shuffle(btns);

        for (Button btn : btns) {
            if (Integer.parseInt(btn.getText().toString()) == (firstNum * secondNum)) {
                Correct = btn;
            }
        }

        for (Button btn : btns) {
            if (btn != Correct) {
                NCorrect1 = btn;
            }
        }

        for (Button btn : btns) {
            if (btn != Correct && btn != NCorrect1) {
                NCorrect2 = btn;
            }
        }

        Correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });

        NCorrect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Not Correct", Toast.LENGTH_SHORT).show();
            }
        });

        NCorrect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Not Correct", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
