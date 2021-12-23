package com.samsung.tictac.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.samsung.tictac.MainActivity;
import com.samsung.tictac.R;
import com.samsung.tictac.models.TicTacGame;

@SuppressWarnings("Convert2Lambda")
public class SettingsActivity extends AppCompatActivity {
    EditText firstPlayerEditText;
    EditText secondPlayerEditText;

    RadioGroup firstPlayerMode;
    RadioGroup secondPlayerMode;

    RadioGroup playerCountMode;
    Spinner difficulty;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TicTacGame game = new TicTacGame();
                game.player1 = firstPlayerEditText.getText().toString();
                game.player2 = secondPlayerEditText.getText().toString();

                if (playerCountMode.getCheckedRadioButtonId() == R.id.radioButtonOnePlayer) {
                    game.amountOfPlayer = 1;
                } else if (playerCountMode.getCheckedRadioButtonId() == R.id.radioButtonTwoPlayer) {
                    game.amountOfPlayer = 2;
                } else {
                    Toast.makeText(getBaseContext(), "Please, pick player amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                game.player1IsX = true;
                game.difficulty = 0;

                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.putExtra("game", game);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initView() {
        firstPlayerEditText = findViewById(R.id.editTextFirstPlayer);
        secondPlayerEditText = findViewById(R.id.editTextSecondPlayer);

        firstPlayerMode = findViewById(R.id.radioButtonFirstPlayer);
        secondPlayerMode = findViewById(R.id.radioButtonSecondPlayer);

        playerCountMode = findViewById(R.id.radioButtonPlayerCount);
        difficulty = findViewById(R.id.spinnerDiff);
        startButton = findViewById(R.id.button);
    }
}