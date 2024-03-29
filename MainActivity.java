package com.example.Caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.roumani.i2c.*;

public class MainActivity extends AppCompatActivity {
    private Game game;
    private String prevQuestion;
    private String answer = "";
    private String log = "";
    private int score = 0;
    private int qNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ask();
    }



    private void ask() {
        game = new Game();
        String question = game.qa();
        prevQuestion = question;

        ((TextView) findViewById(R.id.question)).setText(question);
        qNum++;
        String questionNumber = "Q#" + qNum;
        ((TextView) findViewById(R.id.qNum)).setText(questionNumber);
    }
    public void onDone(View v) {
        String playerAnswer = ((EditText) findViewById(R.id.answer)).getText().toString().toUpperCase();
        answer = game.getAnswer();
        log = "\nQ#" + " " +qNum + ":"+prevQuestion + "\n" + "Your Answer: " + playerAnswer
                + "\n" + "Correct Answer: " + answer + "\n" + log + "\n";
        ((TextView) findViewById(R.id.log)).setText(log);
        final int MAX_NUM_QUESTIONS = 9;
        if (qNum < MAX_NUM_QUESTIONS) {

            if (playerAnswer.equals(game.getAnswer().toUpperCase())) {
                score++;
                ((TextView) findViewById(R.id.score)).setText("Score = " + score);
            }
            ask();
        } else {
            if (playerAnswer.equals(game.getAnswer().toUpperCase())) {
                score++;
                ((TextView) findViewById(R.id.score)).setText("Score = " + score);
            }
            finish();

        }

    }

    public void finish() {
        ((TextView) findViewById(R.id.qNum)).setText("Game Over!");
        ((Button) findViewById(R.id.done)).setOnClickListener(null);
        ((Button)findViewById(R.id.done)) .setEnabled(false);
    }
}