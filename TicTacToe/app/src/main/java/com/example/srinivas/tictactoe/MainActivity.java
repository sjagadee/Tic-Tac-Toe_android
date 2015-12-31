package com.example.srinivas.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    boolean isGameActive = true;

    int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningCombo = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void pressEffect(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameStatus[tappedCounter] == 2 && isGameActive) {

            gameStatus[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.into);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f);

            for(int[] winner : winningCombo) {

                if (gameStatus[winner[0]] != 2
                        && gameStatus[winner[0]] == gameStatus[winner[1]]
                        && gameStatus[winner[1]] == gameStatus[winner[2]]) {

                    isGameActive = false;

                    String winnerName = "Circle";

                    if (gameStatus[winner[0]] == 0) {
                        winnerName = "Into (Cross)";
                    }

                    System.out.println(gameStatus[winner[0]]);

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerDisplay);
                    winnerMessage.setText(winnerName + " Has Won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                } else {
                    boolean isGameOver = true;

                    for(int counterState : gameStatus) {
                        if (counterState == 2) isGameOver = false;
                    }

                    if (isGameOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerDisplay);
                        winnerMessage.setText("It's a Draw!");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i = 0; i < gameStatus.length; i++){
            gameStatus[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        isGameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
