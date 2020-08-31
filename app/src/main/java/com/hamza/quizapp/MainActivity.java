package com.hamza.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView myQuestionsView, myQuizStats;
    Button myTrueBtn, myFalseBtn;
    ProgressBar myProgressBar;
    private int questionIndex;
    private int correctScore;
    private int totalScore;


    private QuizModel[] questionsCollection = new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true)


    };
    final int USER_PROGRESS = (int) Math.ceil(100 / questionsCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

            correctScore = savedInstanceState.getInt("SCORE");
            questionIndex = savedInstanceState.getInt("QUESTION");
        } else {

            correctScore = 0;
            questionIndex = 0;
        }

        // first Android Lifecycles
        Toast.makeText(MainActivity.this, "Oncreate method is called", Toast.LENGTH_SHORT).show();

        myQuestionsView = (TextView) findViewById(R.id.quizQuestion);
        myTrueBtn = (Button) findViewById(R.id.btnTrue);
        myFalseBtn = (Button) findViewById(R.id.btnWrong);
        myProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        myQuizStats = (TextView) findViewById(R.id.quizStats);
        myQuizStats.setText(correctScore + "");

        questionIndex = 0;
        totalScore = questionsCollection.length;
        myQuestionsView.setText(questionsCollection[questionIndex].getQuestion());
        myTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Toast.makeText(MainActivity.this,R.string.correct_toast_message,Toast.LENGTH_SHORT).show();*/


                evaluateAnswers(true);
                changeQuestions();
            }
        });

        myFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Toast.makeText(MainActivity.this,R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show(); */
                evaluateAnswers(false);
                changeQuestions();
            }
        });

    }

    public void changeQuestions() {

        questionIndex = (questionIndex + 1) % questionsCollection.length;
        if (questionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(MainActivity.this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is Finished!");
            quizAlert.setMessage("Your score is: " + correctScore);
            quizAlert.setPositiveButton("Finish The Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            quizAlert.show();

        }
        myQuestionsView.setText(questionsCollection[questionIndex].getQuestion());
        myProgressBar.incrementProgressBy(USER_PROGRESS);
        myQuizStats.setText(Integer.toString(correctScore));
    }

    public void evaluateAnswers(boolean answer) {

        boolean currentQuestionAnswer = questionsCollection[questionIndex].isAnswer();
        if (currentQuestionAnswer == answer) {
            correctScore = correctScore + 1;
            Toast.makeText(MainActivity.this, R.string.correct_toast_message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();
        }

    }


    // LifeCycle Methods


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "OnStart method is called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "OnResume method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this, "OnPause method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "OnStop method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this, "OnReStart method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "OnDestroy method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("SCORE", correctScore);
        outState.putInt("QUESTION", questionIndex);
    }
}
