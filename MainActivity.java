package game.alg.com.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private GameLogic gameLogic;
    private Player gamePlayer;
    private boolean isGameStarted = false;

    private Button submitButton;
    private TextView question;
    private TextView score;
    private TextView welcomeText;
    private EditText answerField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLogic = new GameLogic();
        gamePlayer = new Player();
        initializeComponents();

        submitButton.setText("START GAME");
        answerField.setVisibility(View.GONE);
    }

    private void newQuestion() {
        String qn = gameLogic.getNextQuestion();
        gamePlayer.setQuestion(qn);
        question.setText("Question: " + qn + " ?");
        score.setText("Score: " + gamePlayer.getScore());
        answerField.setText("");
    }

    private void initializeComponents() {
        submitButton = (Button) findViewById(R.id.submit_button);
        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        answerField = (EditText) findViewById(R.id.answer);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameStarted) {
                    startGame();
                } else {
                    Toast.makeText(MainActivity.this, "Game started.", Toast.LENGTH_SHORT).show();
                    isGameStarted = true;
                    welcomeText.setVisibility(View.GONE);
                    submitButton.setText("SUBMIT");
                    answerField.setVisibility(VISIBLE);
                    newQuestion();
                }
            }
        });

    }

    private void startGame() {
        char userAnswer = answerField.getText().charAt(0);
        gamePlayer.setAnswer(userAnswer);
        boolean status = gameLogic.checkAnswer(gamePlayer);
        if (status) {
            Toast.makeText(MainActivity.this, "Correct Answer.", Toast.LENGTH_SHORT).show();
            gamePlayer.setScore(gamePlayer.getScore() + 1);
        } else {
            Toast.makeText(MainActivity.this, "Incorrect Answer.", Toast.LENGTH_SHORT).show();
        }

        newQuestion();
    }

}
