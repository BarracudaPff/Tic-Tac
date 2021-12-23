package com.samsung.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.samsung.tictac.activities.SettingsActivity;
import com.samsung.tictac.activities.SplashActivity;
import com.samsung.tictac.models.TicTacGame;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button00;
    Button button01;
    Button button02;

    Button button10;
    Button button11;
    Button button12;

    Button button20;
    Button button21;
    Button button22;


    // >0 - is X
    // <0 - is Y
    int[][] gameMap = new int[3][3];
    boolean isFirstMove = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TicTacGame game = (TicTacGame) getIntent().getSerializableExtra("game");

        initView();
        System.out.println(game);
        gameLoop();
    }

    public void initView() {
        button00 = findViewById(R.id.button00);
        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button02);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
    }

    public void gameLoop() {
        button00.setOnClickListener(handleClick(0, 0));
        button01.setOnClickListener(handleClick(0, 1));
        button02.setOnClickListener(handleClick(0, 2));
        button10.setOnClickListener(handleClick(1, 0));
        button11.setOnClickListener(handleClick(1, 1));
        button12.setOnClickListener(handleClick(1, 2));
        button20.setOnClickListener(handleClick(2, 0));
        button21.setOnClickListener(handleClick(2, 1));
        button22.setOnClickListener(handleClick(2, 2));
    }

    public View.OnClickListener handleClick(int x, int y) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(x + " " + y + " " + isFirstMove);
                if (isFirstMove) {
                    gameMap[x][y] = 1;
                    ((Button) view).setText("X");
                } else {
                    gameMap[x][y] = -1;
                    ((Button) view).setText("Y");
                }
                int res = checkIsWinning();
                switch (res) {
                    case 0:
                        isFirstMove = !isFirstMove;
                        break;
                    case 1:
                        gameEnd("Draw");
                        break;
                    case 2:
                        gameEnd("X win");
                        break;
                    case 3:
                        gameEnd("0 win");
                        break;
                }
            }
        };
    }

    public void gameEnd(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    // 0 - continue
    // 1 - draft
    // 2 - X win
    // 3 - Y win
    public int checkIsWinning() {
        // Rows
        for (int i = 0; i < gameMap.length; i++) {
            int foundRow = 0;
            for (int j = 0; j < gameMap[i].length; j++) {
                foundRow += gameMap[i][j];
            }
            if (foundRow == 3) {
                return 2;
            } else if (foundRow == -3) {
                return 3;
            }
        }

        // Columns
        for (int i = 0; i < gameMap.length; i++) {
            int foundRow = 0;
            for (int j = 0; j < gameMap[i].length; j++) {
                foundRow += gameMap[j][i];
            }
            if (foundRow == 3) {
                return 2;
            } else if (foundRow == -3) {
                return 3;
            }
        }

        // Main diag
        if (gameMap[2][0] == gameMap[1][1] && gameMap[0][0] == gameMap[0][2] && gameMap[0][0] != 0) {
            int res = gameMap[0][0];
            if (res == 3) {
                return 2;
            } else if (res == -3) {
                return 3;
            }
        }

        // Secondary diag
        if (gameMap[2][0] == gameMap[0][0] && gameMap[0][2] == gameMap[2][2] && gameMap[2][0] != 0) {
            int res = gameMap[2][0];
            if (res == 3) {
                return 2;
            } else if (res == -3) {
                return 3;
            }
        }

        // Columns
        int amount = 0;
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                if (gameMap[j][i] != 0) {
                    amount++;
                }
            }
        }
        if (amount == 9) {
            return 1;
        } else {
            return 0;
        }
    }
}